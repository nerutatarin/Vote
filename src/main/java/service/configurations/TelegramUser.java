package service.configurations;

public class TelegramUser {
    private String title;
    private String phone;
    private String telegramId;
    private String telegramUserName;
    private String telegramFirstName;
    private int rule;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }

    public String getTelegramFirstName() {
        return telegramFirstName;
    }

    public void setTelegramFirstName(String telegramFirstName) {
        this.telegramFirstName = telegramFirstName;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "{" +
                "title:'" + title + '\'' +
                ", phone:'" + phone + '\'' +
                ", telegramId:'" + telegramId + '\'' +
                ", telegramUserName:'" + telegramUserName + '\'' +
                ", telegramFirstName:'" + telegramFirstName + '\'' +
                ", rule:" + rule +
                '}';
    }
}
