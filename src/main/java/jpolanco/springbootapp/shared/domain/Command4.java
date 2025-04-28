package jpolanco.springbootapp.shared.domain;

import jpolanco.springbootapp.shared.application.Result;

public interface Command4<T> {
    Result<T> execute();
}