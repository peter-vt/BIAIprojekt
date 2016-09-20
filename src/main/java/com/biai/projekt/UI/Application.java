package com.biai.projekt.UI;

import com.biai.projekt.parser.StatsParser;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Peter on 2016-09-19.
 */
public class Application {

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
            System.out.println("1 - Load network");
            System.out.println("2 - Create network from given input");
            System.out.println("3 - Fetch data from remote");
            System.out.println("0 - to quit");

            int option = getOptiion();

            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                default:
                    runApp = false;
                    break;
            }
        } while (runApp);

    }

    private int getOptiion() {
        Scanner reader = new Scanner(System.in);
        System.out.print("Option:  ");
        int n = 0;
        boolean wrongInput = true;
        do {
            try {
                n = Integer.parseInt(reader.next());
                if (n < 0 || n > 3) {
                    throw new Exception("Out of range");
                }
                wrongInput = false;
            } catch (Exception exc) {
                System.out.println("Wrong input");
            }
        } while (wrongInput);
        return n;
    }

    private void parser() {
        StatsParser parser = new StatsParser();
        String preURL = "http://pls-web.dataproject.com/MatchStatistics.aspx?mID=";
        int id = 25087;
        try {
            parser.parse(preURL + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
