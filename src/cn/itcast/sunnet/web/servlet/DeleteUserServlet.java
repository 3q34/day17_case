package cn.itcast.sunnet.web.servlet;

import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.service.UserService;
import cn.itcast.sunnet.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by cdx on 2019/11/27.
 * desc:
 */
@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));

        UserService service = new UserServiceImpl();
        int count = service.delete(id);
        if (count > 0) {
            //弹窗成功
            resp.sendRedirect(req.getContextPath() + "/findUserByPageServlet");
            //req.getRequestDispatcher("/findUserByPageServlet").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/findUserByPageServlet");
        }
    }
}
