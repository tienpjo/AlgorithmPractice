public class Soduku {
    final static int _VALUES = 9;
    static int[][] Sudoku = new int[9][9];
    static int[][] markC = new int[9][10];  // ex markC[i][v] = 1 means that v appeared on column c

    static  int[][] markR = new int[9][10]; // ex markC[i][v] = 1 means that v appeared on row c
    static  int[][][] markS = new int[10][10][10];
    static boolean check(int value, int row, int column){
        if(markC[column][value] == 1) return false; // kiểm tra giá trị ở cột column đã có hay chưa nếu có rồi thì trả về false
        if(markR[row][value] == 1) return false; // kiểm tra giá trị ở hàng đã có hay chưa nếu tồn tại rồi thì trả về false
        if(markS[row][column][value] == 1) return false; // kiểm tra trong 1/9 ô vuông nhỏ đã có giá trị hay chưa
        return true;
    }

    static void printSolution(){
        for(int i=0; i< _VALUES -1; i++){
            for (int j=0; j< _VALUES - 1; j++){
                System.out.printf("%d ",Sudoku[i][j]);
            }
            System.out.printf("\n");
        }
        System.out.printf("-----------------------\n");
    }
    static void Try(int row, int column){
        for(int value = 1; value <= _VALUES; value++){
            if(check(value, row, column)){
                Sudoku[row] [column] = value;
                markR[row][value] = 1;
                markC[column][value] = 1;
                markS[row/3][column/3][value] = 1;
                if (row == 8 && column == 8) {
                    printSolution();
                }
                else {
                    if(column < 8) {
                        Try(row , column + 1);
                    }
                    else {
                        Try(row + 1, 0);
                    }
                }

                markR[row][value] = 0;
                markC[column][value] = 0;
                markS[row/3][column/3][value] =0;
            }

        }

    }
    public static void main(String[] args){
        for (int i =1; i<= _VALUES; i++) {
            for (int r = 0; r <= _VALUES -1; r++) {
                markR[r][i] = 0;
            }
            for (int c = 0; c < _VALUES -1; c++){
                markC[c][i] = 0;
            }
            for(int I = 0; I < 3 ; I ++){
                for (int J = 0; J < 3; J ++){
                    markS[I][J][i] = 0;
                }
            }

        }
        Try(0, 0);
    }

}
