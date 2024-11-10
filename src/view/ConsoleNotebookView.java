package view;

import model.Note;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView implements NotebookView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("Заметки не найдены.");
        } else {
            for (Note note : notes) {
                // Используем getDateTime() для вывода времени и getDescription() для описания
                System.out.println("Заметка: " + note.getDescription() + " (создано: " + note.getDateTime() + ")");
            }
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public LocalDateTime getDateTimeInput() {
        while (true) {
            try {
                System.out.println("Введите дату и время (yyyy-MM-dd'T'HH:mm):");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Дата не может быть пустой.");
                    continue;
                }
                return LocalDateTime.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты. Пожалуйста, используйте формат: yyyy-MM-dd'T'HH:mm (например, 2024-11-08T10:30)");
            }
        }
    }

    @Override
    public String getDescriptionInput() {
        System.out.println("Введите описание заметки:");
        return scanner.nextLine().trim();
    }

    @Override
    public String getFileNameInput() {
        System.out.println("Введите имя файла:");
        return scanner.nextLine().trim();
    }

    // Закрытие Scanner (в случае, если программа завершится)
    public void close() {
        scanner.close();
    }
}
