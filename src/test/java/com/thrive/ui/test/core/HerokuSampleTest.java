package com.thrive.ui.test.core;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HerokuSampleTest {
	
	@Test
	public void sampleTestForHerokuPipeline() {
		Assert.assertTrue(true);
		System.out.println("Great!! Test Executed From Heroku");
	}
	
	

}
