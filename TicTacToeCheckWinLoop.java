import java.util.Random;
import java.util.Scanner;

public class TicTacToeCheckWinLoop {

    public static String [][] field;

    public static void main(String[] args) {

        createField();
        drawField();
        gaming();

    }

    public static void createField(){
        field = new String[5][5];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = "-";
            }
        }
    }

    public static void drawField(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void playerMove() {
        Scanner scan = new Scanner(System.in);
        int h, v;

        do {
            System.out.println("Введите номер поля по горизонтали (значение от 1 до 5):");
            h = scan.nextInt() - 1;
            System.out.println("Введите номер поля по вертикали (значение от 1 до 5):");
            v = scan.nextInt() - 1;
        }while(h < 0 || h > 5 || v < 0 || v > 5 || field[h][v] == "X" || field[h][v] == "O");

        field[h][v] = "X";

    }


    public static void botMove() {
        int a, b;

        Random rand = new Random();

        do{
            a = rand.nextInt(5);
            b = rand.nextInt(5);
        } while (field[a][b] == "X" || field[a][b] == "O");

        field[a][b] = "O";

    }

    public static void gaming() {

        while (true){
            playerMove();
            drawField();
            if (checkWinLines("X") || checkWinDials("X")){
                System.out.println("Игрок выиграл");
                break;
            }
            if(checkFullField()){
                System.out.println("Ничья");
                break;
            }
            botMove();
            drawField();
            if (checkWinLines("O") || checkWinDials("O")){
                System.out.println("Компьютер выиграл");
                break;
            }
            if(checkFullField()){
                System.out.println("Ничья");
                break;
            }
        }
    }

    public static boolean checkFullField(){
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++){
                if(field[i][j] == "-"){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWinLines(String a){
        int check = 0;
        for(int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == a) {
                    for (int n = 0; n < field.length; n++) {
                        if (field[i][n] == a) {
                            check++;
                            if (check == field.length) {
                                return true;
                            }
                        }
                    }
                    check = 0;
                    for (int m = 0; m < field.length; m++) {
                        if (field[m][j] == a) {
                            check++;
                            if (check == field.length) {
                                return true;
                            }
                        }
                    }
                    check = 0;
                }
            }
        }
        return false;
    }

    public static boolean checkWinDials(String a){
        int check1 = 0;
        int check2 = 0;
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++){
                if(i == j){
                    if(field[i][j] == a){
                        check1++;
                    }
                }
                if(i + j == field.length - 1){
                    if(field[i][j] == a){
                        check2++;
                    }
                }
            }
        }
        if(check1 == field.length || check2 == field.length){
            return true;
        }
        check1 = 0;
        check2 = 0;
        return false;
    }

}
