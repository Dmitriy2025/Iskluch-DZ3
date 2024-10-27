public class ArrayLengthException extends Exception {
    public ArrayLengthException() {
        super("Количество введенных данных не совпадает с запросом, проверьте формат ввода.");
    }
}
