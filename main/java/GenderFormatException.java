public class GenderFormatException extends Exception {
    public GenderFormatException() {
        super("Неверный формат пола. Ожидалось 'f' или 'm'.");
    }
}
