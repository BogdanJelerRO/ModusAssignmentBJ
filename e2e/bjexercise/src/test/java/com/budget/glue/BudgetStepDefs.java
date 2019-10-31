package com.budget.glue;

import com.budget.pages.BudgetPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;

import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AnyOf.anyOf;

public class BudgetStepDefs {

  @Step
  @Given("I am on the budget page")
  public void iAmOnTheBudgetPage() {
    BudgetPage.open();
  }

  @When("I select the Category \"([^\"]*)\"$")
  public void iSelectTheCategory(String selectedCategory) {
    new BudgetPage().get().selectCategory(selectedCategory);
  }

  @And("I complete the Description with \"([^\"]*)\"$")
  public void iCompleteTheDescription(String givenDescriptionText) {
    new BudgetPage().get().insertDescription(givenDescriptionText);
  }

  @And("I enter the value \"([^\"]*)\"$")
  public void iEnterTheValue(String enterValueAmount) {
    assertThat(enterValueAmount).matches("^[0-9]*$");
    new BudgetPage().get().insertAmountValue(enterValueAmount);
  }

  @Then("I should be able to add the entry in the table")
  public void iShouldBeAbleToAddTheEntryInTheTable() {
    new BudgetPage().get().addInflowOrOutflowAmountInTheTable();
  }

  @And("The Working balance should be {string}")
  public void theWorkingBalanceShouldBe(String displayedWorkingBalanceValue) {
    assertThat(new BudgetPage().get().getWorkingBalanceDisplayedValue().contains(displayedWorkingBalanceValue));
  }

  @When("I delete all entries from the table")
  public void iDeleteAllEntriesFromTheTable() {
    new BudgetPage().get().deleteAllEntriesInTheTable();
  }

  @When("I change the value of the first amount value with {string}")
  public void iChangeTheValueOfTheFirstAmountValueWith(String newAmountValue) {
    new BudgetPage().get().updateFirstAmountValueInTheTableWithGivenValue(newAmountValue);
  }

  @Then("I should see the new value {string} in the table")
  public void iShouldSeeTheNewValueInTheTable(String newAmountValueInserted) {
    assertThat(new BudgetPage().get().getSearchForAmountValueInTheTable().isPresent()).isTrue();
    MatcherAssert.assertThat(new BudgetPage().get().getSearchForAmountValueInTheTable().get(), anyOf(is(newAmountValueInserted)));
  }
}
