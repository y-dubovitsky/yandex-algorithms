package ru.ydubovitsky.algoritms.twoChips;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TwoChips {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int windowSize = readInt(reader);
            List<Integer> result = movingAverage(n, arr, windowSize);
            if (Objects.isNull(result)) {
                writer.write("None");
                return;
            }
            for (int elem : result) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Integer> movingAverage(int n, List<Integer> arr, int windowSize) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int a = arr.get(i);
                int b = arr.get(j);
                if (a + b == windowSize) {
                    result.add(a);
                    result.add(b);
                    return result;
                }
            }
        }
        return null;
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
