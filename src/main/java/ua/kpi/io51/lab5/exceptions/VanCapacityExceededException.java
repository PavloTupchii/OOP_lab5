package ua.kpi.io51.lab5.exceptions;

/**
 * Клас винятку, що викидається при спробі перевищити максимальний об'єм фургона.
 */
public class VanCapacityExceededException extends Exception {
    public VanCapacityExceededException(String message) {
        super(message);
    }
}