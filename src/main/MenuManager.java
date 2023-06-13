import java.sql.*;

public class MenuManager {
    private Connection connection;

    public MenuManager(Connection connection) {
        this.connection = connection;
    }

    // 添加菜单项
    public void addMenuItem(String name, double price) throws SQLException {
        String sql = "INSERT INTO menu (name, price) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.executeUpdate();
        }
    }

    // 修改菜单项
    public void updateMenuItem(int menuItemId, String newName, double newPrice) throws SQLException {
        String sql = "UPDATE menu SET name = ?, price = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setDouble(2, newPrice);
            statement.setInt(3, menuItemId);
            statement.executeUpdate();
        }
    }

    // 删除菜单项
    public void deleteMenuItem(int menuItemId) throws SQLException {
        String sql = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, menuItemId);
            statement.executeUpdate();
        }
    }
}
