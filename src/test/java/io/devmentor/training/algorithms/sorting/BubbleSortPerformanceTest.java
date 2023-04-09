package io.devmentor.training.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class BubbleSortPerformanceTest {

  @Test
  void testPerformance() {
    BubbleSort bubbleSort = new BubbleSort();
    int[] numbers = generateRandomArray(20_000);

    int[] numbersForSimpleSort = numbers.clone();
    int[] numbersForImprovedSort = numbers.clone();
    int[] numbersForFinalSort = numbers.clone();
    int[] numbersForJavaSort = numbers.clone();

    long startTime = System.currentTimeMillis();
    bubbleSort.simpleSort(numbersForSimpleSort);
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println("Простая сортировка. Время выполнения заняло (мс): " + elapsedTime);

    startTime = System.currentTimeMillis();
    bubbleSort.improvedSort(numbersForImprovedSort);
    stopTime = System.currentTimeMillis();
    elapsedTime = stopTime - startTime;
    System.out.println("Улучшенная сортировка. Время выполнения заняло (мс): " + elapsedTime);

    startTime = System.currentTimeMillis();
    bubbleSort.sort(numbersForFinalSort);
    stopTime = System.currentTimeMillis();
    elapsedTime = stopTime - startTime;
    System.out.println("Финальная сортировка. Время выполнения заняло (мс): " + elapsedTime);

    startTime = System.currentTimeMillis();
    Arrays.sort(numbersForJavaSort);
    stopTime = System.currentTimeMillis();
    elapsedTime = stopTime - startTime;
    System.out.println("Java-сортировка. Время выполнения заняло (мс): " + elapsedTime);
  }

  private int[] generateRandomArray(int length) {
    return IntStream.generate(() -> new Random().nextInt(100_000))
        .limit(length)
        .toArray();
  }
}
