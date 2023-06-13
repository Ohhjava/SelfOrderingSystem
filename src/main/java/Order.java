import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<Menu> menuItems;
    private double totalAmount;

    // 构造函数、getter和setter方法等可以根据需要自行添加

    // 在这里可以添加其他订单管理相关的方法，如添加菜品到订单、计算订单总金额等
    private List<Menu> menuItems;
    private double totalAmount;

    public Order() {
        menuItems = new ArrayList<>();
        totalAmount = 0.0;
    }

    public void addMenuItem(Menu menuItem) {
        menuItems.add(menuItem);
        totalAmount += menuItem.getPrice();
    }

    public void removeMenuItem(Menu menuItem) {
        menuItems.remove(menuItem);
        totalAmount -= menuItem.getPrice();
    }

    public double calculateTotalAmount() {
        return totalAmount;
    }
}
