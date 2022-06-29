package service.proxy;

public interface Proxy {
    org.openqa.selenium.Proxy getProxy(String browserName);
}
