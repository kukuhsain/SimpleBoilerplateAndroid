package com.kukuhsain.simple.boilerplate;

import com.kukuhsain.simple.boilerplate.model.DataManager;
import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;
import com.kukuhsain.simple.boilerplate.model.local.RealmHelper;
import com.kukuhsain.simple.boilerplate.testutil.RxAndroidSchedulersRule;
import com.kukuhsain.simple.boilerplate.presentation.samplelist.SampleListMvpView;
import com.kukuhsain.simple.boilerplate.presentation.samplelist.SampleListPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kukuh on 28/01/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class SampleListPresenterTest {

    @Mock
    SampleListMvpView mMockSampleListMvpView;
    @Mock DataManager mMockDataManager;
    @Mock RealmHelper mMockRealmHelper;
    private SampleListPresenter mSampleListPresenter;

    @Rule
    public final RxAndroidSchedulersRule mRxAndroidSchedulersRule = new RxAndroidSchedulersRule();

    @Before
    public void init() {
        mSampleListPresenter = new SampleListPresenter(mMockDataManager);
        mSampleListPresenter.attachView(mMockSampleListMvpView);
    }

    @After
    public void destroy() {
        mSampleListPresenter.detachView();
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
    public void getSamplesSuccess() {
        List<Sample> samples = getDummySamples();
        when(mMockDataManager.getRealmHelper())
                .thenReturn(mMockRealmHelper);
        when(mMockDataManager.getRealmHelper().getAllSamples())
                .thenReturn(samples);
        when(mMockDataManager.getSamples())
                .thenReturn(Observable.just(samples));
        mSampleListPresenter.getSamples();
        verify(mMockSampleListMvpView, atLeastOnce()).showSamples(samples);
    }
}
