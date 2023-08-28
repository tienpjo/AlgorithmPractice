import java.util.Scanner;

public class travelMan {
    static int n; // so diem di tu thanh pho i den thanh pho j
    static int m;// số chặng có thể đi đc
    final static int _COST = 1000000; // define chi phi neu k co
    static int[] city = new int[30]; // thành phố thứ n
    static int[][] cost = new int[30][30]; //  chi phi tại thành phố [i,j]
    static int trip_cost;   // chi phí để đi từ thành phố i đến j
    static  int trip_cost_min; // chi phí nhỏ nhất

    static  int[] mark = new int[10];

    /** Read data
     * dòng đầu tiên n là số thành phố, m số chặng mà có thể đi đc
     * dòng tiếp theo là đi từ thành phố i đêến thành phố j với chi phí m
     *   2 2
     *   1 2 3
     *   2 1 2
     */


    static void inputData(){
        Scanner input = new Scanner(System.in);

        n = input.nextInt();
        m = input.nextInt();
        /*
         * nếu không có chi phi trên quãng đường thì gán chi phí bằng _COST
         */
//        for(int i = 1; i <=n ; i++){
//            for(int j =1; j<=n; j++)
//                cost[i][j] = _COST;
//        }
        /*
        * trong trường hợp data đầy đủ duyệt toàn các giá trị
         */
        for(int k = 1 ; k <= m ; k ++){
            int i,j,d;
            i = input.nextInt();
            j = input.nextInt();
            d = input.nextInt();
            cost[i][j] = d;
            cost[j][i] = d;
        }
        //System.out.println(cost[2][2]);
    }

    /* Ghi nhận 1 kỷ lục
    * chi phí đi từ điểm n về điểm 1
    * nếu hành trình mà nhỏ hơn ký lục đã được update trc đó
    */
    static  void updateBest(){
        if(trip_cost + cost[city[n]][city[1]] < trip_cost_min) {
            trip_cost_min = trip_cost + cost[city[n]][city[1]];
        }
    }
    /* Hàm thử tất cả các giá cho city
    * khi đi từ city[1] đến city[k-1] thì cộng thêm với hành trình city[k-1] ->  city[k]
    * đánh dấu lại là point đã được đi qua bằng mark
    * update lại kỷ lục nếu đi đến đc n điểm, còn không sẽ thử với giá trị k+1
    *
    */
    static void Try(int k){
        for(int v = 1; v <= n; v++){
            if(mark[v] == 0){
                city[k] = v;
                trip_cost += cost[city[k-1]][city[k]];
                mark[v] = 1;

                if(k == n) {
                    updateBest();
                }
                else {
                    Try(k +1);
                }
                trip_cost = trip_cost - cost[city[k-1]][city[k]];
            }
        }
    }
    public static void main(String[] args){
            inputData();
            // khởi taạo chưa đi đc thành phố nào
            trip_cost = 0;
            // các thành phố chưa đc đánh dấu là đã đi qua
            for(int i = 0; i<=n; i++) mark[i] = 0;
            // khởi tạo và đánh dấu đã đi qua thành phố xuất phát là 1
            city[1] = 1; mark[1] =1 ;

            // mặc định giá trị nhở nhất tim thấy
            trip_cost_min = _COST;
            // bắt đầu try từ thành phố thứ 2
            Try(2);

            System.out.printf("Chi phí nhỏ nhất: %d \n",trip_cost_min);


    }
}
