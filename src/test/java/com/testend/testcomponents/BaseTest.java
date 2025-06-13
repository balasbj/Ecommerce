package com.testend.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testend.pageobjectmodel.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeTest() throws IOException {



		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/testend/resources/GlobalProperties.properties");
		prop.load(fis);

		String browsername =System.getProperty("browser") != null? System.getProperty("browser") : prop.getProperty("browser");
		

		if(browsername.contains("chrome")) {
			driver = new ChromeDriver();

		}
		else if(browsername.equalsIgnoreCase("firefox")){

			driver = new FirefoxDriver();

		}
		else if(browsername.equalsIgnoreCase("edge")){

			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}


	@BeforeMethod
	public LandingPage launchApplication() throws IOException{

		driver = initializeTest();

		landingpage = new LandingPage(driver);

		landingpage.GoTo();

		return landingpage;
	}




	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot)driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File file  = new File(System.getProperty("user.dir") + "//reports//" +testCaseName+".png");

		FileUtils.copyFile(source, file);

		return System.getProperty("user.dir") + "//reports//" +testCaseName+".png";
	}













	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// Read JSON to String
		String jsonContent = FileUtils.readFileToString(
				new File(filePath),
				StandardCharsets.UTF_8);

		jsonContent = jsonContent.replace('\u00A0', ' ');


		// Convert JSON string to List of Maps
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {}) ;

		return data;

	}

	@AfterMethod
	public void teardown() {

		driver.close();
	}

}
