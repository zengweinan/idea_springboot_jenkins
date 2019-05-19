package com.pactera.controller;

/**
 * Created by bo on 2018/12/17.
 */
public class TestController {

    public static void main(String[] args) {
       /* String[] atp = {"Rafael Nadal", "Novak Djokovic", "Stanisdlas " + "Wawraaaddinka", "David Ferrer", "Roger " +
                "Federer", "Anddy " + "Murray", "Tomas Berdych", "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);
        players.forEach((player) -> System.out.print(player + "; "));
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        Runnable r1 = null;
        HashMap<String, Object> objects = new HashMap<>();
        if (1 > 0) {
            System.out.println("33333");
            r1 = () -> {
                objects.put("111", "222");
            };
            for (int i = 0; i <= 1000; i++) {
                System.out.println(i + "+" + i + "");
            }
        }
        r1.run();
        System.out.println("填报");*/
        //   Comparator<Lambda> lambada = (Lambda a3, Lambda a2) -> a3.getA().compareTo(a3.getA());
        //System.out.println(lambda);
        //System.out.println(222);

        try {
            test();
           /* if (3 / 2 > 0) {
                throw new RuntimeException("hahha");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public static void test() {
        try{
            if (3 > 2) {
                throw new RuntimeException("被除数不能为0");
            }
        }catch (Exception e){
              throw  new RuntimeException("nihahah");
        }

    }


}
