package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	static WebDriver driver;
	static String browser;
	static String url;

	public static void readConfig() {
		// Buffered , InputStream, fileReader,Scanner
		// helps to read any types of files

		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used " + browser);
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver init() {

		String browser = "chrome";
		readConfig();
		if (browser.equalsIgnoreCase("Chrome")) { // we have double == in java
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			// we use firefox browser
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public static void tearDown() {
		driver.close();
		driver.quit();
	}
}
