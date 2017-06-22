//package KollelSportsProgram;

//Written by Yair Frankel and Nachi Cooper
//Primary purpose: to generate a daily schedule of sporting events for the NCSY Kollel leagues while avoiding both
//conflicts between the basketball and hockey leagues and scheduling games between teams that have already played.
//Associated programs: KollelLeague, KollelSportsDay, BallTeam, BallGame, HockeyTeam, HockeyGame

/* Features to add:
 * Swing
 * possibility for >3 periods (would require changing the constructor of KollelSportsDay, or at least additional constructors with 4 or even 5 parameters)
 * refs (starting with who has reffed the least...)
 * win/loss (program can eventually evolve into a full-fledged stat-tracking program)
 */

import java.util.*;
import java.io.*;

public class KollelSportsProgram {
    // ///////Fields///////////
    KollelLeague league;
    KollelSportsDay today;

    // //////Constuctor/////////
    public KollelSportsProgram(int x, int y, int z, String fileName, int h, int i, int j)
            throws IOException {
        league = new KollelLeague(fileName);
        today = new KollelSportsDay(x, y, z, h, i, j);
    }

    // ///////Methods///////////
    public static void main(String args[]) throws IOException {
        System.out.println();
        System.out
                .println("  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out
                .println("  X                                                                                                           X");
        System.out
                .println("  X                          X     X  XXXXX   X         X        XXXXXXXXX X                                  X");
        System.out
                .println("  X                          X    X  X     X  X         X        X         X                                  X");
        System.out
                .println("  X                          X   X  X       X X         X        X         X                                  X");
        System.out
                .println("  X                          X  X   X       X X         X        X         X                                  X");
        System.out
                .println("  X                          XXX    X       X X         X        XXXXXX    X                                  X");
        System.out
                .println("  X                          X  X   X       X X         X        X         X                                  X");
        System.out
                .println("  X                          X   X  X       X X         X        X         X                                  X");
        System.out
                .println("  X                          X    X  X     X  X         X        X         X                                  X");
        System.out
                .println("  X                          X     X  XXXXX   XXXXXXXXX XXXXXXXX XXXXXXXXX XXXXXXXXX                          X");
        System.out
                .println("  X                                                                                                           X");
        System.out
                .println("  X                       XXXXXXXXX XXXXXXX     XXXXX   XXXXXXX   XXXXXXXXX XXXXXXXXX  X                      X");
        System.out
                .println("  X                       X         X      X   X     X  X      X      X     X          X                      X");
        System.out
                .println("  X                       X         X       X X       X X       X     X     X          X                      X");
        System.out
                .println("  X                       X         X      X  X       X X      X      X     X          X                      X");
        System.out
                .println("  X                       XXXXXXXXX XXXXXXX   X       X XXXXXXX       X     XXXXXXXXX  X                      X");
        System.out
                .println("  X                               X X         X       X X    X        X             X  X                      X");
        System.out
                .println("  X                               X X         X       X X     X       X             X  X                      X");
        System.out
                .println("  X                               X X          X     X  X      X      X             X                         X");
        System.out
                .println("  X                       XXXXXXXXX X           XXXXX   X       X     X     XXXXXXXXX  X                      X");
        System.out
                .println("  X                                                                                                           X");
        System.out
                .println("  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        Scanner in = new Scanner(System.in);
        System.out
                .print("\n\n\n                                     Welcome to the Kollel Sports Program! \n\n  Please enter, separated by spaces, the amount of basketball games you would like in");
        System.out
                .print(" each of the three periods.\n");
        System.out
                .print("  For example: 4 4 3 or 4 4 0\n");
        System.out
                .print("  Input of any other format will cause the program to crash, and we don't want that.\n\n  ");
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();
        System.out.print("\n\n  Please enter the amount of hockey games you want in each period: 1 1 2\n\n  ");
        int h = in.nextInt();
        int i = in.nextInt();
        int j = in.nextInt();
        String fileName = "C:\\Documents and Settings\\Marc Poleyeff\\My Documents\\My Documents\\NCSY Kollel\\NCSY Kollel 2012\\Sports\\Kollel Sports Program\\Kollel League Info.txt";
        KollelSportsProgram today = new KollelSportsProgram(x, y, z, fileName, h, i ,j);
        while ((x + y + z) * 2 > today.league.bTeams.length) {
            System.out
                    .println("\n\n  You entered more games than are possible with this amount of teams. Please try again.");
            System.out.print("\n  Enter the number of basketball games you want in each period: 4 4 3\n\n  ");
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
            System.out.print("\n  Enter the amount of hockey games you want in each period: 1 1 2\n\n  ");
            h = in.nextInt();
            i = in.nextInt();
            j = in.nextInt();
            today = new KollelSportsProgram(x, y, z, fileName, h ,i ,j);
        }

        PriorityQueue<Integer> bQ = today.league.mostBGames();
        LinkedList<Integer> bTeamsNotPlaying = new LinkedList<Integer>();
        int teamsNotPlaying = (today.league.bTeams.length) - ((x + y + z) * 2);
        if (teamsNotPlaying > 0)// if there are more teams than slots
        {
            if (bQ.size() > 0 && bQ.size() < today.league.bTeams.length / 2)// there
                                                                            // are
                                                                            // teams
                                                                            // that
                                                                            // have
                                                                            // played
                                                                            // more
                                                                            // games
                                                                            // than
                                                                            // others,
                                                                            // and
                                                                            // it's
                                                                            // less
                                                                            // than
                                                                            // half
                                                                            // the
                                                                            // league
            {
                System.out
                        .print("\n\n  (The team(s) with the most games played so far: ");
                while (bQ.size() > 0) {
                    System.out.print("" + bQ.poll());
                    if (bQ.size() > 0)
                        System.out.print(", ");
                }
                System.out.print(".)\n");
            }
            System.out
                    .print("\n  Please enter the team numbers of  "
                            + teamsNotPlaying
                            + " basketball team(s) teams you don't want playing today, separated by spaces:\n\n  ");
            for (int q = 0; q < teamsNotPlaying; q++) {
                bTeamsNotPlaying.add(in.nextInt());
            }
        }
        // get special matchups
        LinkedList<BallGame> bMatchups = new LinkedList<BallGame>();
        int tm1, tm2;
        tm1 = tm2 = -1;
        BallTeam bT1, bT2;
        bT1 = bT2 = new BallTeam(0);// this is just to remove the
                                    // "may not have been initialized yet"
                                    // compiler error
        BallGame bRequest;
        boolean tryAgain;
        System.out
                .print("\n\n\n  Thank you. Are there any special basketball matchups you would like to request?  (y/n)\n\n  ");
        String choice = in.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.print("\n\n  You must choose either 'y' or 'n'.\n  ");
            choice = in.next();
        }
        if (choice.equalsIgnoreCase("y"))
            System.out
                    .println("\n\n  I would recommend not requesting more than 3 or 4 matchups.\n");
        while (choice.equalsIgnoreCase("y") && tm1 != 0
                && bMatchups.size() < x + y + z) {
            if (bMatchups.size() > 0)
                System.out.println("\n  Number of matchups requested: "
                        + bMatchups.size() + "\n  Number of possible games: "
                        + (x + y + z));
            System.out
                    .print("\n  Please enter the names (numbers) of the two teams that should play each other, separated by a space.\n  To stop entering matchups, enter '0 0'\n\n  ");
            do {
                tm1 = in.nextInt();
                tm2 = in.nextInt();
                tryAgain = false;
                for (int q = 0; q < today.league.bTeams.length; q++) {
                    if (tm1 == today.league.bTeams[q].getName())
                        bT1 = today.league.bTeams[q];
                    if (tm2 == today.league.bTeams[q].getName())
                        bT2 = today.league.bTeams[q];
                }
                bRequest = new BallGame(bT1, bT2);
                if (tm1 != 0
                        && (bTeamsNotPlaying.contains(tm1) || bTeamsNotPlaying
                                .contains(tm2))) {
                    System.out
                            .println("\n  One or both of those teams are on the list of teams that aren't supposed to play today.  Please try again.\n  ");
                    tryAgain = true;
                } else if (tm1 != 0 && bRequest.playedAlready()) {
                    System.out
                            .println("\n  Those two teams have played already each other.  Please try again.\n");
                    tryAgain = true;
                } else if (tm1 != 0)
                    bMatchups.add(bRequest);
            } while (tryAgain);
        }
        
        // find out which hockey teams he does/doesn't want to play
        PriorityQueue<String> hQ = today.league.mostHGames();
        LinkedList<String> hTeamsNotPlaying = new LinkedList<String>();
        teamsNotPlaying = today.league.hTeams.length
                -  ((h + i + j) * 2);   //today.today.hGames.length * 2;
        if (teamsNotPlaying > 0)// if there are more teams than slots
        {
            if (hQ.size() > 0 && hQ.size() <= today.league.hTeams.length / 2)// there
                                                                                // are
                                                                                // teams
                                                                                // that
                                                                                // have
                                                                                // played
                                                                                // more
                                                                                // games
                                                                                // than
                                                                                // others,
                                                                                // and
                                                                                // it's
                                                                                // <=
                                                                                // half
                                                                                // the
                                                                                // league
            {
                System.out
                        .print("   (The team(s) with the most games played so far: ");
                while (hQ.size() > 0) {
                    System.out.print("" + hQ.poll().toUpperCase());
                    if (hQ.size() > 0)
                        System.out.print(", ");
                }
                System.out.print(".)\n");
            }
            System.out
                    .println("  Enter the "
                            + teamsNotPlaying
                            + " letter of the hockey teams you do not want playing today, separated by spaces:\n\n  ");
            for (int q = 0; q < teamsNotPlaying; q++) {
                hTeamsNotPlaying.add(in.next().toLowerCase());
            }
        }
        // get special matchups
        LinkedList<HockeyGame> hMatchups = new LinkedList<HockeyGame>();
        String team1, team2;
        team1 = team2 = "";
        HockeyTeam t1, t2;
        t1 = t2 = new HockeyTeam("zzzz");// this is just to remove the
                                            // "may not have been initialized yet"
                                            // compiler error
        HockeyGame hRequest;
        System.out
                .print("\n\n  Are there any special hockey matchups you would like to request? (y/n)\n\n  ");
        choice = in.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.print("\n\n  You must choose either 'y' or 'n'.\n\n  ");
            choice = in.next();
        }
        if (choice.equalsIgnoreCase("y")) {
            System.out
                    .println("\n  Please keep in mind that entering too many matchups (hockey and basketball combined) might make it impossible");
            System.out
                    .println("  to generate a schedule.  Also, be very careful with the hockey matchups that you choose,");
            System.out
                    .println("  especially later in the season.  Make sure that what you request isn't impossible.\n  ");
        }
        while (choice.equalsIgnoreCase("y") && !team1.equalsIgnoreCase("z")
                && hMatchups.size() < today.today.hGames.length) {
            if (hMatchups.size() > 0)
                System.out.println("  Number of matchups requested: "
                        + hMatchups.size() + "\n  Number of possible games: "
                        + (h + i + j));
            System.out
                    .print("\n  Please enter the names of the two teams that should play each other, separated by a space.  \n  To stop entering matchups, enter 'z z'\n\n  ");
            do {
                team1 = in.next().toLowerCase();
                team2 = in.next().toLowerCase();
                tryAgain = false;
                for (int q = 0; !team1.equalsIgnoreCase("z")
                        && q < today.league.hTeams.length; q++) {
                    if (team1
                            .equalsIgnoreCase(today.league.hTeams[q].getName()))
                        t1 = today.league.hTeams[q];
                    if (team2
                            .equalsIgnoreCase(today.league.hTeams[q].getName()))
                        t2 = today.league.hTeams[q];
                }
                hRequest = new HockeyGame(t1, t2);
                if (!team1.equalsIgnoreCase("z")
                        && (hTeamsNotPlaying.contains(team1) || hTeamsNotPlaying
                                .contains(team2))) {
                    System.out
                            .println("\n  One or both of those teams are on the list of teams that aren't supposed to play today.  Please try again.\n");
                    tryAgain = true;
                } else if (!team1.equalsIgnoreCase("z")
                        && hRequest.playedAlready()) {
                    System.out
                            .println("\n  Those two teams have played already.  Please try again.\n");
                    tryAgain = true;
                } else if (!team1.equalsIgnoreCase("z"))
                    hMatchups.add(hRequest);
            } while (tryAgain);
        }

        boolean regenerate;
        do {
            regenerate = false;
            System.out
                    .println("\n\n  It is possible that you have entered information with which it is impossible to generate ");
            System.out
                    .println("  a valid schedule. This is more likely to happen if there are only two periods. It could also be ");
            System.out
                    .println("  later in the summer and there are more matchups that are no longer possible, and/or you requested ");
            System.out
                    .println("  too many special matchups (especially hockey ones).  If after about a minute there is no schedule, it means ");
            System.out
                    .println("  that you gave me unworkable information and you need to start the program again.  ");
            System.out
                    .println("  Try tinkering around with the teams that are playing.  Also, there's a good chance the problem ");
            System.out
                    .println("  is caused by the hockey teams you chose to play - there are less options with hockey.");
            today.process(bTeamsNotPlaying, hTeamsNotPlaying, bMatchups,
                    hMatchups);

            System.out.println("\n\n\n  Here is today's schedule:\n");
            today.today.printDay();
            System.out
                    .println("\n  Is this schedule satisfactory (y/n)? If you answer 'n' I will try to generate a different one using the data you've already entered.\n\n  ");
            choice = in.next();
            while (!choice.equalsIgnoreCase("y")
                    && !choice.equalsIgnoreCase("n")) {
                System.out.println("\n  You must choose either 'y' or 'n'.\n\n  ");
                choice = in.next();
            }
            if (choice.equalsIgnoreCase("n")) {
                regenerate = true;
                today.today.toNull();
            }
        } while (regenerate);

        System.out
                .println("\n  Would you like me to change the league information file to reflect the above schedule (y/n)?\n\n  ");
        choice = in.next();
        while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
            System.out.println("\n  You must choose either 'y' or 'n'.\n\n ");
            choice = in.next();
        }

