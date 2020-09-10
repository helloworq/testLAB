package Pattern.SimpleFactory;

public interface IOperationFactory {
    CalOperation getOperation(String operation);
}
