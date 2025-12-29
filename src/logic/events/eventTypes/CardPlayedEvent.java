package logic.events.eventTypes;

import engine.Card;
import engine.Player;
import logic.events.GameEvent;

public record CardPlayedEvent(Player player, Card card) implements GameEvent {
    
}
