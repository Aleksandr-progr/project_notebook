import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создаем экземпляры модели, представления и презентера.
        // Презентер выступает в роли посредника между моделью и представлением.
        Notebook model = new Notebook();  // Модель, которая управляет данными заметок.
        NotebookView view = new ConsoleNotebookView();  // Представление для отображения данных в консоли.
        NotebookPresenter presenter = new NotebookPresenter(model, view);  // Презентер, который управляет взаимодействием между моделью и представлением.
        
        // Создаем сканер для ввода данных с клавиатуры.
        Scanner scanner = new Scanner(System.in);
        
        // Бесконечный цикл, предоставляющий пользователю возможность взаимодействовать с приложением.
        while (true) {
            // Отображаем меню с опциями, доступными для пользователя.
            System.out.println("1. Add Note");
            System.out.println("2. Show Notes for Day");
            System.out.println("3. Show Notes for Week");
            System.out.println("4. Save Notes");
            System.out.println("5. Load Notes");
            System.out.println("6. Exit");
            
            // Получаем ввод пользователя и сохраняем его в переменной choice.
            int choice = scanner.nextInt();
            scanner.nextLine(); // Поглощаем символ новой строки, оставшийся после ввода числа
            
            // В зависимости от выбора пользователя выполняем соответствующее действие.
            switch (choice) {
                case 1:
                    // Добавление заметки. Презентер вызывает метод добавления заметки в модель.
                    presenter.addNote();
                    break;
                case 2:
                    // Показать заметки за день. Презентер запрашивает заметки за конкретный день.
                    presenter.showNotesForDay();
                    break;
                case 3:
                    // Показать заметки за неделю. Презентер запрашивает заметки за конкретную неделю.
                    presenter.showNotesForWeek();
                    break;
                case 4:
                    // Сохранение заметок в файл или другую форму постоянного хранилища.
                    presenter.saveNotes();
                    break;
                case 5:
                    // Загрузка заметок из файла или другого хранилища.
                    presenter.loadNotes();
                    break;
                case 6:
                    // Выход из программы. Прерываем выполнение цикла.
                    return;
                default:
                    // Если пользователь ввел некорректный вариант, выводим сообщение об ошибке.
                    System.out.println("Invalid choice");
            }
        }
    }
}
