package view; // Убедитесь, что пакет соответствует правильному местоположению файла

import java.time.LocalDateTime;
import java.util.List;
import model.Note;


public interface NotebookView {
    void showNotes(List<Note> notes);        // Отображение списка заметок
    void showMessage(String message);        // Показ сообщения пользователю
    LocalDateTime getDateTimeInput();        // Получение даты и времени от пользователя
    String getDescriptionInput();            // Получение описания заметки
    String getFileNameInput();               // Получение имени файла для сохранения/загрузки
}
