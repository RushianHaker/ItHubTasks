package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private static final Game INSTANCE = new Game();

    public static final int LEADERBOARD_SIZE = 5;

    private final IGameMemory memory = new GameMemorySettings();

    public final IWriterGamesInfo gameInfo = new WriterGamesInfo("Game Info:");

    private List<Player> players;

    public Game() {
        this.players = new ArrayList<Player>();

        gameInfo.info("Создана комната с игрой -'Полиндром'- ");
    }

    private void updateLeaderBoard() {
        this.memory.saveLeadersBoard(this.players.stream().sorted((a, b) -> {
            return Integer.compare(b.getScore(), a.getScore());
        }).limit(LEADERBOARD_SIZE).collect(Collectors.toList()));
    }


    public Player loginGame(String playerName) {
        Player player = new Player(playerName);
        this.players.add(player);
        this.updateLeaderBoard();
        gameInfo.info(String.format("%s вошел в игру ...", playerName));
        return player;
    }

    private boolean checkSentence(String text) {
        return StringSettings.isPalindrome(text);
    }


    public AnswerStatus updateSentence(Player player, String text) {
        if (this.checkSentence(text)) {
            if (this.memory.savePhrase(player, StringSettings.standardizationText(text))) {
                final int points = text.length();
                player.updatePoints(points);
                this.updateLeaderBoard();
                gameInfo.info(String.format("Игрок %s заработал %d очков за полиндромное слово/фразу.", player.getName(), points));
                return AnswerStatus.SUCCESS;
            }
            return AnswerStatus.ALREADY_USED;
        }
        return AnswerStatus.NOT_PALINDROME;
    }

    public static Game getInstance() {
        return INSTANCE;
    }

    public IGameMemory getMemory() {
        return this.memory;
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
