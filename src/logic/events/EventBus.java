package logic.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {

    private final Map<Class < ? extends GameEvent>, List<EventListner<?>>> listners = new HashMap<>();

    public <T extends GameEvent> void subscribe(Class<T> eventType, EventListner<T> listner) {
        listners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listner);
    }

    @SuppressWarnings("unchecked")
    public <T extends GameEvent> void publish(T event) {
        List<EventListner<?>> eventListners = listners.get(event.getClass());
        if(eventListners == null) return;

        for(EventListner<?> listner: eventListners) {
            ((EventListner<T>) listner).onEvent(event);
        }
    }
}