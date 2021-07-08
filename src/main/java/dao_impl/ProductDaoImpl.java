package dao_impl;

import dao.ProductDao;
import model.MyConnection;
import model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    private MyConnection myConnection = new MyConnection();

    @Override
    public Product getObject(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getDouble("price"));
        product.setImage(resultSet.getString("image"));
        product.setIntroduction(resultSet.getString("introduction"));
        product.setSpecification(resultSet.getString("specification"));
        product.setSoldOut(resultSet.getBoolean("sold_out"));
        product.setguarantee(resultSet.getInt("guarantee"));
        product.setBought(resultSet.getInt("bought"));
        product.setCreateTime(resultSet.getDate("create_time"));
        product.setPromotion(resultSet.getInt("promotion"));
        product.setDeleted(resultSet.getBoolean("deleted"));
        product.setCategoryId(resultSet.getInt("category_id"));
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "select * from product where deleted = false";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = null;
        String sql = "select * from product where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            product = getObject(resultSet);
        }
        return product;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        Product newProduct = null;
        String sql = "insert into product (name,price, image, introduction, specification, sold_out, guarantee," +
                " bought, createTime,  promotion, deleted, categoryId) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setString(3, product.getImage());
        preparedStatement.setString(4, product.getIntroduction());
        preparedStatement.setString(5, product.getSpecification());
        preparedStatement.setBoolean(6, product.isSoldOut());
        preparedStatement.setInt(7, product.getguarantee());
        preparedStatement.setInt(8, product.getBought());
        preparedStatement.setDate(9, new Date(new java.util.Date().getTime()));
        preparedStatement.setInt(10, product.getPromotion());
        preparedStatement.setBoolean(11,product.isDeleted());
        preparedStatement.setInt(12, product.getCategoryId());

        int rs = preparedStatement.executeUpdate();
        if(rs > 0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id = (int) resultSet.getLong(1);
                newProduct = findById(id);
            }
        }

        return newProduct;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean result = false;
        String sql = "update product set name = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getId() );
        int rs = preparedStatement.executeUpdate();
        if(rs > 0){
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result = false;
        String sql = "delete from product where deleted = false anh id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0){
            result = true;
        }

        return result;
    }

    @Override
    public List<Product> sortBy(String field, boolean isASC) throws SQLException {
        String sql = "select * from product where deleted = false order by " + field + (isASC ? " ASC" : " DESC");
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public List<Product> findCategory(int idCategory) throws SQLException {
        String sql = "select distinct p.* from product as p, category as c where p.category_id = c.id and " +
                "c.deleted = false  and p.deleted = false and c.id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,  idCategory);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee,
                                int categoryId, int bought, int promotion) throws SQLException {

        String sql = "select distinct p.* from product as p, category as c " +
                "where p.deleted = false and" +
                "p.name like ? and " +
                "p.create_time >= ? and " +
                "p.create_time <= ? and" +
                "(? is null or p.sold_out = ? )and" +
                "(? = -1 or p.guarantee = ?) and" +
                "(? = -1 or p.bought = ?) and" +
                "(? = -1 or p.promotion = ?) and" +
                "(? > 0 and (c.deleted = false and c.id = ? and p.category_id = c.id)) and" +
                "";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, "%" + name + "%");
        preparedStatement.setString(2, startDate == null ? "0000-01-01" : startDate);
        preparedStatement.setString(3,startDate == null ? "9999-12-31" : startDate);
        preparedStatement.setBoolean(4,soldOut);
        if(soldOut == null){
            preparedStatement.setString(4,null);
            preparedStatement.setBoolean(5, true);
        }else{
            preparedStatement.setBoolean(4, true);
            preparedStatement.setBoolean(5, soldOut);
        }
        preparedStatement.setInt(6, guarantee);
        preparedStatement.setInt(7, guarantee);
        preparedStatement.setInt(8, bought);
        preparedStatement.setInt(9, bought);
        preparedStatement.setInt(10, promotion);
        preparedStatement.setInt(11, promotion);
        preparedStatement.setInt(12, categoryId);
        preparedStatement.setInt(13, categoryId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet) ;
    }
}
