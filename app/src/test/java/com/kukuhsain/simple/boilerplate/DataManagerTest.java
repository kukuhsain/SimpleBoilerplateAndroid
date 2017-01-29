package com.kukuhsain.simple.boilerplate;

import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.local.PreferencesHelper;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.model.remote.RetrofitService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

/**
 * Created by kukuh on 29/01/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock RetrofitService mMockRetrofitService;
    @Mock PreferencesHelper mPreferencesHelper;
    @Mock RealmHelper mRealmHelper;
    private DataManager mDataManager;

    @Before
    public void init() {
        mDataManager = new DataManager(mMockRetrofitService, mPreferencesHelper, mRealmHelper);
    }

    private List<Sample> getDummySamples() {
        List<Sample> samples = new ArrayList<>();
        for (int i=1; i<=10; i++) {
            Sample sample = new Sample(i, "Dummy Name "+i, "Dummy description "+i);
            samples.add(sample);
        }
        return samples;
    }

    @Test
    public void getSamplesFromApi_Success() {
        List<Sample> samples = getDummySamples();
        when(mMockRetrofitService.getSamples())
                .thenReturn(Observable.just(samples));

        TestSubscriber<List<Sample>> result = new TestSubscriber<>();
        mDataManager.getSamples().subscribe(result);
        result.assertNoErrors();
        result.assertValue(samples);
    }
}
