package com.biai.projekt.network;

import com.biai.projekt.parser.CSVmanager;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;

/**
 * Created by Peter on 2016-09-19.
 */
public class OurNetwork {
    private NeuralNetwork neuralNetwork;
    private DataSet trainingSet;


    public void createNetworkAndDataTest(int inputNeurons, int outputNeurons) {
        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, inputNeurons, 100, outputNeurons);
        MomentumBackpropagation learningRule = new MomentumBackpropagation();
        learningRule.setLearningRate(0.05);
        learningRule.setMomentum(0.4);
        neuralNetwork.setLearningRule(learningRule);
        trainingSet = new DataSet(inputNeurons, outputNeurons);
    }

    public void readDataSetFromFile(String fileName) {
        trainingSet = CSVmanager.readDataSetFromFile(fileName, trainingSet);
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

    public boolean isNetworkLoaded() {
        if (neuralNetwork != null) return true;
        else return false;
    }
}
