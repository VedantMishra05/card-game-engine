package logic.events.eventTypes;

import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Player;
import logic.events.GameEvent;

public record RoundEndedEvent(Map<Player, Card> playedCards, List<Player> winners) implements GameEvent{

}