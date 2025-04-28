package jpolanco.springbootapp.shared.domain;

import jpolanco.springbootapp.shared.application.Result;

public interface Command<R, A> {
    Result<R> execute(A args);
}
