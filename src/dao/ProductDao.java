package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.ProductModel;

public class ProductDao {

    String url = "jdbc:mysql://localhost:3306/gwec?allowPublicKeyRetrieval=true&useSSL=false";
    String id = "root";
    String password = "password";
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;

    public List<ProductModel> findAll() {

        List<ProductModel> productModelList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM product");

            while (rs.next()) {
                productModelList.add(new ProductModel(
                        rs.getInt("pro_cd"),
                        rs.getString("pro_name"),
                        rs.getInt("stock_no"),
                        rs.getInt("pro_price"),
                        rs.getInt("cat_id"),
                        rs.getString("pro_img"),
                        rs.getString("pro_msg")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return productModelList;
    }

    public List<ProductModel> findByCategory(int categoryId) {

        List<ProductModel> productModelList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("SELECT * FROM product WHERE cat_id = ?");
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                productModelList.add(new ProductModel(
                        rs.getInt("pro_cd"),
                        rs.getString("pro_name"),
                        rs.getInt("stock_no"),
                        rs.getInt("pro_price"),
                        rs.getInt("cat_id"),
                        rs.getString("pro_img"),
                        rs.getString("pro_msg")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return productModelList;
    }

    public List<ProductModel> findByProductName(String productName) {

        List<ProductModel> productModelList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("SELECT * FROM product WHERE pro_name like ?");
            ps.setString(1, "%" + productName + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                productModelList.add(new ProductModel(
                        rs.getInt("pro_cd"),
                        rs.getString("pro_name"),
                        rs.getInt("stock_no"),
                        rs.getInt("pro_price"),
                        rs.getInt("cat_id"),
                        rs.getString("pro_img"),
                        rs.getString("pro_msg")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return productModelList;
    }

    public ProductModel findByProductCode(int productCode) {

        ProductModel productModel = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("SELECT * FROM product, category WHERE pro_cd = ? AND product.cat_id = category.cat_id");
            ps.setInt(1, productCode);
            rs = ps.executeQuery();

            if (rs.next()) {
                productModel = new ProductModel(
                        rs.getInt("pro_cd"),
                        rs.getString("pro_name"),
                        rs.getInt("stock_no"),
                        rs.getInt("pro_price"),
                        rs.getInt("cat_id"),
                        rs.getString("cat_name"),
                        rs.getString("pro_img"),
                        rs.getString("pro_msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return productModel;
    }

    public List<ProductModel> find(int categoryId, String productName) {

        List<ProductModel> productModelList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("SELECT * FROM product WHERE pro_name like ? AND cat_id = ?");
            ps.setString(1, "%" + productName + "%");
            ps.setInt(2, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                productModelList.add(new ProductModel(
                        rs.getInt("pro_cd"),
                        rs.getString("pro_name"),
                        rs.getInt("stock_no"),
                        rs.getInt("pro_price"),
                        rs.getInt("cat_id"),
                        rs.getString("pro_img"),
                        rs.getString("pro_msg")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return productModelList;
    }

    public Map<Integer, Integer> getStockNoMap(List<Integer> productCodeList) {

        Map<Integer, Integer> stockNoMap = new HashMap<>();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT pro_cd, stock_no FROM product WHERE pro_cd IN (");
            sql.append(createInSQL(productCodeList.size()));
            sql.append(")");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement(sql.toString());
            for(int i = 0; i < productCodeList.size(); i ++) {
                ps.setInt(i + 1, productCodeList.get(i));
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                stockNoMap.put(rs.getInt("pro_cd"), rs.getInt("stock_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return stockNoMap;
    }

    public void updateStockNo(int productCode, int stockNo) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, id, password);
            ps = con.prepareStatement("UPDATE PRODUCT SET stock_no = ? WHERE pro_cd = ?");
            ps.setInt(1,stockNo);
            ps.setInt(2, productCode);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (Objects.nonNull(con)) {
                con.close();
            }
            if (Objects.nonNull(st)) {
                st.close();
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

    private String createInSQL(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length;) {
            sb.append("?");
            if (++i < length) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
