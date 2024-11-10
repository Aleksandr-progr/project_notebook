// src/Main.java
import model.Notebook;
import view.ConsoleNotebookView;
import presenter.NotebookPresenter;
import presenter.NotebookPresenterImpl;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляры всех необходимых классов
        Notebook model = new Notebook();
        ConsoleNotebookView view = new ConsoleNotebookView();
        NotebookPresenter presenter = new NotebookPresenterImpl(model, view);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        try {
            while (running) {
                System.out.println("1. Добавить заметку");
                System.out.println("2. Показать все заметки");
                System.out.println("3. Показать заметки на день");
                System.out.println("4. Показать заметки на неделю");
                System.out.println("5. Сохранить заметки");
                System.out.println("6. Загрузить заметки");
                System.out.println("7. Выход");
                System.out.print("Выберите опцию: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Поглощаем символ новой строки

                switch (choice) {
                    case 1:
                        // Добавление заметки
                        System.out.print("Введите описание заметки: ");
                        String description = scanner.nextLine();
                        presenter.addNote(LocalDateTime.now(), description);
                        break;
                    case 2:
                        presenter.showNotes();  // Показать все заметки
                        break;
                    case 3:
                        // Показать заметки на день
                        System.out.print("Введите дату (например, 2024-11-10): ");
                        String dateInput = scanner.nextLine();
                        try {
                            LocalDateTime date = LocalDateTime.parse(dateInput + "T00:00");
                            presenter.showNotesForDay(date);
                        } catch (Exception e) {
                            System.out.println("Неверный формат даты.");
                        }
                        break;
                    case 4:
                        // Показать заметки на неделю
                        System.out.print("Введите дату начала недели (например, 2024-11-10): ");
                        String startOfWeekInput = scanner.nextLine();
                        try {
                            LocalDateTime startOfWeek = LocalDateTime.parse(startOfWeekInput + "T00:00");
                            presenter.showNotesForWeek(startOfWeek);
                        } catch (Exception e) {
                            System.out.println("Неверный формат даты.");
                        }
                        break;
                    case 5:
                        presenter.saveNotes();  // Сохранить заметки
                        break;
                    case 6:
                        presenter.loadNotes();  // Загрузить заметки
                        break;
                    case 7:
                        running = false;  // Завершаем программу
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
        } finally {
            // Закрываем scanner в блоке finally, чтобы гарантировать закрытие ресурса
            scanner.close();
        }
    }
}
