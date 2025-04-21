package preprocessing;

/**
 * Interface for preprocessing strategies.
 * Implementations define how to transform numeric data.
 */
public interface Preprocessor {
    /**
     * Applies preprocessing to a matrix of numeric values.
     * @param data the original numeric data
     * @return the transformed data
     */
    double[][] transform(double[][] data);
}


