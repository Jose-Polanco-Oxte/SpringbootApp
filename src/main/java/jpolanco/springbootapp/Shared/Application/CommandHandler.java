package jpolanco.springbootapp.Shared.Application;

public interface CommandHandler <TCommand extends Command<TResult>, TResult> {
    TResult handle(TCommand command);
}