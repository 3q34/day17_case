package cn.itcast.sunnet.dao.impl;

import cn.itcast.sunnet.dao.UserDao;
import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
}
