package cn.itcast.sunnet.service.impl;

import cn.itcast.sunnet.dao.UserDao;
import cn.itcast.sunnet.dao.impl.UserDaoImpl;
import cn.itcast.sunnet.domian.PageBean;
import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.service.UserService;

import java.util.List;

/**
 * Created by cdx on 2019/11/26.
 * desc:
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    /*
     * @author: cdx
     * @desc: 调用DAO
     * @param null
     * @return:
     * @TO DO:
     * @date: 2019/11/26 14:03
     * @throws:
     */
    @Override
    public List<User> FindAll() {
        return dao.findAll();
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return dao.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public int add(User user) {
        return dao.add(user);
    }

    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    @Override
    public List<User> queryUserByNameAddrEmail(String name, String address, String email) {
        return dao.queryUserByNameAddrEmail(name, address, email);
    }

    @Override
    public PageBean<User> findUserByPage(int currentPage, int pageSize) {
        return dao.findUserByPage(currentPage,pageSize);
    }

    @Override
    public int getCount() {
        return dao.getCount();
    }
}
