package com.swichfully.chicodespons.oorder.security;


import java.util.ArrayList;
import java.util.List;

public enum Role {

    CUSTOMER(new ArrayList<>(List.of(Feature.TEST, Feature.CREATE_USER))),
    ADMIN(new ArrayList<>(List.of(Feature.TEST, Feature.ADMIN_TEST, Feature.CREATE_USER)));

    private List<Feature> featureList;

    Role(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
