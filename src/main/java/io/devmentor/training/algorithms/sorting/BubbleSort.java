package io.devmentor.training.algorithms.sorting;

/**
 * Алгоритмы сортировки, "Сортировка пузырьком" ("пузырьковая сортировка")
 * @author Anton Kozytskyi
 */
public class BubbleSort {

  /**
   * Точка входа в программу
   * @param args аргументы командной строки (если переданы)
   */
  public static void main(String[] args) {
    // массив, который мы будем сортировать, состоящий из восьми значений
    int[] numbers = {57, 2, 36, 12, 8, 133, 86, 72};
    // создаем экземпляр класса, так как далее мы будем вызывать его нестатические методы
    BubbleSort bubbleSort = new BubbleSort();

    // печатаем оригинальный (неотсортированный) массив
    System.out.println("Оригинальный массив (до сортировки):");
    print(numbers);

    // простой алгоритм (самый банальный, без оптимизаций)
    int[] numbersForSimpleSort = numbers.clone();
    bubbleSort.simpleSort(numbersForSimpleSort);
    System.out.println("Отсортированный массив (простой алгоритм):");
    print(numbersForSimpleSort);

    // улучшенный алгоритм (как только мы понимаем, что в массиве уже нечего сортировать,
    // мы завершаем сортировку)
    int[] numbersForImprovedSort = numbers.clone();
    bubbleSort.improvedSort(numbersForImprovedSort);
    System.out.println("Отсортированный массив (улучшенный алгоритм):");
    print(numbersForImprovedSort);

    // финальное решение (мы учитываем различные факторы и оптимизируем алгоритм)
    int[] numbersForFinalSort = numbers.clone();
    bubbleSort.sort(numbersForFinalSort);
    System.out.println("Отсортированный массив (финальная версия):");
    print(numbersForFinalSort);
  }

  /**
   * Здесь мы просто проходим про всему массиву от начала и до конца. Этот алгоритм самый простой,
   * так как в нем отсутствуют оптимизации. О каких оптимизациях мы говорим? Представьте ситуации:
   *
   * <ul>
   *   <li>в массиве все значения уже отсортированы;
   *   <li>в массиве значения не отсортированы, но для того, чтобы их отсортировать по возрастанию
   *       нам необязательно проходить по всему массиву (например, после прохода половины или 2/3
   *       массива значения уже становятся отсортированными);
   * </ul>
   *
   * Ниже я привожу пример, как меняется массив от итерации к итерации. Как вы видите, уже после
   * третьей итерации наш массив становится отсортированным. В этом алгоритме мы учитываем
   * наихудший вариант, когда нужно пройти максимальное количество раз, чтобы гарантированно
   * отсортировать все значения. Но такие ситуации бывают не всегда
   *
   * <pre>
   *   [57, 2, 36, 12, 8, 133, 86, 72]
   *   [2, 36, 12, 8, 57, 86, 72, 133]
   *   [2, 12, 8, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   * </pre>
   *
   * @param numbers массив, который мы будем сортировать
   */
  void simpleSort(int[] numbers) {
    // ВНЕШНИЙ ЦИКЛ: нам нужно столько раз выполнить внутренний цикл, чтобы отсортировать все
    // элементы массива. Чтобы сделать это гарантированно, нам нужно пройти количество раз, равное
    // длине массива (смотрите диаграмму, чтобы понять это визуально)
    for (int i = 0; i < numbers.length - 1; i++) {

      // ВНУТРЕННИЙ ЦИКЛ: проход по самим элементам массива [57, 2, 36, 12, 8, 133, 86, 72];
      // так как мы сравниваем текущий элемент со следующим, то нам нужно проходить не по всей
      // его длине, а длине, уменьшенной на один (чтобы не выйти на границы массива)
      for (int j = 0; j < numbers.length - 1; j++) {

        // здесь мы сравниваем текущий элемента (j) массива со следующим (j + 1);
        // если он больше, то меняем элементы местами
        if (numbers[j] > numbers[j + 1]) {

          // чтобы поменять элементы массива местами, нам нужно запомнить один из элементов
          // во временную переменную
          int buffer = numbers[j + 1];
          numbers[j + 1] = numbers[j];
          numbers[j] = buffer;
        }
      }

      // раскомментируйте строку ниже, если хотите увидеть как меняется массив от итерации к итерации
      // print(numbers);
    }
  }

