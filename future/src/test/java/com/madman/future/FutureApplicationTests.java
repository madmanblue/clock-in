package com.madman.future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class FutureApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        String sql = "insert into test (name) values ('张三')";
        jdbcTemplate.update(sql);
    }

    @Test
    void testGet() {
        String sql = "select * from test";

        List<User> query = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                User user = new User();
                user.setId(id);
                String name = resultSet.getString("name");
                user.setName(name);

                return user;
            }
        });

        User user = query.get(0);
        System.out.println(user);

    }
}

class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}