package util;

import javax.servlet.http.Cookie;

public class CookieUtil {
    /**
     * 从一个cookie数组中找到我们想要的cookie对象
     *
     * @param cookies
     * @param name
     * @return
     */
    public static Cookie findCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}

