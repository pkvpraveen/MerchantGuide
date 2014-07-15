package com.galaxy.domain;

public class Iron extends Metal {
    private float unitPrice;

    @Override
    public float getUnitPrice() {
        return this.unitPrice;
    }

    @Override
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
