package fin.locator.droid.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerFragment;
import fin.locator.droid.utils.NetworkUtils;

public abstract class BaseFragment <T extends ViewDataBinding, V extends BaseViewModel> extends DaggerFragment {


    private BaseActivity mActivity;
    private T mViewDataBinding;
    private V mViewModel;


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();


    /**
     * @return layout resource id
     */
    @LayoutRes
    public abstract int getLayout();


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();


    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    if (context instanceof BaseActivity){
        this.mActivity = (BaseActivity) context;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();

        /**
         * Set bearer token here
         * get token from where it is saved
         */
        setBearerToken("");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater,getLayout(),container,false);
        return mViewDataBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(),mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }


    /**
     * check for network connectivity
     * @return true|false
     */

    public boolean isNetworkConnected(){
        return NetworkUtils.isNetworkAvailable(getContext());
    }



    /**
     * setting the bearer token in fragments
     * @param token
     */
    public void setBearerToken(String token){
        String bearerToken = "Bearer "+token;
        mViewModel.setBearerToken(bearerToken);
    }


    public String getBearerToken(){
        /**
         * Return the bearer token from the location where
         * it will be searched from
         */

        return null;
    }

}

