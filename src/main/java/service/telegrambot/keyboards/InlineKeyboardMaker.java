package service.telegrambot.keyboards;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static service.telegrambot.keyboards.ButtonNameEnum.*;

/**
 * Инлайн кнопки
 * @return InlineKeyboardMarkup
 */
public class InlineKeyboardMaker {

    @NotNull
    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonStatus = buildButton(GET_STATUS_SERVER.getButtonName(), "/status");
        InlineKeyboardButton buttonParticipants = buildButton(GET_PARTICIPANTS.getButtonName(), "/participants");
        InlineKeyboardButton buttonResults = buildButton(GET_RESULTS_VOTE.getButtonName(), "/resultsvote");

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(buttonStatus);
        firstRow.add(buttonParticipants);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(buttonResults);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstRow);
        rowsInline.add(secondRow);

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }
    @NotNull
    private InlineKeyboardButton buildButton(String title, String callbackData) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(title);
        inlineKeyboardButton.setCallbackData(callbackData);
        return inlineKeyboardButton;
    }
}
