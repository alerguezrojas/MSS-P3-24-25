import preprocessing.NoPreprocessing;
import preprocessing.Preprocessor;

import java.io.*;
import java.util.*;

/**
 * Represents a dataset composed of multiple instances and their associated attributes.
 * Supports configurable preprocessing, weighting, loading, saving, and dataset splitting.
 */
public class Dataset {
    private final List<Instance> instances;
    private final List<Attribute> attributes;
    private Preprocessor preprocessor;
    private double[] weights;

    /**
     * Constructs an empty Dataset.
     */
    public Dataset() {
        this.instances = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.preprocessor = new NoPreprocessing();
    }

    /**
     * Loads the dataset from a CSV file, parsing both numeric and qualitative values.
     * @param filename path to the CSV file
     * @param isNumeric array indicating which attributes are numeric
     * @throws IOException if the file cannot be read
     */
    public void loadFromCSV(String filename, boolean[] isNumeric) throws IOException {
        instances.clear();
        attributes.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] header = reader.readLine().split(",");
            for (int i = 0; i < header.length - 1; i++) {
                attributes.add(new Attribute(header[i], isNumeric[i]));
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                double[] numeric = new double[attributes.size()];
                String[] qualitative = new String[attributes.size()];
                String label = tokens[attributes.size()];

                for (int i = 0; i < attributes.size(); i++) {
                    Attribute attr = attributes.get(i);
                    if (attr.isNumeric()) {
                        numeric[i] = Double.parseDouble(tokens[i]);
                    } else {
                        qualitative[i] = tokens[i];
                        attr.addQualitativeValue(tokens[i]);
                    }
                }

                instances.add(new Instance(numeric, qualitative, label));
            }
        }

        weights = new double[attributes.size()];
        Arrays.fill(weights, 1.0); // default weights
    }

    /**
     * Saves the dataset to a CSV file.
     * @param filename the output filename
     * @throws IOException if the file cannot be written
     */
    public void saveToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < attributes.size(); i++) {
                writer.write(attributes.get(i).getName() + ",");
            }
            writer.write("label\n");

            for (Instance inst : instances) {
                for (int i = 0; i < attributes.size(); i++) {
                    if (attributes.get(i).isNumeric()) {
                        writer.write(String.valueOf(inst.getNumericValues()[i]));
                    } else {
                        writer.write(inst.getQualitativeValues()[i]);
                    }
                    writer.write(",");
                }
                writer.write(inst.getLabel() + "\n");
            }
        }
    }

    /**
     * Applies the current preprocessor to all numeric data in the dataset.
     */
    public void preprocessAll() {
        if (preprocessor != null) {
            double[][] data = instances.stream()
                    .map(Instance::getNumericValues)
                    .toArray(double[][]::new);
            double[][] processed = preprocessor.transform(data);
            for (int i = 0; i < instances.size(); i++) {
                instances.get(i).setNumericValues(processed[i]);
            }
        }
    }

    /**
     * Splits the dataset into two parts: training and testing.
     * @param trainRatio proportion of instances to assign to the training set (0.0–1.0)
     * @return an array of two Datasets: [0] = train, [1] = test
     */
    public Dataset[] splitTrainTest(double trainRatio) {
        int splitPoint = (int) (instances.size() * trainRatio);
        Dataset train = new Dataset();
        Dataset test = new Dataset();
        train.attributes.addAll(this.attributes);
        test.attributes.addAll(this.attributes);
        train.instances.addAll(instances.subList(0, splitPoint));
        test.instances.addAll(instances.subList(splitPoint, instances.size()));
        train.setPreprocessor(this.preprocessor);
        test.setPreprocessor(this.preprocessor);
        train.setWeights(this.weights);
        test.setWeights(this.weights);
        return new Dataset[]{train, test};
    }

    /**
     * Returns the list of all instances.
     * @return list of instances
     */
    public List<Instance> getInstances() {
        return instances;
    }

    /**
     * Returns the list of attributes.
     * @return list of attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Sets the preprocessor to be used.
     * @param preprocessor a strategy implementing Preprocessor
     */
    public void setPreprocessor(Preprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    /**
     * Returns the current preprocessor.
     * @return preprocessor instance
     */
    public Preprocessor getPreprocessor() {
        return preprocessor;
    }

    /**
     * Returns the weights array used for numeric attribute weighting.
     * @return array of weights
     */
    public double[] getWeights() {
        return weights;
    }

    /**
     * Sets the weights for numeric attributes.
     * @param weights array of weights
     */
    public void setWeights(double[] weights) {
        this.weights = weights;
    }
}
