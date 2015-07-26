package nl.kristalsoftware.beanreflection.helloworld;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Created by sjoerdadema on 25-06-15.
 */
public class HelloWorld {

    @Inject
    private InjectTest injectTest;

    @Inject
    private Logger log;

    private final String text = "Hello World";

    public String getText() {
        log.info("getText method");
        return injectTest.getTestMsg();
    }

}
