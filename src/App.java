import lists.CustomerManagement;
import lists.ProductManagement;
import utils.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        boolean ext = false;
        do {
            int mainChoice = Menu.getMainChoice();
            ProductManagement productManagement = ProductManagement.getInstance();
            CustomerManagement customerManagement = CustomerManagement.getInstance();
            switch (mainChoice) {
                case 1:
                    productManagement.listAllProducts();
                    break;
                case 2:
                    customerManagement.listAllCustomers();
                    break;
                case 3:
                    customerManagement.findById();
                    break;
                case 4:
                    customerManagement.addCustomer();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    ext = true;
                    break;
            }
        } while (!ext);
    }
}
