package com.company;

import java.util.*;

public class GameMemorySettings implements IGameMemory {

    private final Map<Player, Set<String>> sentences = new HashMap<Player, Set<String>>();

    private final List<Player> leadersBoard = new ArrayList<Player>(5);

    @Override
    public boolean savePhrase(Player player, String msg) {
        return this.sentences.computeIfAbsent(player, o -> new HashSet<String>()).add(msg);
    }

    @Override
    public void saveLeadersBoard(List<Player> leaderBoard) {
        this.leadersBoard.clear();
        this.leadersBoard.addAll(leaderBoard);
    }

    public List<Player> getLeadersBoard() { return this.leadersBoard; }
}
