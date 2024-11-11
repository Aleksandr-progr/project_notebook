// src/presenter/NotebookPresenterImpl.java
package presenter;

import model.Note;
import model.Notebook;
import view.ConsoleNotebookView;

import java.time.LocalDateTime;
import java.util.List;

public class NotebookPresenterImpl implements NotebookPresenter {

    private Notebook model;
    private ConsoleNotebookView view;

    public NotebookPresenterImpl(Notebook model, ConsoleNotebookView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void addNote(LocalDateTime dateTime, String description) {
        if (description.isEmpty()) {
            view.showMessage("Описание не может быть пустым!");
            return;
        }

        model.add(new Note(dateTime, description));
        view.showMessage("Заметка успешно добавлена.");
    }

    @Override
    public void showNotes() {
        List<Note> notes = model.getNotes();
        view.showNotes(notes);
    }

    @Override
    public void showNotesForDay(LocalDateTime dateTime) {
        List<Note> notes = model.getNotesForDay(dateTime);
        view.showNotes(notes);
    }

    @Override
    public void showNotesForWeek(LocalDateTime startOfWeek) {
        List<Note> notes = model.getNotesForWeek(startOfWeek);
        view.showNotes(notes);
    }

    @Override
    public void saveNotes(String fileName) {
        try {
            model.saveToFile(fileName);
            view.showMessage("Заметки успешно сохранены.");
        } catch (Exception e) {
            view.showMessage("Ошибка при сохранении заметок.");
        }
    }

    @Override
    public void loadNotes(String fileName) {
        try {
            model.loadFromFile(fileName);
            view.showMessage("Заметки успешно загружены.");
        } catch (Exception e) {
            view.showMessage("Ошибка при загрузке заметок.");
        }
    }
}
