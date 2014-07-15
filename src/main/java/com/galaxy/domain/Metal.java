package com.galaxy.domain;


public abstract class Metal {

    public abstract float getUnitPrice();
    public abstract void setUnitPrice(float unitPrice);

    public static Metal detect(String metal) {
        if ("Gold".equalsIgnoreCase(metal))
            return new Gold();
        if ("Silver".equalsIgnoreCase(metal))
            return new Silver();
        if ("Iron".equalsIgnoreCase(metal))
            return new Iron();
        else
            return null;
    }


}
