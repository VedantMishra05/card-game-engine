package logic.events.eventTypes;

import logic.events.GameEvent;

public record RoundStartedEvent(int roundNumber) implements GameEvent{
    
} 
