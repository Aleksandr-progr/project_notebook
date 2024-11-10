// src/presenter/NotebookPresenter.java
package presenter;

import java.time.LocalDateTime;

public interface NotebookPresenter {
    void addNote(LocalDateTime dateTime, String description);  // Метод для добавления заметки с параметрами
    void showNotes();  // Показать все заметки
    void showNotesForDay(LocalDateTime dateTime);  // Показать заметки на конкретный день
    void showNotesForWeek(LocalDateTime startOfWeek);  // Показать заметки на неделю
    void saveNotes();  // Сохранить заметки
    void loadNotes();  // Загрузить заметки
}
