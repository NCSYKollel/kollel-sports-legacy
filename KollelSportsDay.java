//package KollelSportsProgram;

import java.util.*;

public class KollelSportsDay {
	// ///////Fields//////////
	BallGame[][] bGames;
	HockeyGame[][] hGames;

	// //////Constructor///////
	public KollelSportsDay(int x, int y, int z, int h, int i, int j) {
		if (z > 0)// if there are three periods (which is the standard amount)
		{
			bGames = new BallGame[3][];
			bGames[0] = new BallGame[x];
			bGames[1] = new BallGame[y];
			bGames[2] = new BallGame[z];
			hGames = new HockeyGame[3][];
			hGames[0] = new HockeyGame[h];
			hGames[1] = new HockeyGame[i];
			hGames[2] = new HockeyGame[j];
		} 
		else if (y > 0)// if there are only two periods
		{
			bGames = new BallGame[2][];
			bGames[0] = new BallGame[x];
			bGames[1] = new BallGame[y];
			hGames = new HockeyGame[2][];
			hGames[0] = new HockeyGame[h];
			hGames[1] = new HockeyGame[i];
		} 
		else// if there is only one period
		{
			bGames = new BallGame[1][x];
			hGames = new HockeyGame[1][h];
		}

		// initialize all the games
		for (int q = 0; q < bGames.length; q++) {
			for(int p = 0; p < hGames[q].length; p++){
				hGames[q][p] = new HockeyGame();
			}
			for (int w = 0; w < bGames[q].length; w++) {
				bGames[q][w] = new BallGame();
			}
		}
	}

	// ///////Methods//////////
	public boolean playedAlready() {
		// check the basketball games
		for (int x = 0; x < bGames.length; x++) {
			for (int y = 0; y < bGames[x].length; y++) {
				if (bGames[x][y].playedAlready())
					return true;
			}
		}
		// check the hockey games
		for (int q = 0; q < hGames.length; q++) {
			for (int b = 0; b < hGames[q].length; b++){
				if (hGames[q][b].playedAlready())
					return true;
			}
		}

		// if it's made it to the end, there are no already used matchups in
		// either sport, so return false
		return false;
	}

	public boolean conflict() {
		// the check will be performed period by period, going through the
		// hockey games and cross-checking the teams with the basketball games
		for (int x = 0; x < hGames.length; x++) 	// cycle through every hockey
			for (int w = 0; w < hGames[x].length; w++)	// game...
			{
				for (int y = 0; y < bGames[x].length; y++) // ...and every
					// basketball game
				{
					// checking for conflicts shouldn't happen if the team is still
					// undetermined, and we therefore have to divide the checks
					// depending on what's null
					if (hGames[x][w].team1 != null)// within hockey team 1
					{
						if (bGames[x][y].team1 != null) {
							if (hGames[x][w].team1.bTeams.contains(bGames[x][y].team1
									.getName()))
								return true;
						}
						if (bGames[x][y].team2 != null) {
							if (hGames[x][w].team1.bTeams.contains(bGames[x][y].team2
									.getName()))
								return true;
						}
					}
					if (hGames[x][w].team2 != null)// within hockey team 2
					{
						if (bGames[x][y].team1 != null) {
							if (hGames[x][w].team2.bTeams.contains(bGames[x][y].team1
									.getName()))
								return true;
						}
						if (bGames[x][y].team2 != null) {
							if (hGames[x][w].team2.bTeams.contains(bGames[x][y].team2
									.getName()))
								return true;
						}
					}
				}
			}

		// if it's come this far, there's no conflict, so return false:
		return false;
	}

	public boolean everythingIsOK() {
		if (!playedAlready() && !conflict())
			return true;
		else
			return false;
	}

	protected boolean spotIsEmpty(int i, int j, int team) {
		if (team == 0)
			return (bGames[i][j].team1 == null);
		else
			return (bGames[i][j].team2 == null);
	}

	protected boolean hSpotIsEmpty(int i, int j, int team) {
		if (team == 0)
			return (hGames[i][j].team1 == null);
		else
			return (hGames[i][j].team2 == null);
	}

