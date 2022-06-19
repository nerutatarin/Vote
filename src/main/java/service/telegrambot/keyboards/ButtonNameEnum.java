package service.telegrambot.keyboards;

public enum ButtonNameEnum {
    GET_STATUS("Статус сервера"),
    GET_MEMBERS("Участники голосования"),
    GET_RESULT("Результат голосования"),
    GET_VOTE("Проголосовать(дефолт)"),
    GET_NOMINATIONS("Список номинаций");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}