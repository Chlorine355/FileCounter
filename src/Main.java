import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Character.toLowerCase;

public class Main {
    public static void main(String[] args) {
        // принять файл. Вход: имя
        System.out.println("Введите имя файла для подсчёта:");
        Scanner scanner = new Scanner(System.in);
        String inputPath = scanner.nextLine();
        File inputFile = new File(inputPath);
        while (!(inputFile.exists() && !inputFile.isDirectory())) {
            System.out.println("Файл не существует. Введите новое имя:");
            inputPath = scanner.nextLine();
            inputFile = new File(inputPath);
        }
        // конец принятия файла. Выход: файл

        // создание словаря с буковками. Вход: файл
        Map<Character, Integer> dict = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (int index = 0; index < line.length(); index++) {
                    char c = line.charAt(index);
                    if ('a' <= toLowerCase(c) && toLowerCase(c) <= 'z') {
                        dict.put(c, dict.getOrDefault(c, 0) + 1);
                    }

                }
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла. Это всё, что я знаю:");
            System.out.println(e.getMessage());
            return;
        }
        // конец создания словаря. выход: словарь

        // запись. Вход: словарь. Выход: конец.
        System.out.println("Файл " + inputPath + " считан. Теперь введите имя файла для записи:");

        String outputPath = scanner.nextLine();
        File outputFile = new File(outputPath);
        while (true) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (var pair : dict.entrySet()) {
                    writer.write(pair.getKey() + ": " + pair.getValue() + "\n");
                }
                writer.flush();
                writer.close();
                System.out.println("Данные успешно записаны в файл " + outputPath + "!");
                return;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Попробуйте ввести другое имя:");
                outputPath = scanner.nextLine();
                outputFile = new File(outputPath);
                continue;
            }
        }
    }
}