	protected boolean finished() {
		for (int x = 0; x < bGames.length; x++) {
			for (int w = 0; w < hGames[x].length; w++){
				if (hGames[x][w].team1 == null || hGames[x][w].team2 == null)// check the
					// hockey
					// games; if
					// one is
					// null
					// return
					// false
					return false;
			}
			for (int y = 0; y < bGames[x].length; y++)
				// check all the basketball games; if one is null return false
				if (bGames[x][y].team1 == null || bGames[x][y].team2 == null)
					return false;
		}
		// if we've made it this far, there are no null references, so the day
		// is full, so return true
		return true;
	}

	protected boolean hockeyFinished() {
		for (int x = 0; x < bGames.length; x++)
			for (int y = 0; y < hGames[x].length; y++){
				if (hGames[x][y].team1 == null || hGames[x][y].team2 == null)// check the
					// hockey
					// games; if
					// one is
					// null
					// return
					// false
					return false;
			}
		// if we've made it this far, there are no null references, so the
		// hockey day is full, so return true
		return true;
	}

	protected boolean testBTeam(int i, int j, int team, int q, BallTeam[] bTeams) {
		if (team == 0)
			bGames[i][j].team1 = bTeams[q];
		else
			bGames[i][j].team2 = bTeams[q];

		boolean OK = everythingIsOK();

		if (team == 0)
			bGames[i][j].team1 = null;
		else
			bGames[i][j].team2 = null;

		return OK;
	}

	protected boolean testBGame(int i, int j, BallGame tryGame) {
		bGames[i][j].team1 = tryGame.team1;
		bGames[i][j].team2 = tryGame.team2;

		boolean OK = everythingIsOK();

		bGames[i][j].team1 = null;
		bGames[i][j].team2 = null;

		// if(OK==false) System.out.println("testBGame() returning false");
		// if(OK==true) System.out.println("testBGame() returning true");
		return OK;
	}

	public boolean bMatchupsFinished(int numMatchups) {
		int count = 0;
		for (int x = 0; x < bGames.length; x++) {
			for (int y = 0; y < bGames[x].length; y++) {
				if (bGames[x][y].team1 != null)
					count++;
			}
		}
		return (count == numMatchups);
	}

	public boolean bMatchupsOK(LinkedList<BallGame> bMatchups) {
		if (bMatchups.size() == 0) // if no matchups were requested
			return true;

		BallGame testGame;
		for (int x = 0; x < bGames.length; x++) {
			for (int y = 0; y < bGames[x].length; y++) // for every basketball
				// game, do the
				// following:
			{
				if (bGames[x][y].team1 != null && bGames[x][y].team2 != null) // if
					// either
					// team
					// is
					// null,
					// it
					// obviously
					// doesn't
					// violate
					// a
					// matchup,
					// so
					// don't
					// test
					// it
				{
					for (int q = 0; q < bMatchups.size(); q++) // iterate
						// through each
						// member of the
						// matchup list
						// and check to
						// see if it
						// provides a
						// violation
					{
						testGame = bMatchups.get(q);
						if ((bGames[x][y].team1.getName() == testGame.team1
								.getName() && bGames[x][y].team2.getName() != testGame.team2
								.getName())
								|| // test for
								(bGames[x][y].team1.getName() == testGame.team2
								.getName() && bGames[x][y].team1
								.getName() != testGame.team2.getName())
								|| // any possible
								(bGames[x][y].team2.getName() == testGame.team1
								.getName() && bGames[x][y].team2
								.getName() != testGame.team1.getName())
								|| // violation of
								(bGames[x][y].team2.getName() == testGame.team2
								.getName() && bGames[x][y].team1
								.getName() != testGame.team1.getName())) // the
							// matchups
							return false;
					}
				}
			}
		}
		// if it's made it this far, there are no problems, so...
		return true;
	}

