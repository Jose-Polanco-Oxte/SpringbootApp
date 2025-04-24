package jpolanco.springbootapp.shared.application;

import java.util.function.Function;

public class ResultBuilder<T> {
    private final Result<T> result;

    public ResultBuilder(Result<T> result) {
        this.result = result;
    }

    public static <T> ResultBuilder<T> from(Result<T> result) {
        return new ResultBuilder<>(result);
    }


    public <U> ResultBuilder<U> then(Function<T, Result<U>> function) {
        return new ResultBuilder<>(Result.bind(result, function));
    }

    public Result<T> result() {
        return result;
    }
}
