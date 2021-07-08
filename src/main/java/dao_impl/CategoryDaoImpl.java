package dao_impl;

import dao.CategoryDao;
import model.Category;
import model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {
    private MyConnection myConnection = new MyConnection();
    @Override
    public Category getObject(ResultSet resultSet) throws SQLException {
        Category category = null;
        category = new Category(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBoolean("deleted"));
        return category;
    }

//    @Override
//    public List<Category> getList(ResultSet resultSet) throws SQLException {
//        List<Category> list = new ArrayList<>();
//        while(resultSet.next()){
//            Category category = getObject(resultSet);
//            if(category != null){
//                list.add(category);
//            }
//        }
//
//        return list;
//    }

    @Override
    public List<Category> findAll() throws SQLException {
        String sql = "Select * from Category where deleted = false";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category = null;
        String sql = "Select * from Category where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            category = getObject(resultSet);
        }
        return category;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        Category newCategory = null;
        String sql = "insert into category (name, deleted) values (?, ?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1,category.getName());
        preparedStatement.setBoolean(2,category.isDeleted());
        int rs = preparedStatement.executeUpdate(); // số lượng sp được update
        if(rs > 0){
            //sử dụng hàm getGenerateKeys dể trả về resultset để trả về đối tựọng ứng với id
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                int id =(int) resultSet.getLong(1);
                newCategory = findById(id);
            }
        }

        return newCategory;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        boolean result = false;
        String sql = "update category set name = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setInt(2,category.getId());
        int rs = preparedStatement.executeUpdate();
        if(rs > 0){
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result = false;
        String sql = "update category set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0){
            result = true;
        }
        return result;
    }
}
