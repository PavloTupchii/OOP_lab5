package ua.kpi.io51.lab5.models;

/**
 * Клас, що представляє розчинну каву.
 */
public class InstantCoffee extends Coffee {
    private String packagingType;

    public InstantCoffee(String name, double weight, double volume, double price, int quality, String packagingType) {
        super(name + " (розчинна, " + packagingType + ")", weight, volume, price, quality);
        this.packagingType = packagingType;
    }

    public String getPackagingType() {
        return packagingType;
    }
}