package cn.itcast.sunnet.dao;

import cn.itcast.sunnet.domian.User;

import java.util.List;

/**
 * Created by cdx on 2019/11/26.
 * desc:用户操作的Dao
 */
public interface UserDao {
    public List<User> findAll();
    public User findUserByUsernameAndPassword(String username,String password);
    public int add(User user);
    int delete(int id);
    User getUser(int id);
}
