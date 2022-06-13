package vote.vote2022.kp.model;

import com.google.gson.annotations.Expose;

public class SetCookieKP {

    @Expose(serialize = true, deserialize = true)
    private CookieKP[] cookieKPS;

    public CookieKP[] getCookieKPS() {
        return cookieKPS;
    }

    public void setCookieKPS(CookieKP[] cookieKPS) {
        this.cookieKPS = cookieKPS;
    }
}
