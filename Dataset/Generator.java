package Dataset;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {

    public static void create(int exponent, String datasetType) {
        int size = (int) Math.pow(2, exponent);
        int[] dataset = new int[size];
        Random random = new Random();
    
        switch (datasetType) {
            case "Random":
                for (int i = 0; i < size; i++) {
                    dataset[i] = random.nextInt(Integer.MAX_VALUE);  // Update this line to generate random values within the specified range
                }
                break;
            case "Sorted":
                for (int i = 0; i < size; i++) {
                    dataset[i] = i * (Integer.MAX_VALUE / (size - 1));  // Update this line to space out the values according to the specified range
                }
                break;
            case "Reversed":
                for (int i = 0; i < size; i++) {
                    dataset[i] = (size - 1 - i) * (Integer.MAX_VALUE / (size - 1));  // Update this line to space out the values according to the specified range
                }
                break;
        }
    
        String fileName = getFileName(exponent, datasetType);
        saveToFile(dataset, fileName);
    }

    private static String getFileName(int exponent, String datasetType) {
        String prefix = "DS";
        String sizeLabel = getSizeLabel(exponent);
        String fileName = prefix + sizeLabel + datasetType + ".txt";
        return fileName;
    }

    private static String getSizeLabel(int exponent) {
        if (exponent == 9) {
            return "Kecil";
        } else if (exponent == 13) {
            return "Sedang";
        } else if (exponent == 16) {
            return "Besar";
        } else {
            throw new IllegalArgumentException("Unsupported exponent: " + exponent);
        }
    }

    private static void saveToFile(int[] dataset, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int value : dataset) {
                writer.write(value + "\n");
            }
            writer.close();
            System.out.println("Dataset saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        create(9, "Sorted");
        create(9, "Random");
        create(9, "Reversed");

        create(13, "Sorted");
        create(13, "Random");
        create(13, "Reversed");

        create(16, "Sorted");
        create(16, "Random");
        create(16, "Reversed");
    }
}
