package view;

import presenter.NotebookPresenter;
import model.Note;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView {
    private NotebookPresenter presenter;

    public void setPresenter(NotebookPresenter presenter) {
        this.presenter = presenter;
    }

    public void start() {
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
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNote(scanner);
                        break;
                    case 2:
                        presenter.showNotes();
                        break;
                    case 3:
                        showNotesForDay(scanner);
                        break;
                    case 4:
                        showNotesForWeek(scanner);
                        break;
                    case 5:
                        saveNotes(scanner);
                        break;
                    case 6:
                        loadNotes(scanner);
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void addNote(Scanner scanner) {
        System.out.print("Введите описание заметки: ");
        String description = scanner.nextLine();
        presenter.addNote(LocalDateTime.now(), description);
    }

    private void showNotesForDay(Scanner scanner) {
        System.out.print("Введите дату (например, 2024-11-10): ");
        String dateInput = scanner.nextLine();
        try {
            LocalDateTime date = LocalDateTime.parse(dateInput + "T00:00");
            presenter.showNotesForDay(date);
        } catch (Exception e) {
            System.out.println("Неверный формат даты.");
        }
    }

    private void showNotesForWeek(Scanner scanner) {
        System.out.print("Введите дату начала недели (например, 2024-11-10): ");
        String startOfWeekInput = scanner.nextLine();
        try {
            LocalDateTime startOfWeek = LocalDateTime.parse(startOfWeekInput + "T00:00");
            presenter.showNotesForWeek(startOfWeek);
        } catch (Exception e) {
            System.out.println("Неверный формат даты.");
        }
    }

    private void saveNotes(Scanner scanner) {
        System.out.print("Введите имя файла для сохранения: ");
        String fileName = scanner.nextLine();
        presenter.saveNotes(fileName);
    }

    private void loadNotes(Scanner scanner) {
        System.out.print("Введите имя файла для загрузки: ");
        String fileName = scanner.nextLine();
        presenter.loadNotes(fileName);
    }

    public void showNotes(List<Note> notes) {
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
