package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.CategoryModel;

public class CategoryDao {

    String url = "jdbc:mysql://localhost:3306/gwec?allowPublicKeyRetrieval=true&useSSL=false";
    String id = "root";
    String password = "password";
    Connection con;
    Statement st;
    ResultSet rs;

    public List<CategoryModel> findAll() {

        List<CategoryModel> categoryModelList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM category");

            while (rs.next()) {
                categoryModelList.add(new CategoryModel(rs.getInt("cat_id"), rs.getString("cat_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(con)) {
                    con.close();
                }
                if (Objects.nonNull(st)) {
                    st.close();
                }
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categoryModelList;
    }
}
