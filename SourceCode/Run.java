package SourceCode;

import java.io.*;
import java.util.Arrays;

public class Run {

    private static void execute(String fileName) throws IOException {
        int[] dataSet = loadDataset("Dataset/" + fileName);

        // Measure MaxHeapSort execution time and memory usage
        long heapStartTime = System.nanoTime();
        triggerGC();
        long heapStartMemoryUsage = getCurrentMemoryUsage();
        MaxHeapSort maxHeapSort = new MaxHeapSort();
        maxHeapSort.sort(Arrays.copyOf(dataSet, dataSet.length));
        long heapEndMemoryUsage = getCurrentMemoryUsage();
        long heapEndTime = System.nanoTime();

        printResults("MaxHeapSort", fileName, heapStartTime, heapEndTime, heapStartMemoryUsage, heapEndMemoryUsage);

        // Measure RandomizedShellSort execution time and memory usage
        long shellStartTime = System.nanoTime();
        triggerGC();
        long shellStartMemoryUsage = getCurrentMemoryUsage();
        RandomizedShellSort.randomizedShellSort(Arrays.copyOf(dataSet, dataSet.length));
        long shellEndMemoryUsage = getCurrentMemoryUsage();
        long shellEndTime = System.nanoTime();

        printResults("RandomizedShellSort", fileName, shellStartTime, shellEndTime, shellStartMemoryUsage, shellEndMemoryUsage);

        System.out.println("--------------------------------------");
    }

    private static int[] loadDataset(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append(" ");
        }

        reader.close();

        String[] stringValues = content.toString().trim().split(" ");
        int[] dataSet = new int[stringValues.length];

        for (int i = 0; i < stringValues.length; i++) {
            dataSet[i] = Integer.parseInt(stringValues[i]);
        }

        return dataSet;
    }

    private static long getCurrentMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static void triggerGC() {
        System.gc();
        System.gc();
        System.gc();
    }

    private static void printResults(String sortName, String fileName, long startTime, long endTime, long startMemoryUsage, long endMemoryUsage) {
        double executionTime = ((double) (endTime - startTime) / 1_000_000);
        double memoryUsage = (double) ((endMemoryUsage - startMemoryUsage) / 1024);

        System.out.println(sortName + " on " + fileName);
        System.out.println("Execution Time (ms): " + executionTime);
        System.out.println("Memory Usage (KB): " + memoryUsage);
    }

    public static void main(String[] args) {
        try {
            String[] fileNames = {
                    "DSKecilRandom.txt", "DSKecilReversed.txt", "DSKecilSorted.txt",
                    "DSSedangRandom.txt", "DSSedangReversed.txt", "DSSedangSorted.txt",
                    "DSBesarRandom.txt", "DSBesarReversed.txt", "DSBesarSorted.txt"
            };

            for (String fileName : fileNames) {
                execute(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
