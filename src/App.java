import lists.CustomerManagement;
import lists.OrderManagement;
import lists.ProductManagement;
import lists.UserManagement;
import utils.Menu;

public class App {
    public static void main(String[] args) throws Exception {
        UserManagement.getInstance();
        ProductManagement productManagement = ProductManagement.getInstance();
        CustomerManagement customerManagement = CustomerManagement.getInstance();
        OrderManagement orderManagement = OrderManagement.getInstance();
        boolean ext = false;
        do {
            int choice = Menu.getAdminChoice();
            switch (choice) {
                case 1:
                    productManagement.listAllProducts();
                    break;
                case 2:
                    customerManagement.listAllCustomers();
                    break;
                case 3:
                    customerManagement.searchById();
                    break;
                case 4:
                    customerManagement.addCustomer();
                    break;
                case 5:
                    customerManagement.updateCustomer();
                    break;
                case 6:
                    customerManagement.saveCustomerList();
                    break;
                case 7:
                    orderManagement.printAllOrders();
                    break;
                case 8:
                    orderManagement.printAllPendingOrders();
                    break;
                case 9:
                    orderManagement.addOrder();
                    break;
                case 10:
                    orderManagement.updateOrder();
                    break;
                case 11:
                    orderManagement.saveOrderList();
                    break;
                case 12:
                    ext = true;
                    break;
            }
        } while (!ext);
    }
}
