package com.kukuhsain.simple.boilerplate.model.pojo;

import io.realm.RealmObject;

/**
 * Created by kukuh on 14/11/16.
 */

public class Sample extends RealmObject {
    private long sampleId;
    private String name;
    private String description;

    public Sample() {
    }

    public Sample(long sampleId, String name, String description) {
        this.sampleId = sampleId;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSampleId() {
        return sampleId;
    }

    public void setSampleId(long sampleId) {
        this.sampleId = sampleId;
    }
}
