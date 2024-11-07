package presenter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Презентер для работы с заметками. Связывает модель данных (Notebook) с представлением (NotebookView).
 * Обрабатывает логику добавления заметок, отображения их по дням и неделям, а также сохранения и загрузки данных.
 */
public class NotebookPresenter {
    private Notebook model;    // Модель, хранящая заметки
    private NotebookView view; // Представление для взаимодействия с пользователем

    /**
     * Конструктор для инициализации презентера с моделью и представлением.
     * @param model модель данных, с которой будет работать презентер
     * @param view представление, через которое будет отображаться информация пользователю
     */
    public NotebookPresenter(Notebook model, NotebookView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Метод для добавления новой заметки.
     * Сначала получает данные о времени и описании заметки от представления, затем добавляет её в модель.
     */
    public void addNote() {
        // Получение даты и времени заметки через интерфейс представления
        LocalDateTime dateTime = view.getDateTimeInput();
        // Получение описания заметки
        String description = view.getDescriptionInput();
        // Добавление новой заметки в модель
        model.add(new Note(dateTime, description));
        // Уведомление пользователя о добавлении заметки
        view.showMessage("Note added.");
    }

    /**
     * Метод для отображения заметок за конкретный день.
     * Запрашивает дату у пользователя и отображает все заметки, соответствующие этой дате.
     */
    public void showNotesForDay() {
        // Получение даты от пользователя
        LocalDateTime dateTime = view.getDateTimeInput();
        // Получение списка заметок за указанный день
        List<Note> notes = model.getNotesForDay(dateTime);
        // Отображение найденных заметок
        view.showNotes(notes);
    }

    /**
     * Метод для отображения заметок за неделю.
     * Запрашивает дату начала недели у пользователя и отображает все заметки в рамках этой недели.
     */
    public void showNotesForWeek() {
        // Получение даты начала недели от пользователя
        LocalDateTime startOfWeek = view.getDateTimeInput();
        // Получение списка заметок за указанную неделю
        List<Note> notes = model.getNotesForWeek(startOfWeek);
        // Отображение найденных заметок
        view.showNotes(notes);
    }

    /**
     * Метод для сохранения заметок в файл.
     * Запрашивает имя файла у пользователя и сохраняет данные в файл.
     */
    public void saveNotes() {
        // Получение имени файла от пользователя
        String fileName = view.getFileNameInput();
        try {
            // Попытка сохранить заметки в файл
            model.saveToFile(fileName);
            // Уведомление пользователя о успешном сохранении
            view.showMessage("Notes saved to " + fileName);
        } catch (IOException e) {
            // Обработка ошибки при сохранении данных
            view.showMessage("Failed to save notes: " + e.getMessage());
        }
    }

    /**
     * Метод для загрузки заметок из файла.
     * Запрашивает имя файла у пользователя и загружает данные из файла в модель.
     */
    public void loadNotes() {
        // Получение имени файла от пользователя
        String fileName = view.getFileNameInput();
        try {
            // Попытка загрузить заметки из файла
            model.loadFromFile(fileName);
            // Уведомление пользователя о успешной загрузке
            view.showMessage("Notes loaded from " + fileName);
        } catch (IOException e) {
            // Обработка ошибки при загрузке данных
            view.showMessage("Failed to load notes: " + e.getMessage());
        }
    }
}
