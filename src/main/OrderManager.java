import java.sql.*;

public class OrderManager {
    private Connection connection;

    public OrderManager(Connection connection) {
        this.connection = connection;
    }

    // 创建订单
    public void createOrder(String customerName, String contactInfo, String orderItems) throws SQLException {
        String sql = "INSERT INTO orders (customer_name, contact_info, order_items, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customerName);
            statement.setString(2, contactInfo);
            statement.setString(3, orderItems);
            statement.setString(4, "待处理");
            statement.executeUpdate();
        }
    }

    // 修改订单
    public void updateOrder(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newStatus);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
    }

    // 取消订单
    public void cancelOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }
}
