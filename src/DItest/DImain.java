package DItest;

import java.util.ArrayList;

public class DImain {
    public static void main(String[] args) {
        //SugarConsumer sugarConsumer=new SugarConsumer();
        //FuritSugarProducer furitSugarProducer=new FuritSugarProducer();
        //sugarConsumer.eat(furitSugarProducer);
        /**
         * 1  2  3  4
         *12 13 14  5
         *11 16 15  6
         *10  9  8  7
         */
        //+4,+3,-3,-2,+2,+1,-1
        //横,纵,横,纵,横,纵,横,纵
        //0,0 0,1 0,2 0,3
        //1,3 2,3 3,3
        //3,2 3,1 3,0
        //2,0 1,0
        //1,1 1,2
        //2,2 2,1
        int length=4;
        creatMaze(length);
    }

    static void creatMaze(int length){
        int[][] Maze=new int[length][length];

    }
}





//两个类，一个类负责吃糖，一个类负责生产糖
class SugarConsumer{
    public static void eat(FuritSugarProducer furitSugarProducer){
        System.out.println("吃掉一颗水果糖");
    }
}

class FuritSugarProducer{

}