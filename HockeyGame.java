//package KollelSportsProgram;

public class HockeyGame {
	// //////////Fields////////////
	public HockeyTeam team1;
	public HockeyTeam team2;

	// ///////Constructors/////////
	public HockeyGame() {
		team1 = null;
		team2 = null;
	}

	public HockeyGame(HockeyTeam t1, HockeyTeam t2) {
		team1 = t1;
		team2 = t2;
	}

	// ////////////Methods///////////
	public boolean playedAlready() {
		if (team1 == null || team2 == null)
			return false;

		if (team1.hTeamsPlayed.contains(team2.getName())
				|| team2.hTeamsPlayed.contains(team1.getName()))
			return true;
		else
			return false;
	}

	public boolean equals(HockeyGame game)
	// Precondition: both games are full (no nulls)
	{
		if ((game.team1.getName().equalsIgnoreCase(team1.getName()) && game.team2
				.getName().equalsIgnoreCase(team2.getName())) || // compare to
																	// see if
																	// the
				(game.team1.getName().equalsIgnoreCase(team2.getName()) && game.team2
						.getName().equalsIgnoreCase(team1.getName()))) // team
																		// names
																		// match
																		// up
			return true;
		else
			return false;
	}
}