package ua.kpi.io51.lab5.service;

import ua.kpi.io51.lab5.exceptions.VanCapacityExceededException;
import ua.kpi.io51.lab5.models.Coffee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клас, що представляє фургон для перевезення кави.
 */
public class Van {
    private double maxVolume;
    private double currentVolume;
    private double currentPrice;
    private List<Coffee> cargo; // Використовуємо поліморфізм: список зберігає базовий тип

    public Van(double maxVolume) {
        this.maxVolume = maxVolume;
        this.currentVolume = 0;
        this.currentPrice = 0;
        this.cargo = new ArrayList<>();
    }

    /**
     * Додає каву до фургона.
     */
    public void addCoffee(Coffee coffee) throws VanCapacityExceededException {
        if (coffee == null) {
            throw new IllegalArgumentException("Неможливо додати порожній об'єкт (null).");
        }
        if (currentVolume + coffee.getVolume() > maxVolume) {
            throw new VanCapacityExceededException("Недостатньо місця у фургоні для: " + coffee.getName());
        }

        cargo.add(coffee);
        currentVolume += coffee.getVolume();
        currentPrice += coffee.getPrice();
    }

    /**
     * Сортує товари на основі співвідношення ціни й ваги.
     */
    public void sortByPriceToWeightRatio() {
        cargo.sort(Comparator.comparingDouble(Coffee::getPriceToWeightRatio));
    }

    /**
     * Знаходить товар у фургоні, що відповідає заданому діапазону якості.
     */
    public List<Coffee> findByQualityRange(int minQuality, int maxQuality) {
        return cargo.stream()
                .filter(c -> c.getQuality() >= minQuality && c.getQuality() <= maxQuality)
                .collect(Collectors.toList());
    }

    /**
     * Виводить інформацію про вантаж.
     */
    public void printCargo() {
        System.out.println("Вантаж фургона (Зайнятий об'єм: " + currentVolume + "/" + maxVolume +
                " л, Вартість: " + currentPrice + " грн):");
        for (Coffee c : cargo) {
            System.out.println(c.toString());
        }
    }
    /**
     * Повертає список вантажу (потрібно для тестів).
     * @return список кави у фургоні
     */
    public List<Coffee> getCargo() {
        return cargo;
    }
}
