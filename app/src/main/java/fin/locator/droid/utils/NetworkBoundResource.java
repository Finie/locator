package fin.locator.droid.utils;


import android.os.Build;

import androidx.annotation.MainThread;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.Objects;

import fin.locator.droid.remote.Resource;
import fin.locator.droid.remote.persistence.AppExecutors;

public abstract class NetworkBoundResource<ResultType,RequestType> {

    private final AppExecutors mAppExecutors;

    private final MediatorLiveData result = new MediatorLiveData();

    @MainThread
    public NetworkBoundResource(AppExecutors mAppExecutors) {
        this.mAppExecutors = mAppExecutors;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @MainThread
    private void setValue(Resource<ResultType> newValue){
        if (!Objects.equals(result.getValue(), newValue)){
            result.setValue(newValue);
        }
    }



    private void fetchFromNetwork(final LiveData<ResultType> dbSource){




    }







}
