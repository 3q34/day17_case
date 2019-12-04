package cn.itcast.sunnet.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by cdx on 2019/12/4.
 * desc:
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        //1、判断是否是登录相关的资源
        //是，则登录判断
        //否则直接放行
        if (requestURI.contains("/login.jsp") ||
                requestURI.contains("/loginServlet") ||
                requestURI.contains("/css/") ||
                requestURI.contains("/js/") ||
                requestURI.contains("/fonts/") ||
                requestURI.contains("/checkCodeServlet")) {
            chain.doFilter(req, resp);
        } else {

            HttpSession session = request.getSession();

            //2、登录判断
            //登录则放行
            //否则跳转到登录页面
            if (session != null && session.getAttribute("loginuser") != null) {
                chain.doFilter(req, resp);
            } else {
                req.setAttribute("LoginMsg", "您尚未登录，请登录！");
                //((HttpServletResponse) resp).sendRedirect(request.getContextPath() + "/login.jsp");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
