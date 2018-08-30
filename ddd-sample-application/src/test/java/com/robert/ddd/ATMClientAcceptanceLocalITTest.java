package com.robert.ddd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty", "html:target/cucumber"},
        glue = {
                "com.robert.ddd"
        },
        features = "src/test/resources"
)
public class ATMClientAcceptanceLocalITTest {

}
