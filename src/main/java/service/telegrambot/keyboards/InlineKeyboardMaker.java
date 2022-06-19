package service.telegrambot.keyboards;

import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static service.telegrambot.commands.CommandsEnum.*;
import static service.telegrambot.keyboards.ButtonNameEnum.*;

/**
 * Инлайн кнопки
 * @return InlineKeyboardMarkup
 */
public class InlineKeyboardMaker {

    @NotNull
    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonStatus = buildButton(GET_STATUS.getButtonName(), COMMAND_STATUS.getValue());
        InlineKeyboardButton buttonVote = buildButton(GET_VOTE.getButtonName(), COMMAND_VOTE.getValue());
        InlineKeyboardButton buttonMembers = buildButton(GET_MEMBERS.getButtonName(), COMMAND_MEMBERS.getValue());
        InlineKeyboardButton buttonNominations = buildButton(GET_NOMINATIONS.getButtonName(), COMMAND_NOMINATIONS.getValue());
        InlineKeyboardButton buttonResults = buildButton(GET_RESULT.getButtonName(), COMMAND_RESULT.getValue());

        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(buttonStatus);
        firstRow.add(buttonVote);

        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        secondRow.add(buttonMembers);
        secondRow.add(buttonNominations);

        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        secondRow.add(buttonResults);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstRow);
        rowsInline.add(secondRow);
        rowsInline.add(thirdRow);

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
