//package KollelSportsProgram;

public class BallGame {
    // //////////////Fields/////////////////
    public BallTeam team1;
    public BallTeam team2;

    // //////////////Constructors//////////////
    public BallGame() {
        team1 = null;
        ;
        team2 = null;
        ;
    }

    public BallGame(BallTeam t1, BallTeam t2) {
        team1 = t1;
        team2 = t2;
    }

    // ///////////Methods///////////////
    public boolean playedAlready() {
        if (team1 == null || team2 == null)
            return false;

        if (team1.bTeamsPlayed.contains(team2.getName())
        || team2.bTeamsPlayed.contains(team1.getName()))
            return true;
        else
            return false;
    }

    public boolean equals(BallGame game)
    // Precondition: both games are full (no nulls)
    {
        if ((game.team1.getName() == team1.getName() && game.team2.getName() == team2
            .getName()) || // compare to see if the team names match up
        (game.team1.getName() == team2.getName() && game.team2
            .getName() == team1.getName()))
            return true;
        else
            return false;
    }
}
