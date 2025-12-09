package tasks.epsilon;

public class Main {
    //q - порядок, t - кол-во разрядов в мантиссе
    //eps - машинный эпсилон
    //формула: (q^(1-t))/2
    static double eps(int q, int t){
        return (Math.pow(q,(1-t)))/2;
    }

    public static void main(String[] args){
        int i = 1;
        while (eps(2,i)+1.0 != 1.0) i++;
        System.out.println(eps(2,i));
    }
}
