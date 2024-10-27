import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные человека через пробел в формате: Фамилия Имя Отчество Дата_рождения(дд.мм.гг) Номер_телефона Пол(f/m): ");
        String inputStr = scanner.nextLine();

        try {
            Person person = parseInputData(inputStr);
            writeToFile(person);
        } catch (ArrayLengthException | PhoneNumberLengthException | GenderFormatException | DateTimeParseException | NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом:");
            e.printStackTrace();
        }
        scanner.close();
    }

    private static Person parseInputData(String inputStr) throws ArrayLengthException, PhoneNumberLengthException, GenderFormatException {
        String[] data = inputStr.split(" ");

        if (data.length != 6) {
            throw new ArrayLengthException();
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];

        LocalDate birthDate;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthDate = LocalDate.parse(data[3], dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Неверный формат даты. Ожидается формат: дд.мм.ггг.", data[3], 0);
        }


        if (!data[4].matches("\\d{11}")) {
            throw new PhoneNumberLengthException();
        }
        long phoneNumber = Long.parseLong(data[4]);

        String gender = data[5];
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new GenderFormatException();
        }

        System.out.println("\nИнформация");
        System.out.println("Фамилия: " + lastName);
        System.out.println("Имя: " + firstName);
        System.out.println("Отчество: " + middleName);
        System.out.println("Дата рождения: " + birthDate);
        System.out.println("Номер телефона: " + phoneNumber);
        System.out.println("Пол: " + gender);
        return new Person(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    private static void writeToFile(Person person) throws IOException {
        try (FileWriter writer = new FileWriter(person.getLastName() + ".txt", true)) {
            writer.write(person.toString() + "\n");
            System.out.println("\nДанные успешно записаны в файл: " + person.getLastName() + ".txt");
        }
    }
}