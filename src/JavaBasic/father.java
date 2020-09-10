package JavaBasic;

import java.util.logging.Logger;

public class father {
    public father(String fatherSaying1){
        this.fatherSaying=fatherSaying1;
    }
    String fatherSaying="i am your father";
    public String fatherSay(){
        System.out.println(fatherSaying);
        return fatherSaying;
    }
}
