//package KollelSportsProgram;

import java.util.LinkedList;

public class HockeyTeam {
	// /////////////Fields//////////////
	private final String NAME;
	public LinkedList<Integer> bTeams;
	public LinkedList<String> hTeamsPlayed;

	// /////////////Constructor////////////
	public HockeyTeam(String s) {
		NAME = s;
		bTeams = new LinkedList<Integer>();
		hTeamsPlayed = new LinkedList<String>();
	}

	// //////////Methods///////////////////
	public String getName() {
		return NAME;
	}
}