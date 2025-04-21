package preprocessing;

public class MinMaxScaler implements Preprocessor {
    @Override
    public double[][] transform(double[][] data) {
        int rows = data.length;
        int cols = data[0].length;

        double[] min = new double[cols];
        double[] max = new double[cols];

        for (int j = 0; j < cols; j++) {
            min[j] = Double.POSITIVE_INFINITY;
            max[j] = Double.NEGATIVE_INFINITY;
        }

        for (double[] row : data) {
            for (int j = 0; j < cols; j++) {
                if (row[j] < min[j]) min[j] = row[j];
                if (row[j] > max[j]) max[j] = row[j];
            }
        }

        double[][] scaled = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (max[j] - min[j] != 0)
                    scaled[i][j] = (data[i][j] - min[j]) / (max[j] - min[j]);
                else
                    scaled[i][j] = 0.0;
            }
        }

        return scaled;
    }
}

