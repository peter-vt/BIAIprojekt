package com.biai.projekt.UI;

import com.biai.projekt.model.Match;
import com.biai.projekt.network.Solution;
import com.biai.projekt.parser.HTMLparser;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Peter on 2016-09-19.
 */
public class Application {

    private Scanner reader = new Scanner(System.in);
    HTMLparser parser = new HTMLparser();
    Solution solution = new Solution();


    public static void main(String[] args) {
        Application app = new Application();
        app.mainLoop();
    }

    private void mainLoop() {
        boolean runApp = true;
        do {
            System.out.println("Welcome!");
            System.out.println("Use this app to predicting volleyball match winner");
            System.out.println("Choose your action: ");
            System.out.println("1 - test network");
            System.out.println("2 - Load network");
            System.out.println("3 - Create network from given input");
            System.out.println("4 - Fetch data from remote");
            System.out.println("0 - to quit");

            int option = getOption();

            switch (option) {
                case 1:
                    testNetwork();
                    break;
                case 2:
                    solution.loadNetwork("PlusLiga20152016.nnet");
                    break;
                case 3:
                    createNetwork();
                    break;
                case 4:
                    fetchData();
                    break;
                case 0:
                default:
                    runApp = false;
                    break;
            }
        } while (runApp);

    }

    private void testNetwork() {
        solution.testNetwork();
        System.out.println("How would you want to test network?");

    }

    private void manualTest() {

    }

    private void automaticTest() {

    }

    private void createNetwork() {
        String networkDataFile = "", outputFile = "";

        System.out.print("Enter network data file name: ");
        networkDataFile = reader.next();

        System.out.print("Enter output file name: ");
        outputFile = reader.next();

        //solution.createNetwork(networkDataFile, outputFile);

        // TODO
        // Change it in production
        solution.createNetwork("PlusLiga20152016.csv", "PlusLiga20152016.nnet");
        solution.loadNetwork(outputFile);

        System.out.println("Network created and saved to " + outputFile + " file");
    }

    private void fetchData() {
        String preURL = "";
        Integer id = 0;
        int howManyMatches = 0;

        System.out.print("Please provide pre URL: ");
        preURL = reader.next();

        System.out.print("Please provider id: ");
        id = getInt();

        System.out.print("How many matches fetch? ");
        howManyMatches = getInt();


        //TODO
        //Hardcoded URLs
        preURL = "http://pls-web.dataproject.com/MatchStatistics.aspx?mID=";
        id = 25087;

        Vector<Match> matches = new Vector<Match>();
        int howManyFetched = 0;

        for (; howManyFetched < howManyMatches; howManyFetched++, id++) {
            try {
                matches.add(parseData(preURL + id.toString()));
            } catch (Exception exc) {
                System.out.println("Error fetching data, try again");
                try {
                    matches.add(parseData(preURL + id.toString()));
                } catch (Exception ex) {
                    System.out.println("Can't retrive data");
                    break;
                }
            }
        }

        for (Match match : matches) {
            match.getHomeName();
            System.out.println(match);
        }

        //TODO
        //Update forward teams
        Iterator<Match> iterator = matches.iterator();
        Match tmp;
        Iterator<Match> tmpIterator;
        boolean homeDone = false, guestDone = false;

        for (; iterator.hasNext(); iterator.next()) {
            tmpIterator = matches.iterator();
            tmp = (Match) iterator;

            for (; tmpIterator.hasNext(); tmpIterator.next()) {
                Match nextMatch = (Match) tmpIterator;
                if (nextMatch.getHomeName().equals(tmp.getHomeName()) && !homeDone ) {
                    nextMatch.setHomeAttackPerformance(tmp.getHomeAttackPerformance());
                    nextMatch.setHomeRecievePerformance(tmp.getHomeRecievePerformance());
                    nextMatch.setHomeBlocks(tmp.getHomeBlocks());
                    homeDone = true;
                } else if (nextMatch.getHomeName().equals(tmp.getGuestName()) && !guestDone) {
                    nextMatch.setHomeAttackPerformance(tmp.getGuestAttackPerformance());
                    nextMatch.setHomeRecievePerformance(tmp.getGuestRecievePerformance());
                    nextMatch.setHomeBlocks(tmp.getGuestBlocks());
                    guestDone = true;
                }  else if (nextMatch.getGuestName().equals(tmp.getHomeName()) && !homeDone) {
                    nextMatch.setGuestAttackPerformance(tmp.getHomeAttackPerformance());
                    nextMatch.setGuestRecievePerformance(tmp.getHomeRecievePerformance());
                    nextMatch.setGuestBlocks(tmp.getHomeBlocks());
                    homeDone = true;
                }  else if (nextMatch.getGuestName().equals(tmp.getGuestName()) && !guestDone) {
                    nextMatch.setGuestAttackPerformance(tmp.getGuestAttackPerformance());
                    nextMatch.setGuestRecievePerformance(tmp.getGuestRecievePerformance());
                    nextMatch.setGuestBlocks(tmp.getGuestBlocks());
                    guestDone = true;
                }

                if (homeDone && guestDone) {
                    break;
                }
            }
            iterator.remove();
        }

        for (Match match : matches) {
            System.out.println(match);
        }
        System.out.println("Fetched " + howManyFetched);


    }

    private void updateTeam() {

    }

    private Match parseData(String URL) throws IOException {
        return parser.parse(URL);
    }

    private int getOption() {
        System.out.print("Option:  ");
        int n = 0;
        boolean wrongInput = true;
        do {
            try {
                n = getInt();
                if (n < 0 || n > 4) {
                    throw new Exception("Out of range");
                }
                wrongInput = false;
            } catch (Exception exc) {
                System.out.print("Wrong input, try again: ");
            }
        } while (wrongInput);
        return n;
    }

    private int getInt() {
        int n = 0;
        boolean wrongInput = true;
        do {
            try {
                n = Integer.parseInt(reader.next());
                wrongInput = false;
            } catch (Exception exc) {
                System.out.print("Wrong input, try again: ");
            }
        } while (wrongInput);
        return n;
    }
}
