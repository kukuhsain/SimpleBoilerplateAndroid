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

    public void addSample(Sample destination) {
        List<Sample> destinations = getAllSamples();
        boolean isExisted = false;
        for (Sample destination1 : destinations) {
            if (destination.getSampleId() == destination1.getSampleId()) {
                isExisted = true;
            }
        }
        if (!isExisted) {
            Realm.getDefaultInstance().executeTransaction(realm -> {
                realm.copyToRealm(destination);
            });
        }
    }

    public void addSamples(List<Sample> destinations) {
        List<Sample> realmSamples = getAllSamples();
        for (Sample destination : destinations) {
            boolean isExisted = false;
            for (Sample realmSample : realmSamples) {
                if (realmSample.getSampleId() == destination.getSampleId()) {
                    isExisted = true;
                }
            }
            if (!isExisted) {
                Realm.getDefaultInstance().executeTransaction(realm -> {
                    realm.copyToRealm(destination);
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
