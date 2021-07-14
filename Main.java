public class Main {

// 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
//    при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
// 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
//   Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
//   должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
//   3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException, и вывести результат расчета.



    public static void main(String[] args) {


        String[][] array = new String[][] {
            {"3", "4", "9", "34"},
            {"13", "43", "19", "83"},
            {"23", "34", "91", "11"},
            {"33", "14", "29", "99"}
        };

        sumArrayElements(array);

    }



  public static void sumArrayElements(String[][] array) {
      checkSize(array.length);

      for (int i = 0; i < array.length; i++) {
          checkSize(array[i].length);
      }

      int sum = 0;
      for (int i = 0; i < array.length; i++) {
          for (int j = 0; j < array[i].length; j++) {
              try {
                  sum += Integer.parseInt(array[i][j]);
              } catch (NumberFormatException e) {
                  String message = String.format("Элемент массива array[%s][%s] нельзя преобразовать в число", i, j);
                  throw new MyArrayDataException(message, e);
              }
          }
      }
        System.out.println("Сумма элементов массива = " + sum);
    }


    private static void checkSize(int length){
            if(length != 4){
                throw new MySizeArrayException("Размер массива должен быть 4х4");
            }
    }


}
