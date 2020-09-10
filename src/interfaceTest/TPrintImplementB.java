package interfaceTest;

public class TPrintImplementB implements TPrint{
    @Override
    public void print() {
        System.out.println("Class TPrintImplementB Method print()");
    }

    @Override
    public void doJob() {
        System.out.println("Class TPrintImplementB Method doJob()");
    }
}
