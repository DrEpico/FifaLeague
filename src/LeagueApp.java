import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;
import java.sql.*;
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

    public static void main(String[] args) throws SQLException {
        String sql = "SELECT league_name FROM leagues WHERE league_id = 4;";
        String url = "jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=EAFC24;trustServerCertificate=true";
        String username = "sql";
        String password = "0000";


        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(sql);
        rs.next();
        String name = rs.getNString(1);

        System.out.println(name);

        con.close();

    }



}