package fin.locator.droid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fin.locator.droid.LocatorApplication;
import fin.locator.droid.remote.persistence.AppExecutors;

@Module(includes = {
        ViewModelModule.class
})

public class AppModule {

    @Provides
    @Singleton
    Context provideContext(LocatorApplication application){return application;}


    @Provides
    @Singleton
    AppExecutors provideAppExecutors(AppExecutors mAppExecutors){return mAppExecutors;}




}
