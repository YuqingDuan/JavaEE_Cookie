package servlet;

import util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LastVisitedTime extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("un");
        String password = request.getParameter("pw");
        System.out.println(username + password);

        if ("admin".equals(username) && "123".equals(password)) {
            // 获取请求对象中的所有cookie
            Cookie[] cookies = request.getCookies();
            // 找到我们想要的cookie
            Cookie cookie = CookieUtil.findCookie(cookies, "LastVisitedTime");
            // 是第一次登陆, 没有cookie
            if (cookie == null) {
                // 添加cookie
                Cookie c = new Cookie("LastVisitedTime", System.currentTimeMillis() + "");
                c.setMaxAge(60 * 60);
                response.addCookie(c);
            } else {
                // 第二次登陆, 有cookie, 取到LastVisited = ?的?
                long lastVisitedTime = Long.parseLong(cookie.getValue());
                // 输出到界面
                response.getWriter().write("Welcome: " + username + ", your last visit time is: " + new Date(lastVisitedTime));
                // 更新最后登录时间
                cookie.setValue(System.currentTimeMillis() + "");
            }
        } else {
            response.getWriter().write("fail to login!");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
