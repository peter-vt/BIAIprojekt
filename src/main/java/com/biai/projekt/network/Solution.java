package com.biai.projekt.network;

/**
 * Created by Peter on 2016-09-19.
 */
public class Solution {

    private OurNetwork project = new OurNetwork();

    public void createNetwork(String CSVfileName, String networkFileName) {
        project.createNetworkAndDataTest(6, 2);
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

        callTest(6.1, 3.3, 45, 49, 36, 26, 0, 1);
        callTest(3.6, 3.2, 42, 48, 33, 30, 1, 0);
        callTest(4.5,3.5,38,52,41,38,0,1);
        callTest(4.2,4.3,39,51,28,21,1,0);
        callTest(3.7,4.4,46,41,23,28,0,1);
    }

    private void callTest(double a, double b, double c, double d, double e, double f, int res1, int res2) {
        double[] outputResults = project.calculateInput(1/a, 1/b, 10/c, 10/d, 10/e, 10/f);
        System.out.print("Expected " + res1 + res2 + " get ");
        for (double outputResult : outputResults) {
            System.out.print( Math.round(outputResult));
        }
        System.out.println();
    }
}
