package dubboDemo.dubboImp;

import dubboDemo.dubboInterface.ProviderService;

public class ProviderServiceImpl implements ProviderService {
    @Override
    public String SayHello(String word) {
        return word;
    }
}
