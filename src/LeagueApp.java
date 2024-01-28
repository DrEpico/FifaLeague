import java.util.ArrayList;

/**
 * Main class
 */
public class LeagueApp {
    int season = 1;
    ArrayList<Team> teams = new ArrayList<>();

    public void progressSeason(){
        season++;
        resetSeasonData();
        addPlayerAge();
    }


    //todo: player upgreade based on position


    public void resetSeasonData(){
        for(Team team : teams){
            team.winLoss.setSeasonWins(0);
            team.winLoss.setSeasonDraws(0);
            team.winLoss.setSeasonLosses(0);
            team.winLoss.setSeasonMatches(0);
            for(Player player : team.players){
                player.setSeasonGoals(0);
                player.setSeasonAssists(0);
                player.setSeasonCleansheets(0);
            }
        }
    }

    public void addPlayerAge(){
        for(Team team : teams){
            for(Player player : team.players){
                player.addAge();
            }
        }
    }

//    public static void main(String[] args) {
//
//    }
}