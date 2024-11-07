package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Notebook {
    // Список заметок, хранящий все записи в блокноте.
    private List<Note> notes = new ArrayList<>();

    /**
     * Метод для добавления новой заметки в блокнот.
     * 
     * @param note - заметка, которую нужно добавить.
     */
    public void add(Note note) {
        notes.add(note);  // Добавляем заметку в список.
    }

    /**
     * Метод для получения всех заметок в блокноте.
     * 
     * @return список всех заметок.
     */
    public List<Note> getNotes() {
        return new ArrayList<>(notes);  // Возвращаем копию списка заметок для предотвращения модификаций внешними классами.
    }

    /**
     * Метод для получения всех заметок на конкретный день.
     * Фильтруем заметки по дате, оставляем только те, которые относятся к указанному дню.
     * 
     * @param dateTime - время, для которого нужно получить заметки (дата).
     * @return список заметок, относящихся к указанному дню.
     */
    public List<Note> getNotesForDay(LocalDateTime dateTime) {
        return notes.stream()
            .filter(note -> note.getDateTime().toLocalDate().isEqual(dateTime.toLocalDate()))  // Фильтруем по дате.
            .sorted(Comparator.comparing(Note::getDateTime))  // Сортируем по времени.
            .collect(Collectors.toList());  // Собираем результат в список.
    }

    /**
     * Метод для получения всех заметок за неделю, начиная с указанной даты.
     * Фильтруем заметки по диапазону дат, соответствующему неделе.
     * 
     * @param startOfWeek - начало недели, от которого будем отсчитывать.
     * @return список заметок, относящихся к этой неделе.
     */
    public List<Note> getNotesForWeek(LocalDateTime startOfWeek) {
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1);  // Вычисляем конец недели.
        return notes.stream()
            .filter(note -> !note.getDateTime().isBefore(startOfWeek) && !note.getDateTime().isAfter(endOfWeek))  // Фильтруем заметки в пределах недели.
            .sorted(Comparator.comparing(Note::getDateTime))  // Сортируем заметки по времени.
            .collect(Collectors.toList());  // Собираем и возвращаем список заметок.
    }

    /**
     * Метод для сохранения заметок в файл.
     * Каждая заметка записывается в файл в строковом формате.
     * 
     * @param fileName - имя файла, в который нужно сохранить заметки.
     * @throws IOException - исключение при ошибке записи в файл.
     */
    public void saveToFile(String fileName) throws IOException {
        // Используем try-with-resources для автоматического закрытия ресурсов.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : notes) {
                writer.write(note.toString());  // Записываем заметку в файл в виде строки.
                writer.newLine();  // Добавляем новую строку после каждой заметки.
            }
        }
    }

    /**
     * Метод для загрузки заметок из файла.
     * Заметки считываются из файла, каждую строку делим на дату и описание.
     * 
     * @param fileName - имя файла, из которого нужно загрузить заметки.
     * @throws IOException - исключение при ошибке чтения файла.
     */
    public void loadFromFile(String fileName) throws IOException {
        notes.clear();  // Очищаем список заметок перед загрузкой.
        // Используем try-with-resources для автоматического закрытия ресурсов.
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {  // Читаем файл построчно.
                String[] parts = line.split(": ", 2);  // Разделяем строку на дату и описание.
                LocalDateTime dateTime = LocalDateTime.parse(parts[0]);  // Парсим дату из строки.
                String description = parts[1];  // Получаем описание.
                notes.add(new Note(dateTime, description));  // Создаем объект заметки и добавляем в список.
            }
        }
    }
}
