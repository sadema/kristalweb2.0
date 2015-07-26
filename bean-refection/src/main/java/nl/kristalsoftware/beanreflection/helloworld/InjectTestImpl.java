package nl.kristalsoftware.beanreflection.helloworld;

/**
 * Created by sjoerdadema on 25-06-15.
 */
public class InjectTestImpl implements InjectTest {

    private final String testMsg = "This is a test";

    public String getTestMsg() {
        return testMsg;
    }

}
