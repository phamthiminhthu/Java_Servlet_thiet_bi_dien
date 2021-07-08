package model;

import Common.AppConfig;

import java.sql.*;

public class MyConnection {
    public static Connection connection = null;

    /**
     * Các bước để kết nối database
     * b1 : Kiểm tra driver jdbc đã tồn tại hay chưa bằng hàm drivetest()
     * b2 : thiện hiện kết nối db bằng hàm connectDB
     * b3 : dùng hàm prepare và pareparUpdate để thực  các câu lệh
     * b4 : đóng kết nối
     */

    public void driverTest() throws ClassNotFoundException{
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("JDBC Driver not found");
        }
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {
        if(connection == null){
            driverTest();
            // thực hiện kết nối
            try{
                connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
                if(connection != null){
                    System.out.println("Connect DB successfully");
                }
            }catch (SQLException e){
                throw new SQLException("Connect DB fail" + e.getMessage());
            }

        }
        return connection;

    }

    public PreparedStatement prepare(String sql){
        try{
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
        }catch(SQLException throwables){
            throwables.printStackTrace();
            return null;
        }

    }

    public PreparedStatement prepareUpdate(String sql){
        try{
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // trả về key
            //RETURN_GENERATED_KEYS có tác dụng (giả sửtheem một bả ghi category thì chỉ truyền tên và trường
            // delete và không được truyền id giúp mình lấy ra id khi add và sửa thành công
        }catch(SQLException throwables){
            throwables.printStackTrace();
            return null;

        }
    }

    // sau khi kết nối và thao tác trên db thàh công, nếu không làm việc nữa
    // thì sẽ giải phóng connection

    public void closeConnection() throws SQLException{
        if(connection != null){
            connection.close();
            System.out.println("Connection is closed");
        }
    }


}
