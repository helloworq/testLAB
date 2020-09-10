import com.sun.jna.platform.KeyboardUtils;
import com.sun.xml.bind.v2.model.core.Adapter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class SnakeClient extends JFrame {
    //蛇的初始坐标
    static int StartPosition_X = 40;
    static int StartPosition_Y = 40;
    //蛇身大小
    static int snakeSIZE=20;
    //普通食物，后期可扩展为数组达成多个食物
    static normalFood food;
    //食物是否被吃
    static int foodAlive=0;
    //蛇颜色
    static Color snakeColor=Color.red;
    //初始往右走
    static char snakeDirection='d';
    //使用链表维护蛇身节点数据
    static LinkedList<SnakeNode> snakeList=new LinkedList<>();
    //游戏速度
    final static int GAME_SPEED=200;
    //蛇身图片
    //Image normalFood= ImageIO.read(new FileInputStream("C:\\Users\\12733\\Desktop\\food.jpg"));
    //食物图片
    //Image snakeBody=ImageIO.read(new FileInputStream("C:\\Users\\12733\\Desktop\\a.jpg"));

    public SnakeClient() throws IOException {}

    public static void main(String[] args) throws InterruptedException, IOException {
        SnakeClient snakeClient = new SnakeClient();
        snakeClient.lanch();
    }


    public void lanch() throws InterruptedException {
        this.setBounds(200,100, 400,400); //设置窗体大小和位置
        this.setVisible(true); //使用该属性才能显示窗体
        //实现程序运行关闭的功能
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(-1);
            }
        });

        //蛇链表加入头节点
        SnakeNode snakeNode=new SnakeNode();
        snakeNode.position_X=StartPosition_X;
        snakeNode.position_Y=StartPosition_Y;
        snakeList.addFirst(snakeNode);
        //调用键盘
        addKeyListener(new KeyMointer());
        //开始
        while (true) {
            repaint();
            Thread.sleep(GAME_SPEED);
        }
    }

    static void keyPress(KeyEvent e){
        //e.getKeyChar()
        //根据键入的数据改变蛇方向控制符
        switch (e.getKeyChar()){
            case 'w' :{
                if(SnakeClient.snakeDirection=='a'||SnakeClient.snakeDirection=='d')
                    SnakeClient.snakeDirection='w';
            }break;
            case 'a' :{
                if(SnakeClient.snakeDirection=='w'||SnakeClient.snakeDirection=='s')
                    SnakeClient.snakeDirection='a';
            }break;
            case 's' :{
                if(SnakeClient.snakeDirection=='a'||SnakeClient.snakeDirection=='d')
                    SnakeClient.snakeDirection='s';
            }break;
            case 'd' :{
                if(SnakeClient.snakeDirection=='w'||SnakeClient.snakeDirection=='s')
                    SnakeClient.snakeDirection='d';
            }break;
        }
    }

    static void snakeMove(){
        //蛇移动采取增头去尾方法,同时在增头去尾的过程中判断蛇头是否与食物坐标重合，如果重合则不去尾
        //达到蛇身增加的目的
        switch (SnakeClient.snakeDirection){
            case 'w':{
                //创建新头节点
                SnakeNode newHeadSnakeNode=new SnakeNode();
                newHeadSnakeNode.position_X=(snakeList.getFirst().position_X+0);
                newHeadSnakeNode.position_Y=(snakeList.getFirst().position_Y-20);
                //将新节点加入链表
                snakeList.addFirst(newHeadSnakeNode);
                //如果没吃到就去除尾节点达到增加蛇身的目的
                if(isEatFood(newHeadSnakeNode.position_X,newHeadSnakeNode.position_Y, food.position_X,food.position_Y))
                    foodAlive--;//迟到了则食物存活符减一
                else
                    snakeList.removeLast();
            } break;
            case 'a':{
                //创建新头节点
                SnakeNode newHeadSnakeNode=new SnakeNode();
                newHeadSnakeNode.position_X=(snakeList.getFirst().position_X-20);
                newHeadSnakeNode.position_Y=(snakeList.getFirst().position_Y+0);
                //将新节点加入链表
                snakeList.addFirst(newHeadSnakeNode);
                //如果没吃到就去除尾节点达到增加蛇身的目的
                if(isEatFood(newHeadSnakeNode.position_X,newHeadSnakeNode.position_Y, food.position_X,food.position_Y))
                    foodAlive--;//迟到了则食物存活符减一
                else
                    snakeList.removeLast();
            } break;
            case 's':{
                //创建新头节点
                SnakeNode newHeadSnakeNode=new SnakeNode();
                newHeadSnakeNode.position_X=(snakeList.getFirst().position_X+0);
                newHeadSnakeNode.position_Y=(snakeList.getFirst().position_Y+20);
                //将新节点加入链表
                snakeList.addFirst(newHeadSnakeNode);
                //如果没吃到就去除尾节点达到增加蛇身的目的
                if(isEatFood(newHeadSnakeNode.position_X,newHeadSnakeNode.position_Y, food.position_X,food.position_Y))
                    foodAlive--;//迟到了则食物存活符减一
                else
                   snakeList.removeLast();
            } break;
            case 'd':{
                //创建新头节点
                SnakeNode newHeadSnakeNode=new SnakeNode();
                newHeadSnakeNode.position_X=(snakeList.getFirst().position_X+20);
                newHeadSnakeNode.position_Y=(snakeList.getFirst().position_Y+0);
                //将新节点加入链表
                snakeList.addFirst(newHeadSnakeNode);
                //如果没吃到就去除尾节点达到增加蛇身的目的
                if(isEatFood(newHeadSnakeNode.position_X,newHeadSnakeNode.position_Y, food.position_X,food.position_Y))
                    foodAlive--;//迟到了则食物存活符减一
                else
                    snakeList.removeLast();
            } break;
        }
    }

    static boolean isEatFood(int SnakeX,int SnakeY,int FoodX,int FoodY){
        //蛇吃东西身体变长的处理方式为移动时不去除尾节点
        //坐标重合则判定为吃到食物
        if(SnakeX==FoodX&&SnakeY==FoodY)
            return true;
        else
            return false;
    }

    static void creatFood(){
        if(foodAlive==0) {//如果没有食物则创建
            food = new normalFood();
            foodAlive++;
        }
    }

    //画图函数，这个是重写paint，Frame类的内置函数
    @Override
    public void paint(Graphics g){
        //逐个读取链表中的节点数据，循环打印节点
        //清屏
        g.clearRect(0, 0, 400, 400);
        SnakeClient.snakeMove();
        SnakeClient.creatFood();
        //画蛇
        for (int i = 0; i < snakeList.size(); i++) {
            g.setColor(snakeList.get(i).snakeColor);
            g.fillRect(snakeList.get(i).position_X,snakeList.get(i).position_Y,snakeSIZE,snakeSIZE);
            //g.drawImage(snakeBody,snakeList.get(i).position_X,snakeList.get(i).position_Y,snakeSIZE,snakeSIZE,null);
        }
        //画食物
        g.fillRect(food.position_X,food.position_Y,snakeSIZE,snakeSIZE);
        //g.drawImage(normalFood,food.position_X,food.position_Y,snakeSIZE,snakeSIZE,null);
    }
}

//键盘控制
class KeyMointer extends KeyAdapter {
    public void keyPressed(KeyEvent e){
        SnakeClient.keyPress(e);
    }
}

class SnakeNode{
    int position_X;
    int position_Y;
    static Color snakeColor=SnakeClient.snakeColor;
}

class normalFood{
    public normalFood(){
        position_X = ((int) (Math.random() * 20+1)) * 20;
        position_Y = ((int) (Math.random() * 20+1)) * 20;
    }
    static int position_X;
    static int position_Y;
    static Color FoodColor=Color.GREEN;
    static int awardGrades=1;//吃到后的奖励分数
}