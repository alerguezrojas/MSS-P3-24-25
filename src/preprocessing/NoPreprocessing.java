package preprocessing;

/**
 * A dummy preprocessor that does not alter the data.
 */
public class NoPreprocessing implements Preprocessor {
    /**
     * Returns the input data without changes.
     * @param data the original data
     * @return the same input data
     */
    @Override
    public double[][] transform(double[][] data) {
        return data;
    }
}

