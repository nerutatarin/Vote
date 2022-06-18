package service.pagemanager.model;

import org.openqa.selenium.Cookie;

import java.util.Date;

public class MyCookie extends Cookie {

    public MyCookie(String name, String value, String path, Date expiry) {
        super(name, value, path, expiry);
    }

    public MyCookie(String name, String value, String domain, String path, Date expiry) {
        super(name, value, domain, path, expiry);
    }

    public MyCookie(String name, String value, String domain, String path, Date expiry, boolean isSecure) {
        super(name, value, domain, path, expiry, isSecure);
    }

    public MyCookie(String name, String value, String domain, String path, Date expiry, boolean isSecure, boolean isHttpOnly) {
        super(name, value, domain, path, expiry, isSecure, isHttpOnly);
    }

    public MyCookie(String name, String value, String domain, String path, Date expiry, boolean isSecure, boolean isHttpOnly, String sameSite) {
        super(name, value, domain, path, expiry, isSecure, isHttpOnly, sameSite);
    }

    public MyCookie(String name, String value) {
        super(name, value);
    }

    public MyCookie(String name, String value, String path) {
        super(name, value, path);
    }

/*    public MyCookie(CookieKP[] cookies) {

        for (CookieKP cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            String domain = cookie.getDomain();
            String path = cookie.getPath();
            Date expiry = cookie.getExpiry();
            boolean secure = cookie.isSecure();
            boolean httpOnly = cookie.isHttpOnly();
            String sameSite = cookie.getSameSite();

            super(name,value,domain,path,expiry,secure,httpOnly,sameSite);
        }
    }*/
}
