package Pattern.Factory;

public class DeleteOperationFactory implements ICalFactory{

    @Override
    public CalOperation creatCalOperation() {
        return new DeleteCalOperation();
    }
}
