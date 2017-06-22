//package KollelSportsProgram;

import java.util.LinkedList;

public class BallTeam {
	// /////////////Fields//////////////
	private final int NAME;
	public LinkedList<String> hTeams;
	public LinkedList<Integer> bTeamsPlayed;

	// /////////////Constructor////////////
	public BallTeam(int i) {
		NAME = i;
		hTeams = new LinkedList<String>();
		bTeamsPlayed = new LinkedList<Integer>();
	}

	// //////////Methods///////////////////
	public int getName() {
		return NAME;
	}
}