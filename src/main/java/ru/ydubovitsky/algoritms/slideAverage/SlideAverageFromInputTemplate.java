package ru.ydubovitsky.algoritms.slideAverage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//! Вариант из шаблона!
public class SlideAverageFromInputTemplate {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int windowSize = readInt(reader);
            List<Double> result = movingAverage(n, arr, windowSize);
            for (double elem : result) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Double> movingAverage(int n, List<Integer> arr, int windowSize) {
        List<Double> result = new ArrayList<>();
        Integer begin = 0;
        Integer end = windowSize;
        Double sum = 0.0;
        for (int i = begin; i < end; i++) {
            sum += arr.get(i);
        }
        Double average = sum / windowSize;
        result.add(average);

        for (int i = 0; i < arr.size() - windowSize; i++) {
            sum -= arr.get(i);
            sum += arr.get(i + windowSize);
            average = sum / windowSize;
            result.add(average);
        }

        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

}