	protected boolean bAlreadyPlaying(BallTeam checkTeam) {
		for (int x = 0; x < bGames.length; x++) {
			for (int y = 0; y < bGames[x].length; y++) {
				if (bGames[x][y].team1 != null
						&& bGames[x][y].team1.getName() == checkTeam.getName())
					return true;
				if (bGames[x][y].team2 != null
						&& bGames[x][y].team2.getName() == checkTeam.getName())
					return true;
			}
		}
		// if it's made it this far...
		return false;
	}

	protected boolean hAlreadyPlaying(HockeyTeam checkTeam) {
		for (int x = 0; x < hGames.length; x++) {
			for (int y = 0; y < hGames[x].length; y++){
				if (hGames[x][y].team1 != null
						&& hGames[x][y].team1.getName().equalsIgnoreCase(
								checkTeam.getName()))
					return true;
				if (hGames[x][y].team2 != null
						&& hGames[x][y].team2.getName().equalsIgnoreCase(
								checkTeam.getName()))
					return true;
			}
		}
		// if it's made it this far...
		return false;
	}

	protected boolean testHTeam(int i, int j, int team, HockeyTeam testTeam) {
		if (team == 0)
			hGames[i][j].team1 = testTeam;
		else
			hGames[i][j].team2 = testTeam;

		boolean OK = everythingIsOK();

		if (team == 0)
			hGames[i][j].team1 = null;
		else
			hGames[i][j].team2 = null;

		return OK;
	}

	public boolean hMatchupOK(HockeyGame attempted,
			LinkedList<HockeyGame> hMatchups) {
		if (attempted.team1 == null || attempted.team2 == null)
			return true;

		HockeyGame game;
		for (int q = 0; q < hMatchups.size(); q++) {
			game = hMatchups.get(q);
			if (attempted.team1.getName().equals(game.team1.getName())
					|| attempted.team1.getName().equals(game.team2.getName())
					|| attempted.team2.getName().equals(game.team1.getName())
					|| attempted.team2.getName().equals(game.team1.getName()))
				if (!game.equals(attempted))
					return false;
				else
					return true;
		}
		// if it's made it far, neither of these teams were involved in a
		// request, so..
		return true;
	}

	public boolean hMatchupsOK(LinkedList<HockeyGame> hMatchups) {
		if (hMatchups.size() == 0) // if no matchups were requested
			return true;

		HockeyGame testGame;
		for (int x = 0; x < hGames.length; x++)  // for every basketball game, do
			// the following:
		{
			for (int y = 0; y < hGames[x].length; y++){
				if (hGames[x][y].team1 != null && hGames[x][y].team2 != null) // if either
					// team is
					// null, it
					// obviously
					// doesn't
					// violate a
					// matchup,
					// so don't
					// bother
					// testing
					// it
				{
					for (int q = 0; q < hMatchups.size(); q++) // iterate through
						// each member of
						// the matchup list
						// and check to see
						// if it provides a
						// violation
					{
						testGame = hMatchups.get(q);
						if ((hGames[x][y].team1.getName().equalsIgnoreCase(
								testGame.team1.getName()) && !hGames[x][y].team2
								.getName().equalsIgnoreCase(
										testGame.team2.getName()))
										|| // test for
										(hGames[x][y].team1.getName().equalsIgnoreCase(
												testGame.team2.getName()) && !hGames[x][y].team1
												.getName().equalsIgnoreCase(
														testGame.team2.getName())) || // any
														// possible
														(hGames[x][y].team2.getName().equalsIgnoreCase(
																testGame.team1.getName()) && !hGames[x][y].team2
																.getName().equalsIgnoreCase(
																		testGame.team1.getName())) || // violation
																		// of
																		(hGames[x][y].team2.getName().equalsIgnoreCase(
																				testGame.team2.getName()) && !hGames[x][y].team1
																				.getName().equalsIgnoreCase(
																						testGame.team1.getName()))) // the
							// matchups
							return false;
					}
				}
			}
		}
		// if it's made it this far, there are no problems, so...
		return true;
	}

