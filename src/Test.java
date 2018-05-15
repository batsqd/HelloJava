public class Test {
    public static void main(String args[]){
        int sum=0;
        int a=1;
        for(int i=1;i<=100;i++){
            sum+=i;
            f1();
        }
        sum=100;
        for(int j=0;j<10;j++){
            sum+=j;
        }
        System.out.println("sum="+sum);
    }
    public static void f1(){
        int c=0;
        for(int j=0;j<10;j++){
            c+=j;
        }
        System.out.println(c);
    }
}
