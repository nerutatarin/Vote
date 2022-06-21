package service.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class RouterFactory {

    private static final Map<Boolean, Handler> routerFactory = new HashMap<>();

    static {
        routerFactory.put(true, new CallBackHandler());
        routerFactory.put(false, new MessageHandler());
    }

    public static Handler getInstance(Update update) {
        return routerFactory.get(isCallBackQuery(update));
    }

    private static boolean isCallBackQuery(Update update) {
        return update.hasCallbackQuery();
    }
}