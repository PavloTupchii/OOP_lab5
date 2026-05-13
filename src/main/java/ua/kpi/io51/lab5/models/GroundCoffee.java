package ua.kpi.io51.lab5.models;

/**
 * Клас, що представляє мелену каву.
 */
public class GroundCoffee extends Coffee {
    public GroundCoffee(String name, double weight, double volume, double price, int quality) {
        super(name + " (мелена)", weight, volume, price, quality);
    }
}