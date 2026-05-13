package ua.kpi.io51.lab5.models;

/**
 * Клас, що представляє каву в зернах.
 */
public class CoffeeBean extends Coffee {
    public CoffeeBean(String name, double weight, double volume, double price, int quality) {
        super(name + " (у зернах)", weight, volume, price, quality);
    }
}