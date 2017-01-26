package com.kukuhsain.simple.boilerplate.model.datamodel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kukuh on 14/11/16.
 */

public class Sample extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private String description;

    public Sample() {
    }

    public Sample(long id, String name, String description) {
        this.id = id;
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

    public double getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
