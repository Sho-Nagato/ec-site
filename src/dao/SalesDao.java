package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import model.SalesModel;

public class SalesDao {

    String url = "jdbc:mysql://localhost:3306/gwec?allowPublicKeyRetrieval=true&useSSL=false";
    String id = "root";
    String password = "password";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public void insert(SalesModel salesModel) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("INSERT INTO sales (user_id, pro_cd, sales_date, sales_price) values (?, ?, NOW(), ?)");
            ps.setInt(1, salesModel.getUserId());
            ps.setInt(2, salesModel.getProductCode());
            ps.setInt(3, salesModel.getSalesPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(con)) {
                    con.close();
                }
                if (Objects.nonNull(ps)) {
                    ps.close();
                }
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
