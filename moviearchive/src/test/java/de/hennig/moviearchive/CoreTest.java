package de.hennig.moviearchive;

import de.hennig.moviearchive.util.DateTimeUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class CoreTest {

    @Test(dataProvider = "provideBankAccountStatusScenarios")
    public void testYearCheck(String year, boolean isValid) {
        assertEquals(DateTimeUtil.isValidYear(year), isValid);
    }

    @DataProvider
    private Object[][] provideBankAccountStatusScenarios() {
        return DataProviderBuilder.init()
                .add("1234", true)
                .add("2017", true)
                .add("12344", false)
                .add("5004", false)
                .add("12a4", false)
                .build();
    }
}

