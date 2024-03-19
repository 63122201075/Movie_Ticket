package com.example.movie_ticket;

public class test {
    static int x = 10;
    static int y = 5;

    public static void main(String[] args) {


//        i1 = 10;
//        i2 = 20;
//        i3 = 2;

        calculate(10,20,2);
        calculate(5,4,7);


//        int j = 5;
//        System.out.println(j);
//        String j = getName();
//        System.out.println(j);
//        getName();
//        System.out.println(getName());
//        System.out.println();
//        getNum();
//        String j= getNameandNum("SSS",555);
//        System.out.println(j);


    }

    public static void calculate(
            int i1,
            int i2,
            int i3) {
        int sum = i1 * i2;
        sum = sum / i3;
        System.out.println(sum);
    }

    public static String getName() {
        String name = "sippakorn";
        return "124    " + name;
    }

    public static void getNum() {
        int sum = x + y;
        System.out.println(sum);
    }

    public static String getNameandNum(String name, Integer num) {
        num = num + 200;
        System.out.println(name + "...." + num);
        return name + "......" + num;
    }


}
