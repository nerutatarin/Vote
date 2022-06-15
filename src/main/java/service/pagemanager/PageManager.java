package service.pagemanager;

import utils.ipaddress.model.IPAddress;

public interface PageManager {

    void votePage(String baseUrl);

    void voteInput();

    void voteButton();

    void voteLogging(IPAddress IPAddress);
}