	// protected static boolean hMatchupHere(HockeyTeam t1, HockeyTeam t2,
	// LinkedList<HockeyGame> matchups)
	// {
	// Iterator go=new LinkedListIterator(matchups);//find the right name for
	// the linkedlist iterator
	// HockeyGame game;
	// for(int q=0;q<matchups.size();q++)
	// {
	// game=go.next();
	// if((game.team1.getName().equals(t1.getName()) &&
	// game.team2.getName().equals(t2.getName()))//if the matchup is here
	// || (game.team1.getName().equals(t2.getName()) &&
	// game.team2.getName().equals(t1.getName())))
	// return true;
	// }
	// return false;//if it's reached the end, the matchup wasn't found, so
	// return false
	// }

	public void bToNull() {
		for (int x = 0; x < bGames.length; x++)// set all the basketball teams
			// back to null
		{
			for (int y = 0; y < bGames[x].length; y++) {
				bGames[x][y] = new BallGame();
			}
		}
	}

	public void hToNull() {
		for (int q = 0; q < hGames.length; q++){// set all the hockey teams back
			for (int i = 0; i < hGames[q].length; i++){
				// to null
				hGames[q][i].team1 = null;
				hGames[q][i].team2 = null;
			}
		}
	}

	public void toNull() {
		// System.out.println("setting everything back to null");
		hToNull();
		bToNull();
	}

	public String bIsPlaying(int team)
	// Precondition: the day is full (no nulls)
	{
		for (int x = 0; x < bGames.length; x++) {
			for (int y = 0; y < bGames[x].length; y++) {
				if (bGames[x][y].team1.getName() == team)
					return "" + bGames[x][y].team2.getName();
				else if (bGames[x][y].team2.getName() == team)
					return "" + bGames[x][y].team1.getName();
			}
		}
		return "";// if it's made it this far the team is not playing, so return
		// an empty String
	}

	public String hIsPlaying(String team)
	// Precondition: the day is full (no nulls)
	{
		for (int q = 0; q < hGames.length; q++) {
			for (int p = 0; p < hGames[q].length; p++){
				if (hGames[q][p].team1.getName().equals(team))
					return "" + hGames[q][p].team2.getName();
				else if (hGames[q][p].team2.getName().equals(team))
					return "" + hGames[q][p].team1.getName();
			}
		}
		return "";// if it's made it this far the team is not playing, so return
		// an empty String
	}

	public void printDay() {
		for (int q = 0; q < bGames.length; q++)// for each period do...
		{
			System.out.println("\n\n  PERIOD " + (q + 1) + ":");
			System.out.println("     Basketball games:");
			for (int w = 0; w < bGames[q].length; w++) {
				System.out.println("        Team " + bGames[q][w].team1.getName()
						+ " vs. Team " + bGames[q][w].team2.getName());
			}
			System.out.println("\n     Hockey games:");
			for (int p = 0; p < hGames[q].length; p ++){
				System.out.println("        Team "
						+ hGames[q][p].team1.getName().toUpperCase() + " vs. Team "
						+ hGames[q][p].team2.getName().toUpperCase());
			}
		}
	}

	public void testPrintDay() {
		for (int q = 0; q < bGames.length; q++)// for each period do...
		{
			System.out.print("PERIOD " + (q + 1) + ":");
			System.out.print(" Basketball:");
			for (int w = 0; w < bGames[q].length; w++) {
				if (bGames[q][w].team1 != null)
					System.out.print(" " + bGames[q][w].team1.getName());
				System.out.print(" vs. ");
				if (bGames[q][w].team2 != null)
					System.out.print(" " + bGames[q][w].team2.getName());
			}
			System.out.println();
			System.out.print("      Hockey:");
			for (int p = 0; p < hGames[q].length; p ++){
			if (hGames[q][p].team1 != null)
				System.out.print(" " + hGames[q][p].team1.getName().toUpperCase());
			System.out.print(" vs. ");
			if (hGames[q][p].team2 != null)
				System.out.print(" " + hGames[q][p].team2.getName().toUpperCase());
			System.out.println();
			}
		}
	}
}