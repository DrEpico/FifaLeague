public class Fixture {
    Club homeTeam;
    Club awayTeam;

    public Fixture(Club one, Club two){
        homeTeam = one;
        awayTeam = two;
    }

    public String GetFixture(){
        return homeTeam.managerName + " vs " + awayTeam.managerName + "\n\n";
    }


}

