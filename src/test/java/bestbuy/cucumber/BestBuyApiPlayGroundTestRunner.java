package bestbuy.cucumber;

import bestbuy.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Jay
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/java/resources/feature/",
        glue = "bestbuy"
)
public class BestBuyApiPlayGroundTestRunner extends TestBase {

}
