package fin.locator.droid.ui.fragment.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fin.locator.droid.ui.base.BaseViewModel;

public class LocationViewModel extends BaseViewModel<LocationNavigator>{

    private MutableLiveData<String> mText;

    public LocationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is location fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}