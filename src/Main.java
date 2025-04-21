import preprocessing.MinMaxScaler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dataset fullDataset = new Dataset();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("CSV file path: ");
            String file = sc.nextLine();

            boolean[] isNumeric = {true, true, true, true}; // Para iris.csv
            fullDataset.loadFromCSV(file, isNumeric);

            System.out.println("Original full dataset:");
            for (Instance inst : fullDataset.getInstances()) {
                System.out.println(inst);
            }

            // Aplicamos preprocesador real
            fullDataset.setPreprocessor(new MinMaxScaler());
            fullDataset.preprocessAll();

            // División del dataset en entrenamiento y prueba
            Dataset[] split = fullDataset.splitTrainTest(0.7); // 70% train, 30% test
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
