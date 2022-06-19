package service.telegrambot.keyboards;

public enum ButtonNameEnum {
    GET_STATUS("Статус сервера"),
    GET_MEMBERS("Участники голосования"),
    GET_RESULT("Результат голосования");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}