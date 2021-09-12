package UIPages;

import SeleniumHelper.SeleniumHelper;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CyberAttackPage {

    public static Logger logger = Logger.getLogger(CyberAttackPage.class.getName());
    WebDriver webDriver;
    @FindBy(css = "input#filter-input")
    WebElement filterData;

    @FindBy(css = "select#sort-select")
    WebElement sortData;

    @FindBy(className = "table-row")
    List<WebElement> tableRow;

    @FindBy(className = "table-content")
    WebElement tableContent;

    public CyberAttackPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFilterData(String option1) {
        filterData.clear();
        filterData.sendKeys(option1);
    }

    public void setSortData(String option2) {
        Select select = new Select(sortData);
        select.selectByVisibleText(option2);
    }

    public int getRowSize() {
        return tableRow.size();
    }

    public String getRowData() {
        String s1 = null;
        for (int i = 0; i < tableRow.size(); i++) {
            s1 = tableRow.get(i).getText();
        }

        return s1;
    }
}



