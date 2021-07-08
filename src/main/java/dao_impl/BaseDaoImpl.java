package dao_impl;

import dao.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    public List<T> getList(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<>();
        while(resultSet.next()){
            T t = getObject(resultSet);
            if(t != null){
                list.add(t);
            }
        }
        return list;
    }
}
