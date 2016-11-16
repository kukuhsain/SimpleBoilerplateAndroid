package com.kukuhsain.simple.boilerplate.model.local;

import com.kukuhsain.simple.boilerplate.model.pojo.Sample;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kukuh on 04/11/16.
 */

public class RealmHelper {
    private static RealmHelper INSTANCE;

    private RealmHelper() {}

    public static RealmHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RealmHelper();
        }
        return INSTANCE;
    }

    public void addSample(Sample sample) {
        List<Sample> samples = getAllSamples();
        boolean isExisted = false;
        for (Sample sample1 : samples) {
            if (sample.getSampleId() == sample1.getSampleId()) {
                isExisted = true;
            }
        }
        if (!isExisted) {
            Realm.getDefaultInstance().executeTransaction(realm -> {
                realm.copyToRealm(sample);
            });
        }
    }

    public void addSamples(List<Sample> samples) {
        List<Sample> realmSamples = getAllSamples();
        for (Sample sample : samples) {
            boolean isExisted = false;
            for (Sample realmSample : realmSamples) {
                if (realmSample.getSampleId() == sample.getSampleId()) {
                    isExisted = true;
                }
            }
            if (!isExisted) {
                Realm.getDefaultInstance().executeTransaction(realm -> {
                    realm.copyToRealm(sample);
                });
            }
        }
    }

    public List<Sample> getAllSamples() {
        RealmResults<Sample> iterableSamples = Realm.getDefaultInstance()
                .where(Sample.class).findAll();
        return Realm.getDefaultInstance().copyFromRealm(iterableSamples);
    }
}
