// src/presenter/NotebookPresenterImpl.java
package presenter;

import model.Note;
import model.Notebook;
import view.ConsoleNotebookView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NotebookPresenterImpl implements NotebookPresenter {

    private Notebook model;
    private ConsoleNotebookView view;
    private Scanner scanner;  // Общий экземпляр Scanner

    public NotebookPresenterImpl(Notebook model, ConsoleNotebookView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);  // Инициализация сканера
    }

    @Override
    public void addNote(LocalDateTime dateTime, String description) {
        if (description.isEmpty()) {
            view.showMessage("Описание не может быть пустым!");
            return;
        }

        model.add(new Note(dateTime, description));  // Добавляем заметку в модель
        view.showMessage("Заметка успешно добавлена.");
    }

    @Override
    public void showNotes() {
        List<Note> notes = model.getNotes();  // Получаем все заметки
        view.showNotes(notes);  // Отображаем заметки
    }

    @Override
    public void showNotesForDay(LocalDateTime dateTime) {
        List<Note> notes = model.getNotesForDay(dateTime);  // Получаем заметки за этот день
        view.showNotes(notes);
    }

    @Override
    public void showNotesForWeek(LocalDateTime startOfWeek) {
        List<Note> notes = model.getNotesForWeek(startOfWeek);  // Получаем заметки за неделю
        view.showNotes(notes);
    }

    @Override
    public void saveNotes() {
        try {
            System.out.print("Введите имя файла для сохранения: ");
            String fileName = scanner.nextLine();  // Получаем имя файла
            model.saveToFile(fileName);  // Сохраняем заметки в файл
            view.showMessage("Заметки успешно сохранены.");
        } catch (Exception e) {
            view.showMessage("Ошибка при сохранении заметок.");
        }
    }

    @Override
    public void loadNotes() {
        try {
            System.out.print("Введите имя файла для загрузки: ");
            String fileName = scanner.nextLine();  // Получаем имя файла
            model.loadFromFile(fileName);  // Загружаем заметки из файла
            view.showMessage("Заметки успешно загружены.");
        } catch (Exception e) {
            view.showMessage("Ошибка при загрузке заметок.");
        }
    }

    // Добавьте закрытие Scanner в другом месте, например, в Main.java
}
