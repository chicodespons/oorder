package com.swichfully.chicodespons.oorder.security;


import java.util.ArrayList;
import java.util.List;


public enum Role {

    CUSTOMER(new ArrayList<>(List.of(Feature.TEST, Feature.ORDER_ITEMS))),
    ADMIN(new ArrayList<>(List.of(Feature.TEST, Feature.ADMIN_TEST, Feature.ADD_NEW_ITEM, Feature.ORDER_ITEMS, Feature.GET_ALL_CUSTOMERS, Feature.GET_CUSTOMER_BY_EMAIL)));

    private List<Feature> featureList;

    Role(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
