package module2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileApp {

    /** Запись строк в файл */
    public static void writeToFile(String fileName, List<String> lines) throws FileOperationException {
        try {
            Files.write(Path.of(fileName), lines);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка записи в файл: " + fileName, e);
        }
    }

    /** Чтение всех строк из файла */
    public static List<String> readFromFile(String fileName) throws FileOperationException {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new FileOperationException("Ошибка чтения из файла: " + fileName, e);
        }
    }

    /** Демонстрация */
    public static void main(String[] args) {
        String file = "data.txt";

        try {

            writeToFile(file, List.of("Первая строка", "Вторая строка", "Третья строка"));

            List<String> lines = readFromFile(file);
            System.out.println("Содержимое файла:");
            lines.forEach(System.out::println);

            //Делаю специально чтобы получить ошибку
            System.out.println("\nПробуем открыть несуществующий файл...");
            readFromFile("no_such_file.txt");

        } catch (FileOperationException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
