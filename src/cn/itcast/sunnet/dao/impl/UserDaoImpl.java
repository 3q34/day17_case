package cn.itcast.sunnet.dao.impl;

import cn.itcast.sunnet.dao.UserDao;
import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Iterator;
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
    public User getUserById(int id) {
        String sql = "select * from user where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    @Override
    public int update(User user) {
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
        return template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    public List<User> queryUserByNameAddrEmail(String name, String address, String email) {
        List<Object> params = new ArrayList<>();
        String sql = "select * from user where 1=1";
        if (name != null && name != "") {
            sql += "  and name=?";
            params.add(name);
        }

        if (address != null && address != "") {
            sql += " and address=?";
            params.add(address);
        }
        if (email != null && email != "") {
            sql += "  and email=?";
            params.add(email);
        }

        return this.template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }
}
