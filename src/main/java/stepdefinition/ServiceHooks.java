package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.net.MalformedURLException;
public class ServiceHooks {


    @Before
    public void initTest() throws MalformedURLException {

    }

    @After
    public void afterHooks(Scenario scenario){

    }
}
