package utils.configurations;

public class ClientConfig {
    private static final int DEFAULT_READ_TIMEOUT = 10000;
    private static final int DEFAULT_CONNECT_TIMEOUT = 10000;
    public static final String AUTH_BASIC = "BASIC";
    public static final String AUTH_TOKEN = "TOKEN";

    public ClientConfig() {
        super();
    }

    private static final String CONFIG_HTTP_LOG_LEVEL = "http.logLevel";
    private static final String CONFIG_HTTP_READ_TIMEOUT = "http.readTimeout";
    private static final String CONFIG_HTTP_CONNECT_TIMEOUT = "http.connectTimeout";

    private String name;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;
    private String userName = null;
    private String auth = null;
    private String authHeaderName = null;
    private String password = null;
    private String token = null;

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout( int connectTimeout )
    {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout()
    {
        return readTimeout;
    }

    public void setReadTimeout( int readTimeout )
    {
        this.readTimeout = readTimeout;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getAuth()
    {
        return auth;
    }

    public void setAuth( String auth )
    {
        this.auth = auth;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getAuthHeaderName() {
        return authHeaderName;
    }
}
