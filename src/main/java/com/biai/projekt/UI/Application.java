package com.biai.projekt.UI;

import com.biai.projekt.model.Match;
import com.biai.projekt.network.Solution;
import com.biai.projekt.parser.CSVmanager;
import com.biai.projekt.parser.HTMLparser;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.IOException;
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
        System.out.println("How would you want to test network?");
        System.out.println("1 - Automatic tests");
        System.out.println("2 - Manual test");

        int whichTest = getInt();

        switch (whichTest) {
            case 1:
                automaticTest();
                break;
            case 2:
                manualTest();
                break;
            default:
                break;
        }
    }

    private void automaticTest() {
        solution.testNetwork();
    }

    private void manualTest() {
        double HAP, HRP, HBR, GAP, GRP, GBR;


        try {
            System.out.print("Home attack performance: ");
            HAP = reader.nextDouble();
            System.out.print("Home receive performance: ");
            HRP = reader.nextDouble();
            System.out.print("Home block ratio: ");
            HBR = reader.nextDouble();
            System.out.print("Guest attack performance: ");
            GAP = reader.nextDouble();
            System.out.print("Guest receive performance: ");
            GRP = reader.nextDouble();
            System.out.print("Guest block ratio: ");
            GBR = reader.nextDouble();

            solution.callTest(HAP, HRP, HBR, GAP, GRP, GBR);

        } catch (Exception exc) {
            System.out.println("Wrong input!");
            return;
        }


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
        DataSet trainingSet = createStats(matches);
        CSVmanager.writeDataSetToFile("PlusLiga20152016.csv", trainingSet);
        System.out.println("Created " + trainingSet.size() + " data test");

        for (Match match : matches) {
            match.getHomeName();
            System.out.println(match);
        }
    }

    private DataSet createStats(Vector<Match> matches) {
        Match currentMatch;
        DataSet trainingSet = new DataSet(6, 2);
        Match tmpMatch = new Match();
        for (int i = 1; i < matches.size(); i++) {
            currentMatch = matches.elementAt(i);
            if (currentMatch.getHomePoints().compareTo(currentMatch.getGuestPoints()) == 1) {
                tmpMatch.setHomePoints("1");
                tmpMatch.setGuestPoints("0");
            } else {
                tmpMatch.setHomePoints("0");
                tmpMatch.setGuestPoints("1");
            }

            boolean homeDone = false, guestDone = false;
            for (int k = i - 1; k >= 0; k--) {
                if (currentMatch.getHomeName().equals(matches.elementAt(k).getHomeName()) && !homeDone) {
                    tmpMatch.setHomeAttackPerformance(matches.elementAt(k).getHomeAttackPerformance());
                    tmpMatch.setHomeRecievePerformance(matches.elementAt(k).getHomeRecievePerformance());
                    tmpMatch.setHomeBlocks(matches.elementAt(k).getHomeBlocks());
                    homeDone = true;
                }
                if (currentMatch.getHomeName().equals(matches.elementAt(k).getGuestName()) && !homeDone) {
                    tmpMatch.setHomeAttackPerformance(matches.elementAt(k).getGuestAttackPerformance());
                    tmpMatch.setHomeRecievePerformance(matches.elementAt(k).getGuestRecievePerformance());
                    tmpMatch.setHomeBlocks(matches.elementAt(k).getGuestBlocks());
                    homeDone = true;
                }
                if (currentMatch.getGuestName().equals(matches.elementAt(k).getHomeName()) && !guestDone) {
                    tmpMatch.setGuestAttackPerformance(matches.elementAt(k).getHomeAttackPerformance());
                    tmpMatch.setGuestRecievePerformance(matches.elementAt(k).getHomeRecievePerformance());
                    tmpMatch.setGuestBlocks(matches.elementAt(k).getHomeBlocks());
                    guestDone = true;
                }
                if (currentMatch.getGuestName().equals(matches.elementAt(k).getGuestName()) && !guestDone) {
                    tmpMatch.setGuestAttackPerformance(matches.elementAt(k).getGuestAttackPerformance());
                    tmpMatch.setGuestRecievePerformance(matches.elementAt(k).getGuestRecievePerformance());
                    tmpMatch.setGuestBlocks(matches.elementAt(k).getGuestBlocks());
                    guestDone = true;
                }
                if (homeDone && guestDone) {
                    trainingSet.addRow(matchToDataSetRow(tmpMatch));
                    break;
                }
            }
        }
        return trainingSet;
    }

    private DataSetRow matchToDataSetRow(Match match) {
        return new DataSetRow(new double[]{
                10 / Double.parseDouble(match.getHomeAttackPerformance().replaceAll("[^\\d.]", "")),
                10 / Double.parseDouble(match.getHomeRecievePerformance().replaceAll("[^\\d.]", "")),
                1 / Double.parseDouble(match.getHomeBlocks().replaceAll("[^\\d.]", "")),
                10 / Double.parseDouble(match.getGuestAttackPerformance().replaceAll("[^\\d.]", "")),
                10 / Double.parseDouble(match.getGuestRecievePerformance().replaceAll("[^\\d.]", "")),
                1 / Double.parseDouble(match.getGuestBlocks().replaceAll("[^\\d.]", ""))},
                new double[]{Double.parseDouble(match.getHomePoints()),
                        Double.parseDouble(match.getGuestPoints())});
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
