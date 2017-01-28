package com.kukuhsain.simple.boilerplate.injection.component;

import com.kukuhsain.simple.boilerplate.injection.PerActivity;
import com.kukuhsain.simple.boilerplate.injection.module.ApplicationModule;
import com.kukuhsain.simple.boilerplate.injection.module.NetworkModule;
import com.kukuhsain.simple.boilerplate.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by kukuh on 28/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules={ApplicationModule.class, NetworkModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);
}
