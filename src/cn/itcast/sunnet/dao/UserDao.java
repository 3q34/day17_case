package cn.itcast.sunnet.dao;

import cn.itcast.sunnet.domian.PageBean;
import cn.itcast.sunnet.domian.User;

import java.util.List;

/**
 * Created by cdx on 2019/11/26.
 * desc:用户操作的Dao
 */
public interface UserDao {
    List<User> findAll();

    User findUserByUsernameAndPassword(String username, String password);

    int add(User user);

    int delete(int id);

    User getUserById(int id);

    int update(User user);

    List<User> queryUserByNameAddrEmail(String name, String address, String email);
    PageBean<User> findUserByPage(String name, String address, String email,int currentPage, int pageSize);
    int getCount();
}
