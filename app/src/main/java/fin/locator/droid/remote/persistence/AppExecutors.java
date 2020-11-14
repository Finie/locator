package fin.locator.droid.remote.persistence;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppExecutors {

    private  final Executor mDiskIO;

    private final Executor mNetworkIO;

    private final Executor mMainThread;


    public AppExecutors(Executor mDiskIO, Executor mNetworkIO, Executor mMainThread) {
        this.mDiskIO = mDiskIO;
        this.mNetworkIO = mNetworkIO;
        this.mMainThread = mMainThread;
    }


    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(),Executors.newFixedThreadPool(3),new MainThreadExecutors());
    }


    public Executor getmDiskIO() {
        return mDiskIO;
    }

    public Executor getmNetworkIO() {
        return mNetworkIO;
    }

    public Executor getmMainThread() {
        return mMainThread;
    }

    private static class MainThreadExecutors implements Executor {
        //get main thread from the android os
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }



}
