package com.company;

import java.util.List;

public interface IGameMemory {
    boolean savePhrase(Player player, String msg);
    void saveLeadersBoard(List<Player> leadersBoard);
}
