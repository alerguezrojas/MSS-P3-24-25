import preprocessing.NoPreprocessing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dataset dataset = new Dataset();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("CSV file path: ");
            String file = sc.nextLine();

            // Define attribute types manually for testing (e.g., Iris)
            boolean[] isNumeric = {true, true, true, true}; // Change if needed
            dataset.loadFromCSV(file, isNumeric);

            System.out.println("Original dataset:");
            for (Instance inst : dataset.getInstances()) {
                System.out.println(inst);
            }

            System.out.println("\nApplying preprocessor...");
            dataset.setPreprocessor(new NoPreprocessing()); // Default preprocessor
            dataset.preprocessAll();

            System.out.println("\nProcessed dataset:");
            for (Instance inst : dataset.getInstances()) {
                System.out.println(inst);
            }

            System.out.println("\nSaving to output.csv...");
            dataset.saveToCSV("output.csv");
            System.out.println("Done.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