  /**
   * Это улучшенный алгоритм сортировки. В этом алгоритме мы учитываем следующее: остались ли еще в
   * нашем массиве неотсортированные элементы. Если таковых нет, то мы выходим из цикла, там самым
   * экономя ресурс компьютера и сокращая время на выполнение сортировки.
   *
   * <p>Как мы понимаем, что все элементы уже отсортированы? Мы создаем булево значение и каждый
   * раз, в конце цикла, проверяем: истинное оно или ложное. Само же значение мы выставляем в
   * зависимости от того, сортируем ли мы что-то в цикле или уже нет. То есть, если мы делаем
   * проход, который ни разу ничего не изменил, то мы полагаем, что сортировать больше нечего.
   *
   * <p>Вот так выглядит изменение массива от итерации к итерации:
   *
   * <pre>
   *   [2, 36, 12, 8, 57, 86, 72, 133]
   *   [2, 12, 8, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   * </pre>
   *
   * Как видите, наш алгоритм делает всего три итерации и затем завершает выполнение. В прошлом
   * алгоритме мы делали целых восемь итераций.
   *
   * @param numbers массив, который мы будем сортировать
   */
  void improvedSort(int[] numbers) {
    for (int i = 0; i < numbers.length - 1; i++) {
      boolean isSorted = true;

      for (int j = 0; j < numbers.length - 1; j++) {
        if (numbers[j] > numbers[j + 1]) {
          int buffer = numbers[j + 1];
          numbers[j + 1] = numbers[j];
          numbers[j] = buffer;

          isSorted = false;
        }
      }

      if (isSorted) {
        break;
      }

      // раскомментируйте строку ниже, если хотите увидеть как меняется массив от итерации к итерации
      // print(numbers);
    }
  }

  /**
   * Это улучшенная версия предыдущего алгоритма и она же финальная. Что мы здесь улучшили? Давайте
   * для начала еще раз посмотрим на то, как меняется массив от итерации к итерации:
   *
   * <pre>
   *   [2, 36, 12, 8, 57, 86, 72, 133]
   *   [2, 12, 8, 36, 57, 72, 86, 133]
   *   [2, 8, 12, 36, 57, 72, 86, 133]
   * </pre>
   *
   * Если вы внимательно присмотритесь к элементам массива, то заметите что от итерации к итерации
   * самые последние элементы с каждой новой итерацией остаются постоянными - то есть, нет смысла
   * ходить в цикле (во внутреннем цикле) до самого конца.
   *
   * <p>После оптимизации мы получаем такой результат:
   *
   * <pre>
   *   [2, 36, 12, 8, 57, 86, 72]
   *   [2, 12, 8, 36, 57, 72]
   *   [2, 8, 12, 36, 57]
   * </pre>
   *
   * Как мы сделали оптимизацию? Каждый раз, во внутреннем цикле, в условном блоке мы запоминаем
   * последний индекс, где мы делали перестановку элементов. Мы принимаем этот индекс за крайний,
   * после которого уже все элементы отсортированы. А так как у нас "пузырьковая сортировка", то
   * самыми первыми сортируются именно самые последние элементы в массиве.
   *
   * @param numbers массив, который мы будем сортировать
   */
  void sort(int[] numbers) {
    int lastIndexCount = numbers.length - 1;

    for (int i = 0; i < numbers.length; i++) {
      boolean isSorted = true;
      int lastIndex = numbers.length - 1;

      for (int j = 0; j < lastIndexCount; j++) {
        if (numbers[j] > numbers[j + 1]) {
          int buffer = numbers[j + 1];
          numbers[j + 1] = numbers[j];
          numbers[j] = buffer;

          isSorted = false;
          lastIndex = j;
        }

        // раскомментируйте эти строки, если хотите увидеть элементы, по которым проходит наш
        // внутренний цикл
        // if (j == 0) System.out.print("[");
        // System.out.print(numbers[j] + ((j == lastIndexCount - 1) ? "" : ", "));
        // if (j == lastIndexCount - 1) System.out.println("]");
      }

      lastIndexCount = lastIndex;

      if (isSorted) {
        break;
      }

      // раскомментируйте строку ниже, если хотите увидеть как меняется массив от итерации к итерации
      // print(numbers);
    }
  }

  /**
   * Печатает массив как одну строку, где каждое значение отделено от другого запятой. В самом
   * начале и в самом конце метод выводит квадратные скобки.
   * @param numbers массив, который мы выводим на экран
   */
  private static void print(int[] numbers) {
    System.out.print("[");

    for (int i = 0; i < numbers.length; i++) {
      System.out.print(numbers[i]);

      if (i < numbers.length - 1) {
        System.out.print(", ");
      }
    }

    System.out.println("]");
  }
}