        if (choice.equalsIgnoreCase("n")) {
            System.out
                    .println("\n  OK, I won't do anything.  But if you actually do use this schedule, remember to manually edit the league information file.\n");
        } else {
            today.write(fileName);
            System.out.println("Done.");
        }

        System.out.println("Goodbye, and have a great day.\n");
        String wait = "";
        while (!wait.equalsIgnoreCase("close") && !wait.equalsIgnoreCase("end")
                && !wait.equalsIgnoreCase("finish")
                && !wait.equalsIgnoreCase("goodbye")
                && !wait.equalsIgnoreCase("stop")
                && !wait.equalsIgnoreCase("bye") && in.hasNext())
            wait = in.nextLine();
    }

    protected void process(LinkedList<Integer> bNotPlaying,
            LinkedList<String> hNotPlaying, LinkedList<BallGame> bMatchups,
            LinkedList<HockeyGame> hMatchups) {
        if (hMatchups.size() > 0)
            doHSpecialMatchups(today.hGames, hMatchups);
        boolean hockeyDone = doKollelHockey(hNotPlaying);
        while (!hockeyDone) {
            today.hToNull();
            if (hMatchups.size() > 0)
                doHSpecialMatchups(today.hGames, hMatchups);
            hockeyDone = doKollelHockey(hNotPlaying);
        }
        // today.testPrintDay();//TEST
        boolean bSpecialDone = true;// just for the sake of avoiding a
                                    // "variable might not have been initialized"
                                    // error
        if (bMatchups.size() > 0) {
            bSpecialDone = doBSpecialMatchups(today.bGames, bMatchups);/*
                                                                         * today.
                                                                         * testPrintDay
                                                                         * ();
                                                                         */
        }// TEST
        if (bMatchups.size() > 0) {
            while (!bSpecialDone) {
                today.toNull();
                if (hMatchups.size() > 0)
                    doHSpecialMatchups(today.hGames, hMatchups);
                hockeyDone = doKollelHockey(hNotPlaying);
                while (!hockeyDone) {
                    today.hToNull();
                    if (hMatchups.size() > 0)
                        doHSpecialMatchups(today.hGames, hMatchups);
                    hockeyDone = doKollelHockey(hNotPlaying);
                }
                // today.testPrintDay();//TEST
                if (bMatchups.size() > 0) {
                    bSpecialDone = doBSpecialMatchups(today.bGames, bMatchups);/* 
                                                                                 * today
                                                                                 * .
                                                                                 * testPrintDay
                                                                                 * (
                                                                                 * )
                                                                                 * ;
                                                                                 */
                }// TEST
            }
        }
        boolean done = doKollelBall(bNotPlaying, bMatchups);
        while (!done)// if the return value is false, it means that the info
                        // (including the hockey info) is impossible to use and
                        // we need to randomize again
        {
            // System.out.println("it failed, so putting everything back to null");
            today.toNull();
            if (hMatchups.size() > 0)
                doHSpecialMatchups(today.hGames, hMatchups);
            hockeyDone = doKollelHockey(hNotPlaying);
            while (!hockeyDone) {
                today.toNull();
                if (hMatchups.size() > 0)
                    doHSpecialMatchups(today.hGames, hMatchups);
                hockeyDone = doKollelHockey(hNotPlaying);
            }
            if (bMatchups.size() > 0)
                bSpecialDone = doBSpecialMatchups(today.bGames, bMatchups);
            if (bMatchups.size() > 0) {
                while (!bSpecialDone) {
                    today.bToNull();
                    bSpecialDone = doBSpecialMatchups(today.bGames, bMatchups);
                }
            }
            done = doKollelBall(bNotPlaying, bMatchups);
        }
    }

    public boolean doKollelBall(LinkedList<Integer> bNotPlaying,
            LinkedList<BallGame> bMatchups)
    // creates a basketball schedule to go with the randomly created hockey
    // schedule
    {
        // today.testPrintDay();//TEST
        // System.out.println("Calling doKollelBall()");
        if (today.finished() && today.everythingIsOK()
                && today.bMatchupsOK(bMatchups))
            return true;

        int i, j, team;
        do { // randomly select an open slot
            i = (int) (Math.random() * today.bGames.length);
            j = (int) (Math.random() * today.bGames[i].length);
            team = (int) (Math.random() * 2);
        } while (!today.spotIsEmpty(i, j, team));

        for (int q = 0; q < league.bTeams.length; q++) {
            while ((q < league.bTeams.length)
                    && (bNotPlaying.contains(league.bTeams[q].getName()) || today
                            .bAlreadyPlaying(league.bTeams[q])))
                q++;
            // if(q<league.bTeams.length)
            // System.out.println("Testing team "+(q+1)+" in spot["+i+"]["+j+"], team "+(team+1));
            if (q < league.bTeams.length
                    && today.testBTeam(i, j, team, q, league.bTeams)) {
                // System.out.println("Team fits in this spot...");
                if (team == 0)
                    today.bGames[i][j].team1 = league.bTeams[q];
                else
                    today.bGames[i][j].team2 = league.bTeams[q];

                if (doKollelBall(bNotPlaying, bMatchups))
                    return true;
            }
        }

        if (team == 0)
            today.bGames[i][j].team1 = null;
        else
            today.bGames[i][j].team2 = null;
        // System.out.println("Returning false");
        return false;
    }

    public boolean doBSpecialMatchups(BallGame[][] bGames,
            LinkedList<BallGame> matchups) {
        // System.out.println("Calling doBSpecialMatchups()");
        if (today.bMatchupsFinished(matchups.size()) && today.everythingIsOK())
            return true;

        int i, j;
        do { // randomly select an open slot
            i = (int) (Math.random() * today.bGames.length);
            j = (int) (Math.random() * today.bGames[i].length);
        } while (!today.spotIsEmpty(i, j, 0));
        // System.out.println("Random slot to try: ["+i+"]["+j+"]");
        BallGame tryGame;
        for (int q = 0; q < matchups.size(); q++) {
            tryGame = matchups.get(q);
            if (!today.bAlreadyPlaying(tryGame.team1)) {
                // System.out.println("Trying the matchup of "+tryGame.team1.getName()+" vs. "+tryGame.team2.getName());
                if (today.testBGame(i, j, tryGame)) {
                    // System.out.println("Game fits in this spot...");
                    today.bGames[i][j].team1 = tryGame.team1;
                    today.bGames[i][j].team2 = tryGame.team2;
                    if (doBSpecialMatchups(bGames, matchups))
                        return true;
                }
            }
        }

        today.bGames[i][j].team1 = null;
        today.bGames[i][j].team2 = null;
        // System.out.println("Returning false");
        return false;
    }

    public void doHSpecialMatchups(HockeyGame[][] hGames,
            LinkedList<HockeyGame> matchups) {
        // System.out.println("Calling doHSpecialMatchups()");
        int i;
        int j;
        for (int q = 0; q < matchups.size(); q++)// with each matchup...
        {
            do { // randomly select an open slot
                i = (int) (Math.random() * hGames.length);
                j = (int) (Math.random() * hGames[i].length);
            } while (!today.hSpotIsEmpty(i, j, 0));
            hGames[i][j].team1 = matchups.get(q).team1;// ...fill an empty slot
            hGames[i][j].team2 = matchups.get(q).team2;
        }
    }

    public boolean doKollelHockey(LinkedList<String> hNotPlaying) {
        // System.out.println("Calling doKollelHockey()");
        if (today.hockeyFinished())
            return true;

        int i, team;
        int j;
        do { // randomly select an open slot
            i = (int) (Math.random() * today.hGames.length);
            j = (int) (Math.random() * today.hGames[i].length);
            team = (int) (Math.random() * 2);
        } while (!today.hSpotIsEmpty(i, j, team));

        for (int q = 0; q < league.hTeams.length /*
                                                 * &&
                                                 * !today.hAlreadyPlaying(league
                                                 * .hTeams[q])
                                                 */; q++)// I took out
                                                            // !hNotPlaying.contains(league.hTeams[q].getName())
                                                            // &&
        {
            while ((q < league.hTeams.length)
                    && (hNotPlaying.contains(league.hTeams[q].getName()) || today
                            .hAlreadyPlaying(league.hTeams[q])))
                    q++;
            // if(q<league.hTeams.length)
            // System.out.println("\nTesting team "+(q+1)+" in spot["+i+"], team "+(team+1)+"\n");
            if (q < league.hTeams.length
                    && today.testHTeam(i, j, team, league.hTeams[q])) {
                // System.out.println("Team fits in this spot...");
                if (team == 0)
                    today.hGames[i][j].team1 = league.hTeams[q];
                else
                    today.hGames[i][j].team2 = league.hTeams[q];

                if (doKollelHockey(hNotPlaying))
                    return true;
            }
        }
        if (team == 0)
            today.hGames[i][j].team1 = null;
        else
            today.hGames[i][j].team2 = null;
        // System.out.println("Setting spot["+i+"], team "+(team+1)+" to null and returning false");
        return false;
    }

    public void write(String fileName) throws IOException {
        league.update(fileName, today);
    }
}