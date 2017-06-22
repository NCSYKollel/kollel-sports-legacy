//package KollelSportsProgram;

import java.util.*;
import java.io.*;

public class KollelLeague {
	// ///////Fields/////////
	public BallTeam[] bTeams;
	public HockeyTeam[] hTeams;

	// //////Constructor//////////
	public KollelLeague(String filename) throws IOException {
		processLeague(filename);
	}

	// ////////Methods/////////////
	public void processLeague(String filename) throws IOException {
		FileReader fin = new FileReader(filename);
		Scanner in = new Scanner(fin);
		StringTokenizer tok;
		String input;
		int num, var;

		// read in the basketball teams
		input = in.nextLine();
		tok = new StringTokenizer(input);
		num = Integer.parseInt(tok.nextToken());// first token is how many
												// basketball teams there are
		bTeams = new BallTeam[num];
		for (int q = 0; q < num; q++)// read in all the basketball teams
		{
			input = in.nextLine();
			tok = new StringTokenizer(input);
			bTeams[q] = new BallTeam(Integer.parseInt(tok.nextToken()));// add
																		// team
																		// to
																		// array
																		// (first
																		// token
																		// is
																		// team
																		// name)
			var = Integer.parseInt(tok.nextToken());
			for (int w = 0; w < var; w++)
				// next token is how many hockey teams this basketball team
				// contains
				bTeams[q].hTeams.add(tok.nextToken());// add all this basketball
														// team's hockey teams
														// to its list
			while (tok.hasMoreTokens())
				// all remaining tokens are the names (numbers) of basketball
				// teams that this team has played
				bTeams[q].bTeamsPlayed.add(Integer.parseInt(tok.nextToken()));
		}

		// read in all the hockey teams
		input = in.nextLine();
		tok = new StringTokenizer(input);
		num = Integer.parseInt(tok.nextToken());// first token is how many
												// hockey teams there are
		hTeams = new HockeyTeam[num];
		for (int q = 0; q < num; q++)// read in all the hockey teams
		{
			input = in.nextLine();
			tok = new StringTokenizer(input);
			hTeams[q] = new HockeyTeam(tok.nextToken());// add team to array
														// (first token is team
														// name)
			var = Integer.parseInt(tok.nextToken());
			for (int w = 0; w < var; w++)
				// next token is how many basketball teams this hockey team
				// contains
				hTeams[q].bTeams.add(Integer.parseInt(tok.nextToken()));// add
																		// all
																		// this
																		// hockey
																		// team's
																		// basketball
																		// teams
																		// to
																		// its
																		// list
			while (tok.hasMoreTokens())
				// all remaining tokens are the names of hockey teams that this
				// team has played
				hTeams[q].hTeamsPlayed.add(tok.nextToken());
		}
	}

	public PriorityQueue<Integer> mostBGames() {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(30);
		int max = 0;
		for (int w = 0; w < bTeams.length; w++) {
			if (bTeams[w].bTeamsPlayed.size() > max)
				max = bTeams[w].bTeamsPlayed.size();// find the most games
													// played by any team
		}
		if (max > 0){// if it's zero (beginning of season), return an empty queue
			return q;
		}
		for (int w = 0; w < bTeams.length; w++) {
			if (bTeams[w].bTeamsPlayed.size() == max){// add the team name
													 // (number) to the
													 // queue
				q.add(bTeams[w].getName());
		    }
		}
		return q;
	}

	public PriorityQueue<String> mostHGames() {
		PriorityQueue<String> q = new PriorityQueue<String>(40);
		int max = 0;
		for (int w = 0; w < hTeams.length; w++) {
			if (hTeams[w].hTeamsPlayed.size() > max)
				max = hTeams[w].hTeamsPlayed.size();// find the most games
													// played by any team
		}
		if (max > 0){// if it's zero (beginning of season), return an empty queue
			return q;
		}
		for (int w = 0; w < hTeams.length; w++) {
			if (hTeams[w].hTeamsPlayed.size() == max){// add the team name
													// (number) to the
													// queue
					q.add(hTeams[w].getName());
			}
		}
		return q;
	}

	public void update(String fileName, KollelSportsDay day) throws IOException {
		FileReader fin = new FileReader(fileName);
		Scanner in = new Scanner(fin);
		StringTokenizer tok;
		String input, addTo, hName;
		PrintWriter pw = new PrintWriter(
				new FileWriter(
						"C:\\Documents and Settings\\Marc Poleyeff\\My Documents\\My Documents\\NCSY Kollel\\NCSY Kollel 2012\\Sports\\Kollel Sports Program\\Auxilary League Info.txt"));

		input = in.nextLine();
		pw.println(input);// first line of data never changes
		tok = new StringTokenizer(input);
		int teams = Integer.parseInt(tok.nextToken());// this token contains the
														// number of basketball
														// teams
		int bName;

		for (int q = 0; q < teams; q++)// for each basketball team team do...
		{
			input = in.nextLine();
			tok = new StringTokenizer(input);
			bName = Integer.parseInt(tok.nextToken());// this token contains the
														// team name (number)
			addTo = day.bIsPlaying(bName);
			if (addTo.length() == 1)
				addTo += "  ";
			else if (addTo.length() == 2)
				addTo += " ";
			pw.println(input + addTo);
		}

		input = in.nextLine();
		pw.println(input);// first line of data never changes
		tok = new StringTokenizer(input);
		teams = Integer.parseInt(tok.nextToken());// this token contains the
													// number of hockey teams
		for (int q = 0; q < teams; q++)// for each hockey team team do...
		{
			input = in.nextLine();
			tok = new StringTokenizer(input);
			hName = tok.nextToken();// this token contains the team name
			addTo = day.hIsPlaying(hName);
			if (addTo.length() == 1)
				addTo += " ";
			pw.println(input + addTo);
		}
		pw.close();
		// now we have to copy everything from the auxilary file into the main
		// one
		pw = new PrintWriter(new FileWriter(fileName));
		fin = new FileReader(
				"C:\\Documents and Settings\\Marc Poleyeff\\My Documents\\My Documents\\NCSY Kollel\\NCSY Kollel 2012\\Sports\\Kollel Sports Program\\Auxilary League Info.txt");
		in = new Scanner(fin);
		while (in.hasNext())
			pw.println(in.nextLine());
		pw.close();
	}
}