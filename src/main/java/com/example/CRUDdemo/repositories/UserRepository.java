package com.example.CRUDdemo.repositories;
import com.example.CRUDdemo.config.DatabaseQuerySettings;
import com.example.CRUDdemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final DatabaseQuerySettings sql;
    private final JdbcTemplate jdbc;
    public static int id;

    public List<User> findAll() {
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql.getSelect(), userRowMapper);
    }

    public User save(User user) {
        jdbc.update(sql.getInsert(), ++id, user.getFirstName(), user.getLastName());
        return  user;
    }
    public void deleteById(int ind){
        jdbc.update(sql.getDelete(), ind);
    }

   public void updateById(User user){
       jdbc.update(sql.getUpdate(), user.getFirstName(), user.getLastName(), user.getId());
   }
}
