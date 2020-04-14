package com.beamm.flightbooking.functional.model;

public class LuggageAllowance {

    private Integer luggageID;
    private int carryOns;
    private double carryOnWeight;
    private int checkedBags;
    private double checkedBagWeight;

    public LuggageAllowance(Integer luggageID, int carryOns, double carryOnWeight, int checkedBags, double checkedBagWeight) {
        this.luggageID = luggageID;
        this.carryOns = carryOns;
        this.carryOnWeight = carryOnWeight;
        this.checkedBags = checkedBags;
        this.checkedBagWeight = checkedBagWeight;
    }

    public Integer getLuggageID() {
        return luggageID;
    }

    public int getCarryOns() {
        return carryOns;
    }

    public double getCarryOnWeight() {
        return carryOnWeight;
    }

    public int getCheckedBags() {
        return checkedBags;
    }

    public double getCheckedBagWeight() {
        return checkedBagWeight;
    }
}
