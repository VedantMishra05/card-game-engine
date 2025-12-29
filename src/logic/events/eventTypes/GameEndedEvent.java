package logic.events.eventTypes;

import java.util.List;

import engine.Player;
import logic.events.GameEvent;

public record GameEndedEvent(List<Player> finalWinnerList) implements GameEvent {
    
}
