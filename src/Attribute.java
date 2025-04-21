import java.util.*;

/**
 * Represents a single attribute in the dataset.
 * It can be numeric or qualitative and stores its metadata.
 */
public class Attribute {
    private final String name;
    private final boolean isNumeric;
    private final List<String> qualitativeValues;

    /**
     * Constructs an Attribute.
     * @param name the name of the attribute
     * @param isNumeric true if the attribute is numeric
     */
    public Attribute(String name, boolean isNumeric) {
        this.name = name;
        this.isNumeric = isNumeric;
        this.qualitativeValues = isNumeric ? null : new ArrayList<>();
    }

    /**
     * Returns the attribute's name.
     * @return the attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates whether the attribute is numeric.
     * @return true if numeric, false if qualitative
     */
    public boolean isNumeric() {
        return isNumeric;
    }

    /**
     * Adds a new qualitative value if not already stored.
     * @param value the value to add
     */
    public void addQualitativeValue(String value) {
        if (!isNumeric && !qualitativeValues.contains(value)) {
            qualitativeValues.add(value);
        }
    }

    /**
     * Gets the list of stored qualitative values.
     * @return a list of string values
     */
    public List<String> getQualitativeValues() {
        return qualitativeValues;
    }

    /**
     * Gets the index of a qualitative value.
     * @param value the value to find
     * @return the index, or -1 if not found
     */
    public int getIndexOfQualitativeValue(String value) {
        return qualitativeValues.indexOf(value);
    }

    /**
     * Computes the frequency of each qualitative value.
     * @param values a list of values
     * @return a map with each value and its frequency count
     */
    public Map<String, Integer> getFrequencies(List<String> values) {
        Map<String, Integer> freq = new HashMap<>();
        for (String val : values) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
        return freq;
    }
}
