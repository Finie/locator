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
import androidx.lifecycle.ViewModelProviders;

import fin.locator.droid.R;
import fin.locator.droid.ui.base.BaseFragment;
import fin.locator.droid.ui.base.BaseViewModel;

public class LocationFragment extends BaseFragment<> {

    private LocationViewModel locationViewModel;


    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_slideshow;
    }

    @Override
    public BaseViewModel getViewModel() {
        locationViewModel =ViewModelProviders.of(this).get(LocationViewModel.class);
        return null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        locationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}