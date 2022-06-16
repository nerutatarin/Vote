package service.telegrambot.keyboards;

public enum ButtonNameEnum {
    GET_STATUS_SERVER("Статус сервера"),
    GET_PARTICIPANTS("Участники голосования"),
    GET_RESULTS_VOTE("Результаты голосования");

    private final String buttonName;

    ButtonNameEnum(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getButtonName() {
        return buttonName;
    }
}