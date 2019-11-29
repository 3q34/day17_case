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
        int currentPage = 0;
        int pageSize = 0;
        if (currentPage1 == null || "".equals(currentPage1)) {
            currentPage = 1;
            pageSize = 7;
        } else {
            currentPage = Integer.parseInt(currentPage1.toString());
            pageSize = Integer.parseInt(pageSize1.toString());
        }
        UserService service = new UserServiceImpl();
        int totalCount = service.getCount();
        int totalPage = totalCount % pageSize == 0 ? totalCount /pageSize : (totalCount / pageSize) + 1;
        PageBean<User> pageBean = service.findUserByPage(currentPage, pageSize);
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        req.setAttribute("pageBean", pageBean);
        resp.setContentType("text/html;charset=utf-8");
        //resp.sendRedirect(req.getContextPath()+"/list.jsp");
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }
}
