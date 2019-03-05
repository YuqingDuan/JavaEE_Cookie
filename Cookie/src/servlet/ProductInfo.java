package servlet;

import util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 添加cookie
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取到当前用户准备浏览的ID
        String id = request.getParameter("id");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtil.findCookie(cookies, "history");

        if (cookie == null) {
            Cookie c = new Cookie("history", id);
            c.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(c);
        } else {
            String ids = cookie.getValue();
            cookie.setValue(id + "#" + ids);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        }
        response.sendRedirect("product_info.htm");
    }
}
