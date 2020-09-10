package Proxytest;

import java.lang.reflect.Proxy;

public class BuyDrinkProxy implements BuySomething {
    @Override
    public void buy() {
        System.out.println("...去大润发帮你看看有没有饮料...");
        new BuyDrink().buy();
        System.out.println("...买到了...");
    }
}
