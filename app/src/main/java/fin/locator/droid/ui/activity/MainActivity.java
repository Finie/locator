package fin.locator.droid.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import fin.locator.droid.BR;
import fin.locator.droid.R;
import fin.locator.droid.databinding.ActivityMainBinding;
import fin.locator.droid.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainActivityViewModel> {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding mBinding;
    private MainActivityViewModel mViewModel;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.mainActivityViewModel;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mBinding.mainAppBar.toolbar);
        mBinding.mainAppBar.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_map, R.id.nav_details, R.id.nav_location)
                .setDrawerLayout(mBinding.drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.navView, navController);


        /**
         * TODO
         * Get current fragment and toggle hide/show
         * button here navController.getCurrentPosition()
         */



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.main, menu);*/
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}