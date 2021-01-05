package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.UserModel;

public class UserDao {

    String url = "jdbc:mysql://localhost:3306/gwec?allowPublicKeyRetrieval=true&useSSL=false";
    String id = "root";
    String password = "password";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<UserModel> find(String loginCd, String loginPw) {

        List<UserModel> userModelList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("SELECT * FROM user WHERE login_cd = ? AND login_pw = ?");
            ps.setString(1, loginCd);
            ps.setString(2, loginPw);
            rs = ps.executeQuery();

            if (rs.next()) {
                userModelList.add(new UserModel(rs.getInt("user_id"), rs.getString("login_cd"), rs.getString("login_pw")));
            }
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
        return userModelList;
    }
}
