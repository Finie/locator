package fin.locator.droid.di.component;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import fin.locator.droid.LocatorApplication;
import fin.locator.droid.di.builder.ActivityBuilder;
import fin.locator.droid.di.builder.FragmentBuilder;
import fin.locator.droid.di.module.AppModule;


@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class
})
public interface AppComponent extends AndroidInjector<LocatorApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<LocatorApplication>{

    }
}
