package cn.itcast.sunnet.web.servlet;

import cn.itcast.sunnet.domian.PageBean;
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
 * Created by cdx on 2019/11/28.
 * desc:
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Object currentPage1 = req.getParameter("currentPage");
        Object pageSize1 = req.getParameter("pageSize");

        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
        UserService service = new UserServiceImpl();
        int currentPage = 0;
        int pageSize = 0;
        if (currentPage1 == null || "".equals(currentPage1)) {
            currentPage = 1;
            pageSize = 7;
        } else {
            currentPage = Integer.parseInt(currentPage1.toString());
            pageSize = Integer.parseInt(pageSize1.toString());
        }
        PageBean<User> pageBean =null;


        if ((name != "" && name != null) || (address != "" && address != null) || (email != null && email != "")) {

            pageBean = service.findUserByPage(name,address,email,currentPage, pageSize);
        }
        else{

            pageBean = service.findUserByPage(name,address,email,currentPage, pageSize);
        }

        req.setAttribute("pageBean", pageBean);
        resp.setContentType("text/html;charset=utf-8");
        //resp.sendRedirect(req.getContextPath()+"/list.jsp");
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
