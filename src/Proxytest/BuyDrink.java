package Proxytest;

public class BuyDrink implements BuySomething {
    @Override
    public void buy() {
        System.out.println("我要买饮料");
    }
}
