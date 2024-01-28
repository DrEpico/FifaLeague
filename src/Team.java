import java.util.ArrayList;

public class Team {
    String name;
    WinLoss winLoss = new WinLoss();

    ArrayList<Player> players;
    int budget = 50;

    public Team(String name){
        this.name = name;
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public WinLoss getWinLoss() {
        return winLoss;
    }
}


