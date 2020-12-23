package fin.locator.droid.di.module;


import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import fin.locator.droid.di.ProjectViewModelFactory;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindProjectViewModelFactory(ProjectViewModelFactory projectViewModelFactory);
}
