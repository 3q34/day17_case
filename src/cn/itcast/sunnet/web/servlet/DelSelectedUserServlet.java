package cn.itcast.sunnet.web.servlet;

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
 * Created by cdx on 2019/11/28.
 * desc:
 */
@WebServlet("/delSelectedUserServlet")
public class DelSelectedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String[] ids = req.getParameterValues("uid");
        UserService service = new UserServiceImpl();
        for (String id : ids) {
            service.delete(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/userListServlet");
    }
}
