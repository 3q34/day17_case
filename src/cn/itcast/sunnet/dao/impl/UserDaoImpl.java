package cn.itcast.sunnet.dao.impl;

import cn.itcast.sunnet.dao.UserDao;
import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by cdx on 2019/11/26.
 * desc:
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /*
     * @author: cdx
     * @desc: jdbc查询数据库
     * @param null
     * @return:
     * @TO DO:
     * @date: 2019/11/26 14:05
     * @throws:
     */
    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username=? and password=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
    }

    @Override
    public int add(User user) {
        String sql = "insert into user(name,gender,age,address,qq,email) values(?,?,?,?,?,?)";
        return template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

    }

    @Override
    public int delete(int id) {
        String sql = "delete from user where id=?";
        return template.update(sql, id);
    }

    @Override
    public User getUser(int id) {
        String sql = "select * from user where id=?";
        Map<String, Object> map = template.queryForMap(sql, id, new BeanPropertyRowMapper<>());

        return null;
    }
}
