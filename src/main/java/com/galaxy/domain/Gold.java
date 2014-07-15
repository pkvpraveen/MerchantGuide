package com.galaxy.domain;

/**
 * Created by Praveen on 06-07-2014.
 */
public class Gold extends Metal {
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
