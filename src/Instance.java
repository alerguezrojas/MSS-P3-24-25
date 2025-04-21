import java.util.Arrays;

public class Instance {
    private double[] numericValues;
    private String[] qualitativeValues;
    private String label;

    public Instance(double[] numericValues, String[] qualitativeValues, String label) {
        this.numericValues = numericValues;
        this.qualitativeValues = qualitativeValues;
        this.label = label;
    }

    public double[] getNumericValues() {
        return numericValues;
    }

    public void setNumericValues(double[] numericValues) {
        this.numericValues = numericValues;
    }

    public String[] getQualitativeValues() {
        return qualitativeValues;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return Arrays.toString(numericValues) + " / " + Arrays.toString(qualitativeValues) + " => " + label;
    }
}
