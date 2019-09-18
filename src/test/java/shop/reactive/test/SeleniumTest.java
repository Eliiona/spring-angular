package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {
    private ChromeDriver driver;

    private final int port = 4200;

    private final String domain = "http://localhost:"+port;
    private final String itemsPath = "/items";
    private final String addItemPath = "/additem";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(domain);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test01_MainPage() {
        assertThat(driver.findElementById("title-tag").getText(), is("Grocery List"));
    }
}
