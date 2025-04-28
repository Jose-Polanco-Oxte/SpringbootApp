package jpolanco.springbootapp.shared.domain;

import jpolanco.springbootapp.shared.application.Result;

public interface CommandVoid<R> {
    Result<R> execute();
}