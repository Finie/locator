package fin.locator.droid.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.DaggerAppCompatActivity;
import fin.locator.droid.utils.NetworkUtils;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity {
    private T mViewDataBinding;
    private V mViewModel;



    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mViewDataBinding = DataBindingUtil.setContentView(this,getLayoutId());
        mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(),mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();

        /**
         * Set up the bearerToken here
         */
        setBearerToken("");

    }



    public T getViewDataBinding() {
        return mViewDataBinding;
    }



    /**
     * Check if network is connected
     *
     * @return true|false
     */
    public boolean isNetworkConnected(){
        return NetworkUtils.isNetworkAvailable(getApplicationContext());
    }



  public void  setBearerToken(String token){
        String bearerToken = "Bearer "+token;
        mViewModel.setBearerToken(bearerToken);
    }


    public String getBearerToken(){
        /**
         *Fetch for the bearer token and return it
         */
        return null;
    }


}
