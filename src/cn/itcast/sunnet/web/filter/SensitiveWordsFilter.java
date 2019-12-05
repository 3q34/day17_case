package cn.itcast.sunnet.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdx on 2019/12/4.
 * desc:
 */
@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }
                if (method.getName().equals("getParameterMap")) {
                    Map<String, String[]> map = new HashMap( (Map<String, String[]>) method.invoke(req, args));
                    if (map != null) {
                        for (String key : map.keySet()) {
                            String[] values = map.get(key);

                            for (String str : list) {
                                if (values[0].contains(str)) {
                                    values[0] = values[0].replaceAll(str, "***");
                                    map.put(key, values);
                                }
                            }

                        }

                    }
                    return map;
                }
                return method.invoke(req, args);

            }
        });
        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<>();

    public void init(FilterConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
