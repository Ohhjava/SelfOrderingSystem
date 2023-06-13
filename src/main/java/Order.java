import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final String INSERT_QUERY = "INSERT INTO orders (name, contact, total_amount) VALUES (?, ?, ?)";

    public void createOrder(String name, String contact, double totalAmount) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setString(2, contact);
            statement.setDouble(3, totalAmount);

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);

                    // 在此处根据orderId添加订单项数据到数据库中的订单项表
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
