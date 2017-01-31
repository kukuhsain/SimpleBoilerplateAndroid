package com.kukuhsain.simple.boilerplate.testutil;

import com.kukuhsain.simple.boilerplate.model.datamodel.Sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukuh on 31/01/17.
 */

public class TestDataFactory {

    public static Sample createSample(long id) {
        return new Sample(id, "Name "+id, "Description "+id);
    }

    public static List<Sample> createSamples(int amount) {
        List<Sample> samples = new ArrayList<>();
        for (int i=1; i<=amount; i++) {
            samples.add(createSample(i));
        }
        return samples;
    }
}
