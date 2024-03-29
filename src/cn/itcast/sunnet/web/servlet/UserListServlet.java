package cn.itcast.sunnet.web.servlet;

import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.service.UserService;
import cn.itcast.sunnet.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by cdx on 2019/11/26.
 * desc:
 */
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        List<User> users = null;
        UserService service = new UserServiceImpl();

        if ((name != "" && name != null) || (address != "" && address != null) || (email != null && email != "")) {
            users = service.queryUserByNameAddrEmail(name, address, email);
            System.out.println(users);
        } else {
            users = service.FindAll();
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("list.jsp").forward(req, resp);
    }
}
