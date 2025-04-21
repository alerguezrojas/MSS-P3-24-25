import preprocessing.MinMaxScaler;

import java.util.Scanner;

/**
 * Entry point to demonstrate usage of the extended Dataset class.
 * Loads data, applies preprocessing, splits into train/test and saves output files.
 */
public class Main {
    public static void main(String[] args) {
        Dataset fullDataset = new Dataset();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("CSV file path: ");
            String file = sc.nextLine();

            // Indicates which attributes are numeric (adjust according to the dataset)
            boolean[] isNumeric = {true, true, true, true}; // Ejemplo: iris.csv
            fullDataset.loadFromCSV(file, isNumeric);

            System.out.println("Original full dataset:");
            for (Instance inst : fullDataset.getInstances()) {
                System.out.println(inst);
            }

            // Apply normalization with MinMaxScaler
            fullDataset.setPreprocessor(new MinMaxScaler());
            fullDataset.preprocessAll();

            // Divide the dataset into 70% training, 30% test
            Dataset[] split = fullDataset.splitTrainTest(0.7);
            Dataset trainSet = split[0];
            Dataset testSet = split[1];

            System.out.println("\nNormalized training set:");
            for (Instance inst : trainSet.getInstances()) {
                System.out.println(inst);
            }

            System.out.println("\nNormalized test set:");
            for (Instance inst : testSet.getInstances()) {
                System.out.println(inst);
            }

            trainSet.saveToCSV("train_output.csv");
            testSet.saveToCSV("test_output.csv");
            System.out.println("\nTrain and test sets saved to CSV files.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

