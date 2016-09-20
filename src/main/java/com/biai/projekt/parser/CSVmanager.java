package com.biai.projekt.parser;

import com.opencsv.CSVReader;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Peter on 2016-09-20.
 */
public class CSVmanager {
    static public DataSet readDataSetFromFile(String fileName, DataSet trainingSet) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(fileName), ';');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //TODO
        //Check if data grain
        String[] nextLine;
        try {
            while ((nextLine = reader.readNext()) != null) {
                trainingSet.addRow(new DataSetRow(new double[]{
                        1 / Double.parseDouble(nextLine[0].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[1].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[2].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[3].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[4].replace(',', '.')),
                        1 / Double.parseDouble(nextLine[5].replace(',', '.')),},
                        new double[]{Double.parseDouble(nextLine[6].replace(',', '.')),
                                Double.parseDouble(nextLine[7].replace(',', '.'))}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trainingSet;
    }
}
