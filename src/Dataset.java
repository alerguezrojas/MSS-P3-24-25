import preprocessing.NoPreprocessing;
import preprocessing.Preprocessor;

import java.io.*;
import java.util.*;

public class Dataset {
    private final List<Instance> instances;
    private final List<Attribute> attributes;
    private Preprocessor preprocessor;
    private double[] weights;

    public Dataset() {
        this.instances = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.preprocessor = new NoPreprocessing();
    }

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
        Arrays.fill(weights, 1.0); // Peso por defecto
    }

    public void saveToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Header
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

    public void setPreprocessor(Preprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    public void preprocessAll() {
        if (preprocessor != null) {
            double[][] values = instances.stream()
                    .map(Instance::getNumericValues)
                    .toArray(double[][]::new);
            double[][] processed = preprocessor.transform(values);
            for (int i = 0; i < instances.size(); i++) {
                instances.get(i).setNumericValues(processed[i]);
            }
        }
    }

    public Dataset[] splitTrainTest(double trainRatio) {
        int splitPoint = (int) (instances.size() * trainRatio);
        Dataset train = new Dataset();
        Dataset test = new Dataset();
        train.attributes.addAll(this.attributes);
        test.attributes.addAll(this.attributes);
        train.instances.addAll(instances.subList(0, splitPoint));
        test.instances.addAll(instances.subList(splitPoint, instances.size()));
        return new Dataset[]{train, test};
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }
}
