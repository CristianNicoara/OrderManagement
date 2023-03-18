
import presentation.controller.Controller;
import presentation.view.*;

public class MainClass {
    /**
     * starts the application
     * @param args
     */
    public static void main(String[] args) {
        Controller controller = new Controller(new ClientOpView(), new ProductOpView(), new AddClientView(), new AddProductView(), new CreateOrderView(), new ViewSel(), new EditClientView(), new EditProductView());
    }
}
