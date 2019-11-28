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


/**
 * Created by cdx on 2019/11/27.
 * desc:
 */
@WebServlet("/getUserServlet")
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        UserService service = new UserServiceImpl();
        User user = service.getUserById(id);
        System.out.println(user
        );
        req.setAttribute("user", user);
        req.getRequestDispatcher("/update.jsp").forward(req, resp);

    }
}
