package com.biai.projekt.network;

import com.opencsv.CSVReader;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Peter on 2016-09-19.
 */
public class OurNetwork {
    private NeuralNetwork neuralNetwork;
    private DataSet trainingSet;


    public void createNetworkAndDataTest(int inputNeurons, int outputNeurons) {
        neuralNetwork = new MultiLayerPerceptron(inputNeurons, 40, outputNeurons);
        trainingSet = new DataSet(inputNeurons, outputNeurons);
    }


    public void readDataSetFromFile(String fileName) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(fileName), ';');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                trainingSet.addRow(new DataSetRow(new double[]{1 / Double.parseDouble(nextLine[0]),
                        1 / Double.parseDouble(nextLine[1].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[2].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[3].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[4].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[5].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[6].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[7].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[8].replace(',', '.'))},
                        new double[]{Double.parseDouble(nextLine[9].replace(',', '.')),
                                Double.parseDouble(nextLine[10].replace(',', '.'))}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void networkLearn() {
        neuralNetwork.learn(trainingSet);
    }

    public void saveNetwork(String fileName) {
        neuralNetwork.save(fileName);
    }

    public void loadNetwork(String fileName) throws Exception {
        neuralNetwork = NeuralNetwork.createFromFile(fileName);
        if (neuralNetwork == null) {
            throw new Exception("Can't read file");
        }
    }

    public double[] calculateInput(double... inputeArgs) {
        neuralNetwork.setInput(inputeArgs);
        neuralNetwork.calculate();
        return neuralNetwork.getOutput();
    }
}
