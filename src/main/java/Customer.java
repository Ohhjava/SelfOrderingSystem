import java.sql.*;

public class Customer {
    private int id;
    private String name;
    private String contact;

    // 构造函数、getter和setter方法等可以根据需要自行添加

    // 在这里可以添加其他顾客管理相关的方法，如添加顾客、获取顾客信息等
    private int id;
    private String name;
    private String contact;

    public Customer(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public void addCustomer(Customer customer) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DianCan?user=student&password=123456");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (id, name, contact) VALUES (?, ?, ?)");
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getContact());

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}
