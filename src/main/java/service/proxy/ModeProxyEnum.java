package service.proxy;

public enum ModeProxyEnum {

    NO_PROXY("noProxy"),
    TOR_PROXY("torProxy"),
    WEB_PROXY("webProxy");

    private final String value;

    ModeProxyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
