package UITests;

import SeleniumHelper.SeleniumHelper;
import UIPages.CyberAttackPage;
import Utils.Utils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class CyberAttackTest extends SeleniumHelper {
    public CyberAttackPage cyberAttackPage;
    public Utils utils;

    @BeforeClass
    public void setup() throws IOException {
        init();
        cyberAttackPage = new CyberAttackPage(webDriver);
        utils = new Utils();
    }

    @Test
    public void A_SpecificFilterWithName() {
        cyberAttackPage.setFilterData(getDataName().get(0));

        // Assert that rows should display filter data
        Assert.assertNotNull(getDataCases().get(0));
        Assert.assertNotNull(getDataName().get(0));
        Assert.assertNotNull(getDataImpact().get(0));
        Assert.assertNotNull(getDataComplexity().get(0));
    }

    @Test
    public void B_SpecificFilterWithComplexity() {
        cyberAttackPage.setFilterData(getDataComplexity().get(0));

        // Assert that rows should display complexity data
        Assert.assertNotNull(getDataCases().get(0));
        Assert.assertNotNull(getDataName().get(0));
        Assert.assertNotNull(getDataImpact().get(0));
        Assert.assertNotNull(getDataComplexity().get(0));

    }

    @Test
    public void C_SetSmallCaseFilterData() {
        cyberAttackPage.setFilterData(getDataName().get(0).toLowerCase(Locale.ROOT));

        // Assert that rows should display data even though filter data is in small case
        Assert.assertNotNull(getDataCases().get(0));
        Assert.assertNotNull(getDataName().get(0));
        Assert.assertNotNull(getDataImpact().get(0));
        Assert.assertNotNull(getDataComplexity().get(0));

    }


    @Test
    public void D_SetWrongFilterData() {
        cyberAttackPage.setFilterData("kkkk");

        //Assert that no data is displayed
        Assert.assertEquals(cyberAttackPage.getRowSize(), 0);
    }

    @Test
    public void E_SpecificFilterWithImpactScore() {
        cyberAttackPage.setFilterData(getDataImpact().get(0).toString());

        // Assert that rows not display complexity data
        Assert.assertEquals(cyberAttackPage.getRowSize(), 0);
    }

    @Test
    public void F_SpecificFilterWithNoOfCases() {
        cyberAttackPage.setFilterData(getDataCases().get(0).toString());

        // Assert that rows not display cases data
        Assert.assertEquals(cyberAttackPage.getRowSize(), 0);
    }

    @Test
    public void G_SortByName() throws InterruptedException {
        cyberAttackPage.setSortData("Name");

        //Assert that complexity is sorted in ascending order
        Assert.assertTrue(Utils.checkSortingString(getDataName()));

    }

    @Test
    public void H_SortByComplexity() throws InterruptedException {
        cyberAttackPage.setSortData("Complexity");

        //Assert that complexity is sorted in ascending order
        Assert.assertTrue(Utils.checkSortingString(getDataComplexity()));

    }

    @Test
    public void I_SortByImpactScore() throws InterruptedException {
        cyberAttackPage.setSortData("Impact score");

        //Assert that complexity is sorted in ascending order
        Assert.assertTrue(Utils.checkSortingFloat(getDataImpact()));

    }

    @Test
    public void J_SortByCases() throws InterruptedException, NumberFormatException {
        cyberAttackPage.setSortData("Number of cases");

        // Assert that after converting the no of cases to double and confirm the sort order
        Assert.assertEquals(Utils.checkSortingDouble(Utils.numberFormatter(getDataCases())), true);

    }

    public ArrayList<String> getDataName() {
        ArrayList<String> arrayList = new ArrayList<String>();

        for (int i = 1; i <= cyberAttackPage.getRowSize(); i++) {
            arrayList.add(webDriver.findElement(By.cssSelector("div#app div:nth-child(" + i + ") > div.table-data.data-name")).getText());
        }
        return arrayList;
    }

    public ArrayList<String> getDataCases() throws NumberFormatException {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 1; i <= cyberAttackPage.getRowSize(); i++) {
            arrayList.add(webDriver.findElement(By.cssSelector("div#app div:nth-child(" + i + ") > div.table-data.data-cases")).getText());
        }
        return arrayList;

    }


    public ArrayList<Float> getDataImpact() {
        ArrayList<Float> arrayList = new ArrayList<Float>();
        for (int i = 1; i <= cyberAttackPage.getRowSize(); i++) {
            arrayList.add(Float.parseFloat(webDriver.findElement(By.cssSelector("div#app div:nth-child(" + i + ") > div.table-data.data-averageImpact")).getText()));
        }
        return arrayList;
    }

    public ArrayList<String> getDataComplexity() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 1; i <= cyberAttackPage.getRowSize(); i++) {
            arrayList.add(webDriver.findElement(By.cssSelector("div#app div:nth-child(" + i + ") > div.table-data.data-complexity")).getText());
        }
        return arrayList;
    }

    @AfterMethod
    public void refresh()
    {
        webDriver.navigate().refresh();
    }
    @AfterClass
    public void close() {
        webDriver.close();
    }

}
