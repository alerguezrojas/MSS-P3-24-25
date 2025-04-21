import java.util.Arrays;

/**
 * Represents a single data instance in the dataset.
 * Stores numeric and qualitative values, and the class label.
 */
public class Instance {
    private double[] numericValues;
    private String[] qualitativeValues;
    private String label;

    /**
     * Constructs an instance.
     * @param numericValues array of numeric attribute values
     * @param qualitativeValues array of qualitative attribute values
     * @param label the class label
     */
    public Instance(double[] numericValues, String[] qualitativeValues, String label) {
        this.numericValues = numericValues;
        this.qualitativeValues = qualitativeValues;
        this.label = label;
    }

    /**
     * Returns the numeric values.
     * @return numeric value array
     */
    public double[] getNumericValues() {
        return numericValues;
    }

    /**
     * Sets new numeric values.
     * @param numericValues the new values
     */
    public void setNumericValues(double[] numericValues) {
        this.numericValues = numericValues;
    }

    /**
     * Returns the qualitative values.
     * @return string value array
     */
    public String[] getQualitativeValues() {
        return qualitativeValues;
    }

    /**
     * Returns the label associated with the instance.
     * @return the class label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns a string representation of the instance.
     * @return the instance as string
     */
    @Override
    public String toString() {
        return Arrays.toString(numericValues) + " / " + Arrays.toString(qualitativeValues) + " => " + label;
    }
}
