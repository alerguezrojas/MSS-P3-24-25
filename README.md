# MSS-P3-24-25
**University:** Universidad de La Laguna  
**Degree:** Grado en Ingeniería Informática  
**Course:** Modelado de Sistemas Software (2024/2025)  
**Author:** Alejandro Rodríguez Rojas  
**Email:** alu0101317038@ull.edu.es

---

## 📘 Project: Instance Classification System (Practice 3)

This project represents an extended k-NN instance classification system, focusing on the modular design of the `Dataset` class. It includes support for numeric and qualitative attributes, preprocessing via interfaces, and train/test data splitting.

---

## 📁 Project Folder Structure

```
MSS-P3-24-25/
├── src/
│   └── main/
│       ├── Main.java
│       ├── Instance.java
│       ├── Attribute.java
│       ├── Dataset.java
│       └── preprocessing/
│           ├── Preprocessor.java
│           ├── NoPreprocessing.java
│           └── MinMaxScaler.java
├── data/
│   └── iris.csv
├── train_output.csv
├── test_output.csv
└── README.md
```

> ✅ Make sure `iris.csv` is located in the `data/` folder when running the program.

---

## 🧠 Main Components

- **`Instance`**: Represents a single row of data, holding numeric and qualitative values along with the label.
- **`Attribute`**: Describes dataset attributes: name, type (numeric/qualitative), and possible values.
- **`Dataset`**: Main controller class for data loading, saving, preprocessing, and splitting.
- **`Preprocessor`**: Interface to define preprocessing behavior.
- **`NoPreprocessing`**: A class that leaves data unchanged.
- **`MinMaxScaler`**: Normalizes numeric data to the range [0, 1].
- **`Main`**: Runs a prototype that demonstrates reading, processing, splitting, and saving a dataset.

---

## ⚙️ How to Use

1. Place your `iris.csv` file inside the `data/` folder.
2. Run the project from **IntelliJ IDEA**.
3. Enter the path: `data/iris.csv` when prompted.
4. The system will:
    - Load the dataset into memory.
    - Display the original instances.
    - Normalize the numeric attributes using Min-Max scaling.
    - Split into training (70%) and testing (30%) sets.
    - Save results into `train_output.csv` and `test_output.csv`.

---

## 🧪 Example Console Output

```
CSV file path: data/iris.csv
Original full dataset:
[5.1, 3.5, 1.4, 0.2] / [null, null, null, null] => Iris-setosa
...

Normalized training set:
[0.2222, 0.6250, 0.0678, 0.0417] / [null, null, null, null] => Iris-setosa
...

Train and test sets saved to CSV files.
```

---

## 📌 Notes

- Edit `boolean[] isNumeric` in `Main.java` to define which attributes are numeric.
- The system is capable of managing both numeric and qualitative attributes.
- Normalization is applied only to numeric data.

---

## 🚀 Future Enhancements

- Add k-NN classification capabilities (`KNNClassifier`)
- Include multiple distance metrics
- Implement majority voting for classification
- Create an experimentation module for running benchmarks

---

## 🛠️ Requirements

- Java 17 or higher
- IntelliJ IDEA
- CSV-formatted dataset file

---

