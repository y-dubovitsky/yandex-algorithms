package ru.ydubovitsky.algoritms.twoChips;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Рита и Гоша играют в игру. У Риты есть n фишек, на каждой из которых написано количество очков. Фишки лежат на столе в порядке неубывания очков на них. Сначала Гоша называет число k, затем Рита должна выбрать две фишки, сумма очков на которых равна заданному числу.
 *
 * Рите надоело искать фишки самой, и она решила применить свои навыки программирования для решения этой задачи. Помогите ей написать программу для поиска нужных фишек.
 */
public class TwoChipsWithSort {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = readInt(reader);
            List<Integer> list = readList(reader);
            int sum = readInt(reader);

            List<Integer> result = movingAverage(
                    n,
                    sortList(list),
                    sum
            );
            if (result.size() == 0) {
                writer.write("None");
                return;
            }
            for (int elem : result) {
                writer.write(elem + " ");
            }
        }
    }

    private static List<Integer> movingAverage(int n, List<Integer> sortedList, int sum) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = sortedList.size() - 1;
        while (left < right) {
            if (sortedList.get(left) + sortedList.get(right) == sum) {
                result.add(sortedList.get(left));
                result.add(sortedList.get(right));
                break;
            }
            if (sortedList.get(left) + sortedList.get(right) < sum) {
                left += 1;
            }
            if (sortedList.get(left) + sortedList.get(right) > sum) {
                right -= 1;
            }
        }
        return result;
    }

    private static List<Integer> sortList(List<Integer> list) {
        return list.stream().sorted().collect(Collectors.toList());
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
