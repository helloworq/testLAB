package Pattern;

import Pattern.Factory.AddOperationFactory;
import Pattern.Factory.CalOperation;

public class PatternTestRunner {
    public static void main(String[] args) {
        /*
        //简单工厂模式
        CalOperation calOperation= new OperationFactoryImpl().getOperation("+");
        calOperation.setNumA(1);
        calOperation.setNumB(2);
        System.out.println(calOperation.calculate());
         */
        //工厂模式
        CalOperation calOperation=new AddOperationFactory().creatCalOperation();
        calOperation.setNumA(1);
        calOperation.setNumB(2);
        System.out.println(calOperation.calculate());
    }
}
