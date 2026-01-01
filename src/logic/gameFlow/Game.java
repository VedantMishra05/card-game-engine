package logic.gameFlow;

import java.util.List;

import engine.Card;
import engine.Deck;
import engine.Player;
import logic.GameState;
import logic.actions.ActionExecutor;
import logic.actions.actionTypes.PlayCardAction;
import logic.config.GameConfig;
import logic.config.PlayMode;
import logic.events.EventBus;
import logic.events.eventTypes.GameEndedEvent;
import logic.events.eventTypes.GameStartedEvent;
import logic.events.eventTypes.RoundEndedEvent;
import logic.gameRules.RuleResolver;

public class Game {

    private final List<Player> players;
    private final Deck deck;
    private final GameConfig config;
    private final EventBus eventBus;

    private final RuleResolver ruleResolver = new RuleResolver();
    private final ActionExecutor actionExecutor;

    private GameState state = GameState.SETUP;

    private int roundNumber = 1;
    private int currentTurnIndex = 0;
    private Round currentRound;

    public Game(List<Player> players, int shuffleCount, GameConfig config, EventBus eventBus) {
        this.players = players;
        this.deck = new Deck(shuffleCount);
        this.config = config;
        this.eventBus = eventBus;
        this.actionExecutor = new ActionExecutor(this);

        registerListners();
    }

    private void registerListners() {
        eventBus.subscribe(RoundEndedEvent.class, event -> {
            if(state == GameState.IN_PROGRESS) {
                startNextRound();
            }
        });
    }

    public void start() {
        if(state != GameState.SETUP) throw new IllegalStateException("Game already started !!");

        state = GameState.IN_PROGRESS;
        eventBus.publish(new GameStartedEvent());

        startNextRound();
    }

    private void startNextRound() {
        if(!canPlayRound()) {
            endGame();
            return;
        }

        int cardsPerRound = config.getCardsPerRound();
        for(int i = 0; i < cardsPerRound; i++) {
            for(Player player: players) {
                Card drawn = deck.draw();
                player.receiveCard(drawn);
            }
        }

        currentRound = new Round(roundNumber++, players, ruleResolver.resolve(config), eventBus);
        currentTurnIndex = 0;
        currentRound.start();

        if(config.getPlayMode() == PlayMode.AUTO) autoPlayROund();
    }

    private void autoPlayROund() {
        for(Player player: players) {
            Card card = player.showHands().get(0);
            
            actionExecutor.execute(new PlayCardAction(player, card));
        }
    }

    private boolean canPlayRound() {
        return deck.remainingCards() >= players.size();
    }
    
    private void endGame() {
        state = GameState.FINISHED;

        int highestScore = players.stream().mapToInt(Player::getScore).max().orElse(0);
        List<Player> finalWinners = players.stream().filter(p -> p.getScore() == highestScore).toList();

        eventBus.publish(new GameEndedEvent(finalWinners));
    }

    public Round getRound() { return currentRound; }
    public ActionExecutor getActionExecutor() { return actionExecutor; }
    public Player getCurrentTurnPlayer() { return players.get(currentTurnIndex); }
    public void advanceTurn() {
        currentTurnIndex = (currentTurnIndex + 1) % players.size(); // shayad formula galat ho jaaye..
    }
}
