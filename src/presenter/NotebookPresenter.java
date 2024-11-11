// src/presenter/NotebookPresenter.java
package presenter;

import java.time.LocalDateTime;

public interface NotebookPresenter {
    void addNote(LocalDateTime dateTime, String description);  // Добавление заметки
    void showNotes();  // Показать все заметки
    void showNotesForDay(LocalDateTime dateTime);  // Показать заметки на день
    void showNotesForWeek(LocalDateTime startOfWeek);  // Показать заметки на неделю
    void saveNotes(String fileName);  // Сохранение заметок в файл
    void loadNotes(String fileName);  // Загрузка заметок из файла
}
