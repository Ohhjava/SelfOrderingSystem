import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        Order order = new Order();
        Customer customer = new Customer(1, "John Doe", "john.doe@example.com");

        // 添加菜单项到菜单对象
        Menu menuItem1 = new Menu();
        menuItem1.setId(1);
        menuItem1.setName("Hamburger");
        menuItem1.setPrice(8.99);
        menuItem1.setDescription("Delicious hamburger with all the fixings");
        menuItem1.setId(2);
        menuItem1.setName("French Fries");
        menuItem1.setPrice(3.99);
        menuItem1.setDescription("Crispy golden French fries");



        Menu menuItem2 = new Menu();
        menuItem2.setId(2);
        menuItem2.setName("French Fries");
        menuItem2.setPrice(3.99);
        menuItem2.setDescription("Crispy golden French fries");

        menu.addMenuItem(menuItem1);
        menu.addMenuItem(menuItem2);

        System.out.println("欢迎使用自助点餐系统！");

        // 显示菜单供顾客选择
        System.out.println("菜单：");
        for (Menu item : menu.getMenuItems()) {
            System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
        }

        // 获取顾客姓名和联系方式
        System.out.print("请输入您的姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入您的联系方式：");
        String contact = scanner.nextLine();
        Customer newCustomer = new Customer(2, name, contact);

        // 根据顾客选择的菜单项，添加到订单对象
        System.out.print("请选择菜单项（输入菜单项的ID，以逗号分隔）：");
        String choice = scanner.nextLine();
        String[] menuIds = choice.split(",");
        for (String menuId : menuIds) {
            int id = Integer.parseInt(menuId.trim());
            Menu menuItem = getMenuById(menu, id);
            if (menuItem != null) {
                order.addMenuItem(menuItem);
            }
        }

        // 计算订单的总金额
        double totalAmount = order.calculateTotalAmount();

        // 显示订单信息
        System.out.println("订单详情：");
        System.out.println("顾客信息：");
        System.out.println("姓名：" + newCustomer.getName());
        System.out.println("联系方式：" + newCustomer.getContact());
        System.out.println("菜单项：");
        for (Menu item : order.getMenuItems()) {
            System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
        }
        System.out.println("总金额：$" + totalAmount);

        scanner.close();
    }

    private static Menu getMenuById(Menu menu, int id) {
        for (Menu item : menu.getMenuItems()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
