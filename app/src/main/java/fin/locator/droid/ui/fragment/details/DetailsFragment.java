package fin.locator.droid.ui.fragment.details;

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

public class DetailsFragment extends Fragment {

    private DetailsViewModel detailsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        detailsViewModel =
                ViewModelProviders.of(this).get(DetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_details, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        detailsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}