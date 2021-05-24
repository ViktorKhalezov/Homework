public class HW_Lesson3 {

    public static void main(String[] args) {
        task1();
        System.out.println();
        task2();
        System.out.println();
        task3();
        System.out.println();
        task4();
        System.out.println();
        task5(5,3);
        System.out.println();
        task6();
        System.out.println();
        System.out.println(task7());
        System.out.println();
        task8(new int[] {5, 7, 8, 9, 11, 4, 6, 3, 2, 10, 22, 14}, 15);
    }


        public static void task1(){
            System.out.println("Первое задание:");
            int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == 1){
                    arr[i] = 0;
                    System.out.println("arr[" + i + "] = " + arr[i]);
                }else{
                    arr[i] = 1;
                    System.out.println("arr[" + i + "] = " + arr[i]);
                }
            }
        }

        public static void task2(){
            System.out.println("Второе задание:");
            int[] arr = new int[100];
            for(int i = 0; i < arr.length; i++){
                arr[i] = i + 1;
                System.out.println("arr[" + i + "] = " + arr[i]);
            }
        }

        public static void task3(){
            System.out.println("Третье задание:");
            int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
            for(int i = 0; i < arr.length; i++){
                if(arr[i] < 6){
                    arr[i] = arr[i] * 2;
                    System.out.println("arr[" + i + "] = " + arr[i]);
                }else{
                    System.out.println("arr[" + i + "] = " + arr[i]);
                }
            }
        }

        public static void task4(){
            System.out.println("Четвертое задание:");
            int[][] arr = new int[5][5];
            for(int i = 0; i < arr.length; i++){
                for (int j = 0; j < arr[i].length; j++){
                    if (i == j || i + j == arr.length - 1){
                        arr[i][j] = 1;
                        System.out.print(arr[i][j] + " ");
                    }else{
                        arr[i][j] = 0;
                        System.out.print(arr[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }

        public static int [] task5(int len, int initialValue){
            System.out.println("Пятое задание:");
            int[] arr = new int[len];
            for(int i = 0; i < len; i++){
                arr[i] = initialValue;
                System.out.println("arr[" + i + "] = " + arr[i]);
            }
            return arr;
        }

        public static void task6(){
            System.out.println("Шестое задание:");
            int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
            int max = arr[0];
            int min = arr[0];
            for(int i = 0; i < arr.length; i++){
                if(arr[i] > max){
                    max = arr[i];
                }else if(arr[i] < min){
                    min = arr[i];
                }
            }
            System.out.println("Массив: ");
            for(int j = 0; j < arr.length; j++){
                System.out.println("arr[" + "] = " + arr[j]);
            }
            System.out.println("Максимальное значение:" + max);
            System.out.println("Минимальное значение:" + min);
        }

        public static boolean task7(){
            System.out.println("Седьмое задание:");
            int[] arr = {2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1};
            int sumLeft = 0;
            int sumRight = 0;
            int sumArr = 0;
            int check = 0;
            for (int i = 0; i < arr.length; i++){
                sumArr += arr[i];
            }
            for(int j = 0; j < arr.length; j++){
                sumLeft += arr[j];
                sumRight = sumArr - sumLeft;
                if(sumLeft == sumRight){
                    check = 1;
                    break;
                }else{
                    check = 0;
                }
            }
            if (check == 1){
                return true;
            }else{
                return false;
            }
        }

          public static void task8(int[] arr, int n){
            System.out.println("Восьмое задание:");
            System.out.println("Массив:");
            for(int j = 0; j < arr.length; j++){
                System.out.print(arr[j] + " ");
            }
            System.out.println();
            System.out.println("При n > 0 - смещение вправо");
            System.out.println("При n < 0 - смещение влево");
            System.out.println("Новый массив:");
            int m = 0;
            for(int i = 0; i < arr.length; i++) {
                if (n == 0 || n == arr.length || n == -arr.length) {
                    System.out.print(arr[i] + " ");
                }
                if (n % arr.length == arr.length || n % arr.length == -arr.length) {
                    System.out.print(arr[i] + " ");
                }
                if (n > 0) {
                    if (n / arr.length >= 1) {
                        if (i - n % arr.length < 0) {
                            m = i + arr.length - n % arr.length;
                            System.out.print(arr[m] + " ");
                        }
                        if (i - n % arr.length >= 0) {
                            m = i - n % arr.length;
                            System.out.print(arr[m] + " ");
                        }
                    }
                    if (n / arr.length < 1) {
                        if (i - n < 0) {
                            m = i + arr.length - n;
                            System.out.print(arr[m] + " ");
                        }
                        if (i - n >= 0) {
                            m = i - n;
                            System.out.print(arr[m] + " ");
                        }
                    }
                }
                if (n < 0) {
                    if (n / arr.length <= -1) {
                        if (i - n % arr.length > arr.length - 1) {
                            m = i - (arr.length + n % arr.length);
                            System.out.print(arr[m] + " ");
                        }
                        if (i - n % arr.length <= arr.length - 1) {
                            m = i - n % arr.length;
                            System.out.print(arr[m] + " ");
                        }
                    }
                    if (n / arr.length > -1) {
                        if (i - n > arr.length - 1) {
                            m = i - (arr.length + n);
                            System.out.print(arr[m] + " ");
                        }
                        if (i - n <= arr.length - 1) {
                            m = i - n;
                            System.out.print(arr[m] + " ");
                        }
                    }
                }
            }
              System.out.println();
              // System.out.println(n % arr.length);
              // System.out.println(n / arr.length);
            }





}



