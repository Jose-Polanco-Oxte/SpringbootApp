package jpolanco.springbootapp.shared.application;

public interface DtoCreator<P> {
    Dto create(P payload);
}
