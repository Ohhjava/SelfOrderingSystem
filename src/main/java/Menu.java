import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM menu";
    private static final String INSERT_QUERY = "INSERT INTO menu (name, price, description) VALUES (?, ?, ?)";

    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");

                MenuItem menuItem = new MenuItem(id, name, price, description);
                menuItems.add(menuItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, menuItem.getName());
            statement.setDouble(2, menuItem.getPrice());
            statement.setString(3, menuItem.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
