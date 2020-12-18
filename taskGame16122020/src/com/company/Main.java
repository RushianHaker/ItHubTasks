package com.company;

public class Main {

    public static void main(String[] args) {
        Game game = Game.getInstance();
        Player player1 = game.loginGame("God_666");
        player1.submitPhrase("потоп");

        Player player2 = game.loginGame("Devil_1");
        player2.submitPhrase("дохоД");
        player2.submitPhrase("а роза упала на лапу Азора");

        System.out.println(((GameMemorySettings)game.getMemory()).getLeadersBoard());
    }
}
