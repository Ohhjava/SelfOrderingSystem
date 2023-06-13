import java.sql.*;

public class CustomerManager {
    private Connection connection;

    public CustomerManager(Connection connection) {
        this.connection = connection;
    }

    // 添加顾客
    public void addCustomer(String name, String phone) throws SQLException {
        String sql = "INSERT INTO customers (name, phone) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.executeUpdate();
        }
    }

    // 修改顾客信息
    public void updateCustomer(int customerId, String newName, String newPhone) throws SQLException {
        String sql = "UPDATE customers SET name = ?, phone = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newPhone);
            statement.setInt(3, customerId);
            statement.executeUpdate();
        }
    }

    // 查询顾客信息
    public void getCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone);
            }
        }
    }
}
