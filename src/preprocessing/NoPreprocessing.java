package preprocessing;

public class NoPreprocessing implements Preprocessor {
    @Override
    public double[][] transform(double[][] data) {
        return data; // Returns the data as-is, no transformation
    }
}

