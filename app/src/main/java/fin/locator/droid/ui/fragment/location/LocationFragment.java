package fin.locator.droid.ui.fragment.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import fin.locator.droid.R;
import fin.locator.droid.databinding.FragmentLocationBinding;
import fin.locator.droid.ui.base.BaseFragment;
import fin.locator.droid.ui.base.BaseViewModel;

public class LocationFragment extends BaseFragment<FragmentLocationBinding,LocationViewModel> {

    private LocationViewModel locationViewModel;
    private FragmentLocationBinding mBinding;
    @Inject
    ViewModelProvider.Factory factory;


    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_location;
    }

    @Override
    public LocationViewModel getViewModel() {
        locationViewModel =ViewModelProviders.of(this,factory).get(LocationViewModel.class);
        return locationViewModel;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBinding = getViewDataBinding();
        mBinding.setLocationViewModel(locationViewModel);

        locationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mBinding.textSlideshow.setText(s);
            }
        });
        return mBinding.getRoot();
    }
}