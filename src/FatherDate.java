import java.util.Date;

public class FatherDate {
    String date;
    int innerTimes=0;
    public FatherDate(){
        innerTimes++;
        System.out.println("FatherDate()构造器--"+"date--"+date+"--innerTimes--"+innerTimes);
    }

    public FatherDate(String str){
        this.date=str;
        innerTimes++;
        System.out.println("FatherDate()构造器--"+"date--"+date+"--innerTimes--"+innerTimes);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
