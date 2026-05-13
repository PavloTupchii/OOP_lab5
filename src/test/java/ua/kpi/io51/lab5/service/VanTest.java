package ua.kpi.io51.lab5.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.kpi.io51.lab5.models.Coffee;
import ua.kpi.io51.lab5.models.CoffeeBean;
import ua.kpi.io51.lab5.models.GroundCoffee;
import ua.kpi.io51.lab5.exceptions.VanCapacityExceededException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VanTest {
    private Van van;

    // Цей метод виконується перед кожним тестом, щоб створювати чистий фургон
    @BeforeEach
    void setUp() {
        van = new Van(50.0); // Фургон на 50 літрів
    }

    @Test
    void testAddCoffeeSuccess() {
        CoffeeBean bean = new CoffeeBean("Arabica", 10.0, 15.0, 4500, 9);

        // Перевіряємо, що додавання не викликає помилок
        assertDoesNotThrow(() -> van.addCoffee(bean));
        assertEquals(1, van.getCargo().size());
    }

    @Test
    void testAddCoffeeThrowsExceptionWhenOverloaded() {
        CoffeeBean hugeBag = new CoffeeBean("Giant Bag", 100.0, 60.0, 10000, 5);

        // Перевіряємо, що при перевантаженні викидається правильний виняток
        Exception exception = assertThrows(VanCapacityExceededException.class, () -> {
            van.addCoffee(hugeBag);
        });

        assertTrue(exception.getMessage().contains("Недостатньо місця"));
    }

    @Test
    void testSortByPriceToWeightRatio() throws VanCapacityExceededException {
        Coffee c1 = new CoffeeBean("Expensive", 1.0, 1.0, 1000, 9); // Співвідношення 1000
        Coffee c2 = new GroundCoffee("Cheap", 2.0, 2.0, 500, 5);    // Співвідношення 250

        van.addCoffee(c1);
        van.addCoffee(c2);

        van.sortByPriceToWeightRatio();

        // Дешевша кава (за співвідношенням) має бути першою
        assertEquals("Cheap (мелена)", van.getCargo().get(0).getName());
        assertEquals("Expensive (у зернах)", van.getCargo().get(1).getName());
    }

    @Test
    void testFindByQualityRange() throws VanCapacityExceededException {
        van.addCoffee(new CoffeeBean("Good", 1.0, 1.0, 100, 8));
        van.addCoffee(new CoffeeBean("Bad", 1.0, 1.0, 50, 4));
        van.addCoffee(new CoffeeBean("Perfect", 1.0, 1.0, 200, 10));

        List<Coffee> result = van.findByQualityRange(7, 10);

        // Має знайти 2 види кави (Good та Perfect)
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(c -> c.getName().contains("Good")));
        assertTrue(result.stream().anyMatch(c -> c.getName().contains("Perfect")));
    }
}