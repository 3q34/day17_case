package cn.itcast.sunnet.web.servlet;

import cn.itcast.sunnet.domian.User;
import cn.itcast.sunnet.service.UserService;
import cn.itcast.sunnet.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cdx on 2019/11/26.
 * desc:
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String verifycode = req.getParameter("verifycode");

        HttpSession session = req.getSession();
        String code = session.getAttribute("code").toString();

        session.removeAttribute("code");
        if (code.equalsIgnoreCase(verifycode)) {
            User loginuser = new User();

            UserService UserService = new UserServiceImpl();

            loginuser = UserService.findUserByUsernameAndPassword(username, password);
            if (loginuser != null) {
                //resp.setContentType("text/html;charset=utf-8");
                session.setAttribute("loginuser", loginuser);
                System.out.println(loginuser);
                //resp.sendRedirect("/list.jsp");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                req.setAttribute("LoginMsg", "用户名密码错误，请重新输入");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }

        } else {

        }
//        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().write(username + password + verifycode + code);

    }

}
