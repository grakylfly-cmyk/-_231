import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    static class Customer{


        private int id;
        private String name;
        private String email;

        public Customer(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        public int getId() {
            return id;
        }
        @Override
        public String toString() {
            return "Customer {id="+ id + ", name='"
                    + name +'\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

    }
    static class Order {
        private String orderId;
        private int customerId;
        private double total;
        private String status;

        public Order(String orderId, int custometrId,
                     double totall,String status) {
            this.orderId = orderId;
            this.customerId = custometrId;
            this.total = total;
            this.status = status;
        }
        public String getOrderId() {
            return orderId;
        }

        public double getTotal() {
            return total;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderId='" + orderId + '\'' +
                    ", customerId=" + customerId +
                    ", total=" + total +
                    ", status='" + status + '\'' +
                        '}';

            }

        public void add(Order order) {
        }
    }
        static class OrderEvent {
        private int id;
        private String orderId;
        private String type;
        private double amount;
        private String descroption;


        public OrderEvent(int id, String orderId,
                          String type, double amount,
                          String description) {
            this.id = id;
            this.orderId = orderId;
            this.type = type;
            this.amount = amount;
            this.descroption = description;
        }

            public String getOrderId(){
                return orderId;
            }

            @Override
            public String toString(){
                return"OrderEvent{" +
                        "id=" + id +
                        ", orderId='" + orderId + '\'' +
                        ", type='" + type + '\'' +
                        ", amount='" + amount +
                        ", description='" +descroption + '\'' +
                        '}';
        }

        }

                static class ShopService {
                    private ArrayList<Customer> customers = new ArrayList<>();
                    private ArrayList<Order> order = new ArrayList<>();
                    private ArrayList<OrderEvent> events = new ArrayList<>();

                    private HashMap<String, Order> orderMap = new HashMap<>();

                    private int orderCounter = 1;
                    private int eventCounter = 1;

                    public void addCustomer(Customer customer) {
                        customers.add(customer);
                        System.out.println("Customer added!");

                    }

                    public void createOrder(int customerId, double total) {
                        if (total <= 0) {
                            System.out.println("Invalid amount!");
                            return;
                        }

                        boolean found = false;

                        for (Customer c : customers) {
                            if (c.getId() == customerId) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Customer not found!");
                            return;
                        }
                        String orderId = "ORD-" + orderCounter++;

                        Order order = new Order(
                                orderId,
                                customerId,
                                total,
                                "PENDING"
                        );

                        order.add(order);
                        orderMap.put(orderId, order);

                        events.add(new OrderEvent(
                                eventCounter++,
                                orderId,
                                "CREATE",
                                total,
                                "Order created"
                        ));

                        System.out.println("Order created: " + orderId);

                    }
                public void payOrder(String orderId) {
                        Order order = orderMap.get(orderId);

                        if (order == null) {
                            System.out.println("Order not found!");
                            return;
                        }

                        order.setStatus("PAID");

                        events.add(new OrderEvent(
                           eventCounter++,
                           orderId,
                           "PAY",
                                order.getTotal(),
                                "Order paid"
                        ));

                        System.out.println("Order paid!");
                }

                public void cancelOrder(String orderId) {
                        Order order = orderMap.get(orderId);

                        if (order == null) {
                            System.out.println("Order not found!");
                            return;
                        }

                        order.setStatus("CANCELLED");

                        events.add(new OrderEvent(
                           eventCounter++,
                           orderId,
                           "CANCELLED",
                           order.getTotal(),
                           "Order cancelled"
                        ));

                        System.out.println("Status: " + order.getStatus());
                }

                public void getCustomerOrders(int customerId) {
                        for (OrderEvent event : events) {
                            String orderId = "";
                            if (!event.getOrderId().equals(orderId)) {
                                continue;
                            }
                            System.out.println(event);
                        }
                     }

                    public void showOrderHistory(String next) {
                    }

                    public void getOrderStatus(int i) {
                    }
                }

                public static void main(String[] args) {
                    Scanner sc = new Scanner(System.in);
                    ShopService shop = new ShopService();

                    while (true) {
                        System.out.println("\n1. Add Customer");
                        System.out.println("2. Create order");
                        System.out.println("3. Pay order");
                        System.out.println("4. Cancel Order");
                        System.out.println("5. Order status");
                        System.out.println("6. Customer orders");
                        System.out.println("7. Order history");
                        System.out.println("0. Exit");


                        int choice = sc.nextInt();

                        switch (choice) {
                            case 1:
                                System.out.print("ID: ");
                                int id = sc.nextInt();
                                sc.nextLine();

                                System.out.print("NAME: ");
                                String name = sc.nextLine();

                                System.out.print("Email: ");
                                String emaill = sc.nextLine();


                                String email = "";
                                shop.addCustomer(
                                        new Customer(id, name, email));
                                break;

                            case 2:
                                System.out.print("Customer ID: ");
                                int customerId = sc.nextInt();

                                System.out.print("Total: ");
                                double total = sc.nextDouble();

                                shop.createOrder(customerId, total);
                                break;

                            case 3:
                                System.out.print("Order ID: ");
                                shop.payOrder(sc.next());
                                break;

                            case 4:
                                System.out.print("Order ID: ");
                                shop.cancelOrder(sc.next());
                                break;

                            case 5:
                                System.out.print("Order ID: ");
                                shop.getOrderStatus(sc.nextInt());
                                break;

                            case 6:
                                System.out.print("Order ID: ");
                                shop.getCustomerOrders(sc.nextInt());
                                break;

                            case 7:
                                System.out.print("Order ID: ");
                                shop.showOrderHistory(sc.next());
                                break;

                            case 0:
                                System.out.println("Bye!");
                                return;

                            default:
                                System.out.println("Invalid choice!");

                        }
                    }
                }
}