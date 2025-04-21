package jpolanco.springbootapp.Shared.Application;

public interface MappeableCommand<T> extends Command<T> {
    T toDomain();
}
