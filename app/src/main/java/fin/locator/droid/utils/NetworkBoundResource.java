package fin.locator.droid.utils;


import android.os.Build;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;




import fin.locator.droid.remote.Resource;
import fin.locator.droid.remote.persistence.ApiResponse;
import fin.locator.droid.remote.persistence.AppExecutors;

public abstract class NetworkBoundResource<ResultType,RequestType> {

    private final AppExecutors mAppExecutors;

    private final MediatorLiveData result = new MediatorLiveData();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @MainThread
    public NetworkBoundResource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> setValue(Resource.success(newData)));
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @MainThread
    private void setValue(Resource<ResultType> newValue){
        if (!Objects.equals(result.getValue(), newValue)){
            result.setValue(newValue);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void fetchFromNetwork(final LiveData<ResultType> dbSource){


        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            //noinspection ConstantConditions
            if (response.isSuccessful()) {
                mAppExecutors.getmDiskIO().execute(() -> {
                    saveCallResult(processResponse(response));
                    mAppExecutors.getmMainThread().execute(() ->
                            // we specially request a new live data,
                            // otherwise we will get immediately last cached value,
                            // which may not be updated with latest results received from network.
                            result.addSource(loadFromDb(),
                                    newData -> setValue(Resource.success(newData)))
                    );
                });
            } else {
                onFetchFailed();
                result.addSource(dbSource,
                        newData -> setValue(Resource.error(response.errorMessage, newData)));
            }
        });

    }

    protected  void onFetchFailed(){

    };

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();



    protected abstract boolean shouldFetch(Object data);


    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);



}
