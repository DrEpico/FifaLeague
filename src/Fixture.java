public class Fixture {
    Club homeTeam;
    Club awayTeam;

    //todo: an object might be needed to handle match performances and etc.

    public Fixture(Club one, Club two){
        homeTeam = one;
        awayTeam = two;
    }

    public String getFixture(){
        return homeTeam.clubName + " vs " + awayTeam.clubName + "\n\n";
    }


}

