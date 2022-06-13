package votes.kp.model;

import java.util.List;

public class SetCookieKP {

    private List<CookieKP> cookieKPS;

    public List<CookieKP> getCookieKPS() {
        return cookieKPS;
    }

    public void setCookieKPS(List<CookieKP> cookieKPS) {
        this.cookieKPS = cookieKPS;
    }

    @Override
    public String toString() {
        return "SetCookieKP{" +
                "cookieKPS=" + cookieKPS +
                '}';
    }
}
