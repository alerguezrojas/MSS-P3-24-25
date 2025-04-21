import java.util.*;

public class Attribute {
    private final String name;
    private final boolean isNumeric;
    private final List<String> qualitativeValues;

    public Attribute(String name, boolean isNumeric) {
        this.name = name;
        this.isNumeric = isNumeric;
        this.qualitativeValues = isNumeric ? null : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean isNumeric() {
        return isNumeric;
    }

    public void addQualitativeValue(String value) {
        if (!isNumeric && !qualitativeValues.contains(value)) {
            qualitativeValues.add(value);
        }
    }

    public List<String> getQualitativeValues() {
        return qualitativeValues;
    }

    public int getIndexOfQualitativeValue(String value) {
        return qualitativeValues.indexOf(value);
    }

    public Map<String, Integer> getFrequencies(List<String> values) {
        Map<String, Integer> freq = new HashMap<>();
        for (String val : values) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
        return freq;
    }
}
