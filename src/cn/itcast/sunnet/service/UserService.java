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
     * @param  
     * @return: java.util.List<cn.itcast.sunnet.domian.User>
     * @TO DO:
     * @date: 2019/11/28 17:30
     * @throws: 
     */
    List<User> FindAll();

    User findUserByUsernameAndPassword(String username, String password);

    /*
     * @author: cdx
     * @desc: 
     * @param user 
     * @return: int
     * @TO DO:
     * @date: 2019/11/28 17:29
     * @throws: 
     */
    int add(User user);

    /*
     * @author: cdx
     * @desc:
     * @param id
     * @return: int
     * @TO DO:
     * @date: 2019/11/28 17:29
     * @throws:
     */
    int delete(int id);

    int update(User user);

    User getUserById(int id);

    List<User> queryUserByNameAddrEmail(String name, String address, String email);

}
