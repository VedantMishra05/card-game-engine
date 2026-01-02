package logic.gameRules.War;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import engine.Card;
import engine.Player;

public class WarContext {
    private final Map<Player, List<Card>> warPile = new HashMap<>();

    public void addCard(Player player, Card card) {
        warPile.computeIfAbsent(player, k -> new ArrayList<>()).add(card);
    }

    public List<Card> getCards(Player player) {
        return warPile.getOrDefault(player, List.of());
    }

    public Set<Player> getParticipants() {
        return warPile.keySet();
    }
}
