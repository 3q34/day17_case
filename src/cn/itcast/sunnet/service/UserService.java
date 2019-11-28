package cn.itcast.sunnet.service;

import cn.itcast.sunnet.domian.User;

import java.util.List;

/**
 * Created by cdx on 2019/11/26.
 * desc:用户管理的业务接口
 */
public interface UserService {
    /*
     * @author: cdx
     * @desc:
     * @return:
     * @TO DO:
     * @date: 2019/11/26 14:01
     * @throws:
     */
    List<User> FindAll();

    User findUserByUsernameAndPassword(String username, String password);

    int add(User user);

    int delete(int id);

    int update(User user);

    User getUserById(int id);
    List<User> queryUserByNameAddrEmail(String name, String address, String email);
}
