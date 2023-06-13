import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int id;
    private String name;
    private double price;
    private String description;

    // 构造函数、getter和setter方法等可以根据需要自行添加

    // 在这里可以添加其他菜单管理相关的方法，如添加菜单项、获取菜单列表等
    private List<Menu> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
    }

    public List<Menu> getMenuItems() {
        // 从数据库中获取菜单项列表并返回
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DianCan?user=student&password=123456");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Menu menuItem = new Menu();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setName(resultSet.getString("name"));
                menuItem.setPrice(resultSet.getDouble("price"));
                menuItem.setDescription(resultSet.getString("description"));

                menuItems.add(menuItem);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public void addMenuItem(Menu menuItem) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DianCan?user=student&password=123456");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO menu (id, name, price, description) VALUES (?, ?, ?, ?)");
            statement.setInt(1, menuItem.getId());
            statement.setString(2, menuItem.getName());
            statement.setDouble(3, menuItem.getPrice());
            statement.setString(4, menuItem.getDescription());

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
