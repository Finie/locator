package fin.locator.droid.ui.base;


import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public class BaseViewModel<N> extends ViewModel {

    private ObservableBoolean mIsLoading = new ObservableBoolean();
    private ObservableBoolean mIsError =  new ObservableBoolean(false);
    private ObservableField<String> mErrorMessage = new ObservableField<>("");
    private ObservableField<String> mBearerToken = new ObservableField<>("");
    private WeakReference<N> mNavigator;

    public BaseViewModel() {
    }


    public ObservableBoolean getmIsLoading() {
        return mIsLoading;
    }

    public void setmIsLoading(ObservableBoolean mIsLoading) {
        this.mIsLoading = mIsLoading;
    }

    public ObservableBoolean getmIsError() {
        return mIsError;
    }

    public void setmIsError(ObservableBoolean mIsError) {
        this.mIsError = mIsError;
    }

    public ObservableField<String> getmErrorMessage() {
        return mErrorMessage;
    }

    public void setmErrorMessage(ObservableField<String> mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public ObservableField<String> getmBearerToken() {
        return mBearerToken;
    }

    public void setBearerToken(String mBearerToken) {
        this.mBearerToken.set(mBearerToken);
    }

    public WeakReference<N> getmNavigator() {
        return mNavigator;
    }

    public void setmNavigator(WeakReference<N> mNavigator) {
        this.mNavigator = mNavigator;
    }
}
