package com.kukuhsain.simple.boilerplate.model.local;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

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

    public void saveOrUpdateSample(Sample sample) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(sample);
        });
    }

    public void saveOrUpdateSamples(List<Sample> samples) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(samples);
        });
    }

    public List<Sample> getAllSamples() {
        RealmResults<Sample> iterableSamples = Realm.getDefaultInstance()
                .where(Sample.class)
                .findAll();
        return Realm.getDefaultInstance().copyFromRealm(iterableSamples);
    }
}
