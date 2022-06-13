package service.configurations;

public class ClientConfig {
    private static final int DEFAULT_READ_TIMEOUT = 10000;
    private static final int DEFAULT_CONNECT_TIMEOUT = 10000;

    public ClientConfig() {
        super();
    }

    private String name;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int readTimeout = DEFAULT_READ_TIMEOUT;

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

    public String getName() {
        return name;
    }
}