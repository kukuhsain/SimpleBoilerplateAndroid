package com.kukuhsain.simple.boilerplate;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.testutil.TestDataFactory;
import com.kukuhsain.simple.boilerplate.presentation.samplelist.SampleListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kukuh on 31/01/17.
 */

@RunWith(AndroidJUnit4.class)
public class SampleListActivityTest {

    @Rule
    public ActivityTestRule<SampleListActivity> activityTestRule = new ActivityTestRule<>(SampleListActivity.class);

    @Test
    public void sampleListShows() {
        List<Sample> samples = TestDataFactory.createSamples(100);
        DataManager mockDataManager = mock(DataManager.class);
        when(mockDataManager.getSamples())
                .thenReturn(Observable.just(samples));

        activityTestRule.launchActivity(new Intent());
        int position = 0;
        for (Sample sample : samples) {
            onView(withId(R.id.rv_samples))
                    .perform(RecyclerViewActions.scrollToPosition(position));
            onView(withText(sample.getName()))
                    .check(matches(isDisplayed()));
            onView(withText(sample.getDescription()))
                    .check(matches(isDisplayed()));
            position++;
        }
    }
}
