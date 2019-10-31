package com.budget.pages;

import com.frameworkium.core.ui.annotations.Visible;
import com.frameworkium.core.ui.element.OptimisedStreamTable;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;
import java.util.Optional;

public class BudgetPage extends BasePage<BudgetPage> {

  @Visible
  @Name("Budget Menu")
  @FindBy(xpath = "//a[@href='/budget']")
  private Link budgetLinkMenu;

  @Visible
  @Name("Reports Menu")
  @FindBy(xpath = "//a[@href='/reports']")
  private Link reportsLinkMenu;

  @Visible
  @CacheLookup
  @Name("Budget Table")
  @FindBy(className = "_1IRiC")
  private OptimisedStreamTable budgetTable;

  @Visible
  @Name("Category Select")
  @FindBy(name = "categoryId")
  private Select categoryDropdown;

  @Visible
  @Name("Description Input")
  @FindBy(name = "description")
  private TextInput descriptionInput;

  @Visible
  @Name("Value Input")
  @FindBy(name = "value")
  private TextInput valueInput;

  @Visible
  @Name("Add inflow or outflow button")
  @FindBy(xpath = "//button[@class='submit _1eQR8 _3Srv6']")
  private Button addInflowOrOutflowButton;

  @Visible
  @Name("Working balance Output")
  @FindBy(xpath = "//div[@class='M0Z8m _1_jJ7']")
  private TextInput workingBalanceDisplayedValue;

  @Name("Update button visible in the row")
  @FindBy(xpath = "//button[@class ='submit _1eQR8 _3Srv6']")
  private Button updateButtonRow;

  @Name("Cancel button visible in the row")
  @FindBy(xpath = "//button[@class ='cancel _1eQR8 _29Up9']")
  private Button cancelButtonRow;

  @Name("Delete button visible in the row")
  @FindBy(xpath = "//button[@class ='delete _1eQR8 _1Si7F']")
  private Button deleteButtonRow;

  @Step("Open app page")
  public static BudgetPage open() {
    String targetStage = Optional.ofNullable(System.getProperty("targetStage")).orElse("http://localhost:8000");
    return new BudgetPage().get(targetStage);
  }

  @Step("Access budget page")
  public BudgetPage accessBudgetPage() {
    budgetLinkMenu.click();
    return this;
  }

  @Step("Access reports page")
  public BudgetPage accessReportsPage() {
    reportsLinkMenu.click();
    return this;
  }

  @Step("Select Category {0} from dropdown")
  public BudgetPage selectCategory(String giveCategory) {
    categoryDropdown.selectByVisibleText(giveCategory);
    return this;
  }

  @Step("Get categories from category dropdown")
  public List<WebElement> getCategories() {
    return categoryDropdown.getOptions();
  }

  @Step("Insert Description {0}")
  public BudgetPage insertDescription(String insertDescriptionText) {
    descriptionInput.sendKeys(insertDescriptionText);
    return this;
  }

  @Step("Insert Amount Value {0}")
  public BudgetPage insertAmountValue(String insertAmountValue) {
    valueInput.sendKeys(insertAmountValue);
    return this;
  }

  @Step("Add inserted inflow or outflow in the table")
  public BudgetPage addInflowOrOutflowAmountInTheTable() {
    addInflowOrOutflowButton.click();
    return this;
  }

  @Step
  public String getWorkingBalanceDisplayedValue() {
    return workingBalanceDisplayedValue.getText();
  }

  @Step("Delete all entries in the table")
  public BudgetPage deleteAllEntriesInTheTable() {
    budgetTable.getColumn("Amount").peek(WebElement::click).forEach(y -> deleteButtonRow.click());
    return this;
  }

  @Step("Update first amount value in the table with {0} value")
  public BudgetPage updateFirstAmountValueInTheTableWithGivenValue(String giveNewAmountValue) {
    budgetTable.getColumn("Amount").peek(WebElement::click).findFirst().ifPresent(WebElement::click);

    wait.until(ExpectedConditions.visibilityOf(updateButtonRow));

    updateButtonRow.click();
    valueInput.sendKeys(giveNewAmountValue);

    return this;
  }

  @Step("Search for Amount value in the budget table")
  public Optional<WebElement> getSearchForAmountValueInTheTable() {
    Optional<WebElement> amountsColumnValues = budgetTable.getColumn("Amount").findAny();
    return amountsColumnValues;
  }


}
