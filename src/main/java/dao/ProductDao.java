package dao;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends BaseDao<Product>{
    List<Product> sortBy(String field, boolean isASC) throws SQLException;

    List<Product> findCategory(int CategoryId) throws SQLException;

    List<Product> search(String name, String startDate, String endDate,
                         Boolean soldOut, int guarantee, int categoryId,
                         int bought, int promotion) throws SQLException;

}
