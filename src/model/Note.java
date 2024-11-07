package model;

import java.time.LocalDateTime;

public class Note {
    // Переменная для хранения времени создания заметки.
    private LocalDateTime dateTime;
    
    // Переменная для хранения описания заметки (например, текста заметки).
    private String description;

    /**
     * Конструктор класса Note.
     * 
     * @param dateTime - время создания заметки.
     * @param description - текстовое описание заметки.
     */
    public Note(LocalDateTime dateTime, String description) {
        this.dateTime = dateTime;  // Инициализируем время создания заметки.
        this.description = description;  // Инициализируем текстовое описание заметки.
    }

    /**
     * Получение времени создания заметки.
     * 
     * @return LocalDateTime - время создания заметки.
     */
    public LocalDateTime getDateTime() {
        return dateTime;  // Возвращаем время создания заметки.
    }

    /**
     * Получение описания заметки.
     * 
     * @return String - описание заметки.
     */
    public String getDescription() {
        return description;  // Возвращаем описание заметки.
    }

    /**
     * Переопределение метода toString для строкового представления объекта.
     * Этот метод используется для удобного отображения заметки в виде строки.
     * 
     * @return String - строка, представляющая заметку (в формате "время: описание").
     */
    @Override
    public String toString() {
        // Возвращаем строку, которая включает время и описание заметки.
        return dateTime.toString() + ": " + description;
    }
}
