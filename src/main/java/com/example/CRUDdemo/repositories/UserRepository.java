package com.example.CRUDdemo.repositories;
import com.example.CRUDdemo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;
    public static int id;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (?, ?, ?)";
        jdbc.update(sql, ++id, user.getFirstName(), user.getLastName());
        return  user;
    }
    public void deleteById(int ind){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, ind);
    }

    //public void deleteById(int id)
    //"DELETE FROM userTable WHERE id=?"
}
