public class SonDate extends FatherDate{
    public SonDate(){
        super("sss");
        System.out.println("SonDate()构造器--"+"date--"+date+"--innerTimes--"+innerTimes);
    }
    static {
        System.out.println("SonDate里的静态字段被调用了！");
    }
}
