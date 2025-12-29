package logic.events;

public interface EventListner<T extends GameEvent> {
    void onEvent(T event);
}
