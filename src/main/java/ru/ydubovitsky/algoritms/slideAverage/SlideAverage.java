package ru.ydubovitsky.algoritms.slideAverage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//! Мой вариант тоже правильный, но тут с вводом косяки!
public class SlideAverage {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        // Время измерения
        Double measureTime = Double.parseDouble(reader.readLine());
        // Массив данных получаемых в течение measureTime
        List<Double> dataArray = readArrayFromInput(reader);
        // Окно сглаживания
        Integer smoothWindow = Integer.parseInt(reader.readLine());

        writer.write(
                outputArrayIntoString(slideAverage(measureTime, dataArray, smoothWindow))
        );
    }

    private static List<Double> slideAverage(Double measureTime, List<Double> dataArray, Integer smoothWindow) {
        List<Double> result = new ArrayList<>();
        Integer begin = 0;
        Integer end = smoothWindow;
        Double sum = 0.0;
        for (int i = begin; i < end; i++) {
            sum += dataArray.get(i);
        }
        Double average = sum / smoothWindow;
        result.add(average);

        for (int i = 0; i < dataArray.size() - smoothWindow; i++) {
            sum -= dataArray.get(i);
            sum += dataArray.get(i + smoothWindow);
            average = sum / smoothWindow;
            result.add(average);
        }

        return result;
    }

    private static List<Double> readArrayFromInput(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(element -> Double.parseDouble(element))
                .collect(Collectors.toList());
    }

    private static String outputArrayIntoString(List<?> array) {
        List<String> collect = array.stream()
                .map(el -> String.valueOf(el))
                .collect(Collectors.toList());

        return collect.stream()
                .reduce((prev, cur) -> prev + " " + cur)
                .orElseThrow();
    }
}
