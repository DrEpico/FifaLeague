import java.sql.*;
import java.util.ArrayList;

public class FixtureGenerator {
    private String password = "0000";
    public ArrayList<Fixture> fixtures = new ArrayList<>();
    public ArrayList<Club> clubs = new ArrayList<>();

    public FixtureGenerator() throws SQLException {
        //get teams from SQL server
        generateFixtures(initClubs());

        printClubs();
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

    private ArrayList<Fixture> generateFixtures(ArrayList<Club> clubs){

        return fixtures;
    }


}
