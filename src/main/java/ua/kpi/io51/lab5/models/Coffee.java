package ua.kpi.io51.lab5.models;

/**
 * Абстрактний базовий клас, що представляє загальні характеристики кави.
 */
public abstract class Coffee {
    private String name;
    private double weight; // Вага в кілограмах
    private double volume; // Об'єм з упаковкою в літрах
    private double price;  // Ціна в гривнях
    private int quality;   // Якість (оцінка від 1 до 10)

    /**
     * Конструктор для ініціалізації об'єкта кави.
     *
     * @param name    назва або сорт кави
     * @param weight  вага кави (кг)
     * @param volume  об'єм разом з упаковкою (літри)
     * @param price   ціна кави (грн)
     * @param quality показник якості (від 1 до 10)
     */
    public Coffee(String name, double weight, double volume, double price, int quality) {
        if (weight <= 0 || volume <= 0 || price < 0 || quality < 1 || quality > 10) {
            throw new IllegalArgumentException("Некоректні параметри для кави.");
        }
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.price = price;
        this.quality = quality;
    }

    /**
     * Обчислює співвідношення ціни до ваги (для подальшого сортування).
     *
     * @return співвідношення ціна/вага
     */
    public double getPriceToWeightRatio() {
        return price / weight;
    }

    // Геттери для доступу до приватних полів (Інкапсуляція)
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public double getVolume() { return volume; }
    public double getPrice() { return price; }
    public int getQuality() { return quality; }

    @Override
    public String toString() {
        return String.format("%s [Вага: %.2f кг, Об'єм: %.2f л, Ціна: %.2f грн, Якість: %d, Ціна/Вага: %.2f]",
                name, weight, volume, price, quality, getPriceToWeightRatio());
    }
}