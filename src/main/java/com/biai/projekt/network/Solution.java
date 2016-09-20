package com.biai.projekt.network;

/**
 * Created by Peter on 2016-09-19.
 */
public class Solution {
    public static void main(String[] arg) {
        OurNetwork project = new OurNetwork();
        try {
            project.loadNetwork("PlusLiga20152016.nnet");
        } catch (Exception e) {
            project.createNetworkAndDataTest(9, 2);
            project.readDataSetFromFile("PlusLiga20162016.csv");
            project.networkLearn();
            //project.saveNetwork("PlusLiga20152016.nnet");
        }

        double[] outputResults = project.calculateInput(15, 4.2, 3.3, 41, 43, 39, 23, 29, 39);
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }

        System.out.println();
//        20;4,5;3,5;38;52;41;38;29;45;0;1;
//        18;4,2;4,3;39;51;28;21;32;43;1;0;
//        18;4,2;3,3;51;52;21;23;43;38;1;0;
//        14;3,6;3,2;42;48;33;30;45;41;1;0;
//        20;4,5;3,5;38;52;41;38;29;45;0;1;

        outputResults = project.calculateInput(20, 4.5, 3.5, 38, 52, 41, 38, 29, 45);
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }
        System.out.println();

        outputResults = project.calculateInput(18, 4.2, 4.3, 39, 51, 28, 21, 32, 43);
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }

        System.out.println();
        outputResults = project.calculateInput(18, 4.2, 3.3, 51, 52, 21, 23, 43, 38);
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }

        System.out.println();
        outputResults = project.calculateInput(14, 3.6, 3.2, 42, 48, 33, 30, 45, 41);
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }

        System.out.println();
        outputResults = project.calculateInput(20, 4.5, 3.5, 38, 52, 41, 38, 29, 45);
        for (double outputResult : outputResults) {
            System.out.print(Math.round(outputResult));
        }
    }
}
