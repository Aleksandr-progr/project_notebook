package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Конкретная реализация интерфейса NotebookView для работы с консольным интерфейсом.
 * Этот класс предназначен для взаимодействия с пользователем через консоль: вывод заметок,
 * получение ввода даты, описания заметки и имени файла.
 * 
 * Задачи:
 * - Отображение заметок пользователю.
 * - Получение данных от пользователя через консоль.
 */
public class ConsoleNotebookView implements NotebookView {
    
    // Сканер для считывания ввода пользователя из консоли
    private Scanner scanner = new Scanner(System.in);

    /**
     * Отображает список заметок в консоль.
     * Если список пуст, выводится сообщение о том, что заметки не найдены.
     * 
     * @param notes Список заметок для отображения.
     *              Если список пуст, выводится соответствующее сообщение.
     */
    @Override
    public void showNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            // Если список пуст, выводится сообщение о том, что заметки не найдены
            System.out.println("No notes found.");
        } else {
            // Иначе, выводим все заметки
            for (Note note : notes) {
                System.out.println(note);
            }
        }
    }

    /**
     * Показывает сообщение пользователю в консоли.
     * 
     * @param message Сообщение для отображения.
     *                Метод используется для информирования пользователя о различных событиях.
     */
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Запрашивает у пользователя ввод даты и времени.
     * Ожидаемый формат ввода: yyyy-MM-dd'T'HH:mm, например, 2024-11-08T10:30.
     * 
     * @return Введенная пользователем дата и время в формате LocalDateTime.
     */
    @Override
    public LocalDateTime getDateTimeInput() {
        System.out.println("Enter date and time (yyyy-MM-dd'T'HH:mm):");
        String input = scanner.nextLine(); // Считываем строку с консоли
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Формат даты
        return LocalDateTime.parse(input, formatter); // Парсим строку в LocalDateTime
    }

    /**
     * Запрашивает у пользователя описание заметки.
     * 
     * @return Введенное пользователем описание заметки.
     *         Используется для создания или редактирования заметки.
     */
    @Override
    public String getDescriptionInput() {
        System.out.println("Enter note description:");
        return scanner.nextLine(); // Считываем описание с консоли
    }

    /**
     * Запрашивает у пользователя имя файла для сохранения или загрузки данных.
     * 
     * @return Введенное пользователем имя файла.
     */
    @Override
    public String getFileNameInput() {
        System.out.println("Enter file name:");
        return scanner.nextLine(); // Считываем имя файла с консоли
    }
}
