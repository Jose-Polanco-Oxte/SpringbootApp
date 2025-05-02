package jpolanco.springbootapp.shared.domain;

import java.util.function.Function;

public record ResultBuilder<T>(Result<T> result) {

    public static <T> ResultBuilder<T> from(Result<T> result) {
        return new ResultBuilder<>(result);
    }


    public <U> ResultBuilder<U> then(Function<T, Result<U>> function) {
        return new ResultBuilder<>(Result.bind(result, function));
    }
}
