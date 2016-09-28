package com.biai.projekt.network;

/**
 * Created by Peter on 2016-09-19.
 */
public class Solution {

    private OurNetwork project = new OurNetwork();

    public void createNetwork(String CSVfileName, String networkFileName) {
        project.createNetworkAndDataTest(6, 2);
        learnNewThings(CSVfileName, networkFileName);
    }


    public void learnNewThings(String CSVfileName, String networkFileName) {
        project.readDataSetFromFile(CSVfileName);
        project.networkLearn();
        project.saveNetwork(networkFileName);
    }

    public void loadNetwork(String fileName) {
        try {
            project.loadNetwork(fileName);
        } catch (Exception e) {
            System.out.println("No " + fileName + " found");
        }
        System.out.println("Loaded network from " + fileName + " file");
    }

    public void testNetwork() {
        if (!project.isNetworkLoaded()) {
            System.out.println("No network loaded");
            return;
        }

        callTest(0.21739130434782608, 0.45454545454545453, 0.08333333333333333, 0.23809523809523808, 0.38461538461538464, 0.5, 0.0, 1.0);
        callTest(0.2631578947368421, 0.30303030303030304, 0.1, 0.20833333333333334, 0.5, 0.07142857142857142, 0.0, 1.0);
        callTest(0.21739130434782608, 0.38461538461538464, 0.08333333333333333, 0.23809523809523808, 0.5882352941176471, 0.07142857142857142, 0.0, 1.0);
        callTest(0.21739130434782608, 0.38461538461538464, 0.08333333333333333, 0.23809523809523808, 0.5882352941176471, 0.07142857142857142, 0.0, 1.0);
        callTest(0.21739130434782608, 0.38461538461538464, 0.08333333333333333, 0.21739130434782608, 0.38461538461538464, 0.08333333333333333, 0.0, 1.0);
        callTest(0.23809523809523808, 0.38461538461538464, 0.5, 0.2222222222222222, 0.5555555555555556, 0.16666666666666666, 0.0, 1.0);

    }

    public void callTest(double a, double b, double c, double d, double e, double f) {
        double[] outputResults = project.calculateInput(a, b, c, d, e, f);
        if (outputResults[0] > outputResults[1]) {
            System.out.println("Home win");
        } else {
            System.out.println("Guest win");
        }
    }

    private void callTest(double a, double b, double c, double d, double e, double f, double res1, double res2) {
        double[] outputResults = project.calculateInput(a, b, c, d, e, f);
        System.out.print("Expected " + (int) res1 + (int) res2 + " get ");
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }
        System.out.println();
    }
}
