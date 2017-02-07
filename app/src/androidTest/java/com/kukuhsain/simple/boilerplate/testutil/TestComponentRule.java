package com.kukuhsain.simple.boilerplate.testutil;

import android.content.Context;

import com.kukuhsain.simple.boilerplate.SimpleApp;
import com.kukuhsain.simple.boilerplate.injection.component.ActivityComponent;
import com.kukuhsain.simple.boilerplate.injection.component.DaggerActivityComponent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by kukuh on 31/01/17.
 */

public class TestComponentRule implements TestRule {

    private final ActivityComponent mComponent;
    private final Context mContext;

    public TestComponentRule(Context context) {
        mContext = context;
        mComponent = DaggerActivityComponent.builder()
                .applicationComponent(SimpleApp.get(context).getApplicationComponent())
                .build();
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

            }
        };
    }
}
