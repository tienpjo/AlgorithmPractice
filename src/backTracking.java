
public class backTracking {
    static int count;
    static int n;

    static int[] X = new int[4];
    public static boolean check(int v, int k){
          return X[k-1] + v <=1;
    }

    public static void printMonitor(){
        count ++;
        System.out.printf("Solution%d: ", count);
        for(int i = 1; i <= n; i++){
            System.out.printf("%d",X[i]);
        }
        System.out.printf("%n");
    }
    public static void Try(int k){
        for(int v = 0; v <= 1; v++){
            if(check(v,k)){
                X[k] = v;

                if(k==n){
                    printMonitor();
                }
                else {
                    Try(k+1);
                }
            }
        }
    }
    public static void main(String[] args) {
        n = 3;
        count = 0;
        Try(1);
    }
}