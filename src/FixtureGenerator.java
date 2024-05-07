import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FixtureGenerator {
    private String password = "0000";
    public ArrayList<Fixture> fixtures = new ArrayList<>();
    public ArrayList<Club> clubs = new ArrayList<>();

    public FixtureGenerator() throws SQLException {
        printSeasonFixtures(generateFixtures(initClubs()));
    }

    /**
     * Identify clubs that have manager ID assigned, get their club ID's,
     * club names, and manager ID assigned to them to create an array list of Club objects.
     */
    private ArrayList<Club> initClubs() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(LeagueApp.url, LeagueApp.username, password);
            con.setAutoCommit(false); // Start transaction
            String getClubsSQL = "SELECT c.club_id, c.club_name, m.manager_id, m.origin_id, m.name FROM managers as m JOIN clubs as c ON m.manager_id = c.manager_id;"; // Modify this with your SQL query to retrieve clubs data
            try (PreparedStatement statement = con.prepareStatement(getClubsSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int clubId = resultSet.getInt("club_id"); // Modify this with the column name for club ID
                    String clubName = resultSet.getString("club_name"); // Modify this with the column name for club name
                    int managerId = resultSet.getInt("manager_id"); // Modify this with the column name for manager ID
                    String originId = resultSet.getString("origin_id");
                    String managerName = resultSet.getString("name");

                    // Create Club object and add it to the clubs ArrayList
                    clubs.add(new Club(clubId, clubName, managerId, originId, managerName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Reset auto-commit mode!
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close database connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        printClubs();
        return clubs;
    }

    /**
     * Print all elements of the clubs ArrayList.
     */
    private void printClubs() {
        System.out.println("Printing all elements of the clubs ArrayList:");
        System.out.println("Club Name\t\tManager Name");
        System.out.println("-------------------------------------------");

        for (Club club : clubs) {
            System.out.printf("%-15s\t\t%-15s\n", club.clubName, club.managerName);
        }
    }

    public static List<List<Fixture>> generateFixtures(ArrayList<Club> clubs){
        List<List<Fixture>> seasonFixtures = new ArrayList<>();
        int totalTeams = clubs.size();
        int totalWeeks = totalTeams * 2 - 2;

        for (int i = 0; i < 2; i++) {
            generateHalfSeasonFixtures(clubs, totalTeams, totalWeeks, seasonFixtures);
        }

        return seasonFixtures;
    }

    private static void generateHalfSeasonFixtures(
            ArrayList<Club> clubs, int totalTeams, int totalWeeks, List<List<Fixture>> seasonFixtures) {
        for(int week = 0; week < totalWeeks; week++) {
            List<Fixture> weekFixtures = new ArrayList<>();
            for(int match = 0; match < totalTeams/2; match++){
                Fixture fixture = new Fixture(clubs.get(match), clubs.get(9 - match));
                weekFixtures.add(fixture);
            }
            Collections.rotate(clubs.subList(1, clubs.size()), 1); //Rotate teams for next week
            seasonFixtures.add(weekFixtures);
        }
    }

    public static void printSeasonFixtures(List<List<Fixture>> seasonFixtures) {
        System.out.println("Printing all fixtures of the season:");
        System.out.println(seasonFixtures.size());
        System.out.println("--------------------------------------------");
        for (int week = 0; week < seasonFixtures.size(); week++) {
            System.out.println("Week " + (week + 1) + ": ");
            List<Fixture> weekFixtures = seasonFixtures.get(week);
            for (Fixture fixture : weekFixtures) {
                System.out.println(fixture.homeTeam.clubName + " vs " + fixture.awayTeam.clubName);
            }
            System.out.println();
        }
    }
}
