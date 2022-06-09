package utils.retrofit;

import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@FunctionalInterface
interface ErrorMessageExtractor {

    @Nullable
    String extract(@NotNull ResponseBody errorBody) throws IOException;

}

