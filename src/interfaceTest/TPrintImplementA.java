package interfaceTest;

public class TPrintImplementA implements TPrint{
    @Override
    public void print() {
        System.out.println("Class TPrintImplementA Method print()");
    }

    @Override
    public void doJob() {
        System.out.println("Class TPrintImplementA Method doJob()");
    }
}
