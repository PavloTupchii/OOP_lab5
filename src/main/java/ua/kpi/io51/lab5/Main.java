package ua.kpi.io51.lab5;

import ua.kpi.io51.lab5.models.*;
import ua.kpi.io51.lab5.service.Van;
import ua.kpi.io51.lab5.exceptions.VanCapacityExceededException;
import java.util.List;

/**
 * Головний клас для запуску та демонстрації програми.
 */
public class Main {
    /**
     * Точка входу в програму.
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        try {
            // Створюємо фургон об'ємом 60 літрів
            Van van = new Van(60.0);

            System.out.println("--- Завантаження кави у фургон ---");

            // Створюємо різні об'єкти, демонструючи поліморфізм
            // Параметри: Назва, вага(кг), об'єм(л), ціна(грн), якість(1-10)
            van.addCoffee(new CoffeeBean("Arabica", 10.0, 15.0, 4500, 9));
            van.addCoffee(new GroundCoffee("Espresso Mix", 5.0, 7.0, 2000, 8));
            van.addCoffee(new InstantCoffee("Nescafe", 2.0, 4.0, 1200, 6, "банка"));
            van.addCoffee(new InstantCoffee("Jacobs", 1.0, 2.0, 600, 5, "пакетик"));
            van.addCoffee(new CoffeeBean("Robusta", 15.0, 20.0, 3000, 7));

            // Виводимо початковий стан
            van.printCargo();

            // Сортування за співвідношенням ціни до ваги
            System.out.println("\n--- Сортування за ціною/вагою (від дешевших до дорожчих за кг) ---");
            van.sortByPriceToWeightRatio();
            van.printCargo();

            // Пошук за якістю
            int minQ = 7;
            int maxQ = 9;
            System.out.println("\n--- Пошук кави з якістю від " + minQ + " до " + maxQ + " ---");
            List<Coffee> result = van.findByQualityRange(minQ, maxQ);

            if (result.isEmpty()) {
                System.out.println("Нічого не знайдено.");
            } else {
                for (Coffee c : result) {
                    System.out.println(c);
                }
            }

            // Перевірка обробки винятку (спробуємо додати завеликий вантаж)
            System.out.println("\n--- Спроба перевантажити фургон ---");
            van.addCoffee(new CoffeeBean("Overload Coffee", 50.0, 100.0, 5000, 10));

        } catch (VanCapacityExceededException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Сталася непередбачена помилка: " + e.getMessage());
        }
    }
}
