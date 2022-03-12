package com.viola.backend.voilabackend;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Cucumber;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Cucumber
@IncludeEngines("cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.viola.backend.voilabackend.steps")

public class CucumberTest {

}