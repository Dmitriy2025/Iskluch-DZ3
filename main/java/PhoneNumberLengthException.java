public class PhoneNumberLengthException extends Exception {
    public PhoneNumberLengthException() {
        super("Количество цифр в телефонном номере не соответствует формату.");
    }
}
