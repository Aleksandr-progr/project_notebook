// src/Main.java
import model.Notebook;
import view.ConsoleNotebookView;
import presenter.NotebookPresenter;
import presenter.NotebookPresenterImpl;

public class Main {
    public static void main(String[] args) {
        Notebook model = new Notebook();
        ConsoleNotebookView view = new ConsoleNotebookView();
        NotebookPresenter presenter = new NotebookPresenterImpl(model, view);

        view.setPresenter(presenter);  // устанавливаем презентер для представления
        view.start();  // запускаем приложение
    }
}
