public class Fixture {
    Club homeTeam;
    Club awayTeam;

    public Fixture(Club one, Club two){
        homeTeam = one;
        awayTeam = two;
    }

    public String getFixture(){
        return homeTeam.clubName + " vs " + awayTeam.clubName + "\n\n";
    }


}

