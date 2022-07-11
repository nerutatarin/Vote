package service.configurations;

public class TelegramUser {
    private String telegramId;
    private String telegramUserName;
    private String title;
    private String phone;
    private int rule;

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

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "TelegramUser{" +
                "telegramId='" + telegramId + '\'' +
                ", telegramUserName='" + telegramUserName + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", rule=" + rule +
                '}';
    }
}
