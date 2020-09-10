package AnnotionTest;


public class AnnotionTestOne {
    public static void main(String[] args) {
        testm();
    }

    @Mytest(id = 1)
    public static void testm(){
        System.out.println("testm");
    }
}
