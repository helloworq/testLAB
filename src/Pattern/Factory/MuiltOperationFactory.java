package Pattern.Factory;

public class MuiltOperationFactory implements ICalFactory {
    @Override
    public CalOperation creatCalOperation() {
        return new MuiltCalOperation();
    }
}
