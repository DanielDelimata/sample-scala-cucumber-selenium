package eu.delimata.stepdefinitions

import eu.delimata.pages.CustomersPage
import io.cucumber.scala.{EN, ScalaDsl}
import lombok.extern.slf4j.Slf4j
import org.assertj.core.api.Assertions


@Slf4j
class StepDefinitions extends ScalaDsl with EN {
  var customersPage: CustomersPage = _
  var searchSummaryAtVeryBeginning: String = _

  Given("""the user is on the page""") { () =>
    customersPage = new CustomersPage
    customersPage.open
    searchSummaryAtVeryBeginning = customersPage.getSummaryText
  }

  When("""the user enters the value {string} in the text-input""") { (searchInput: String) =>
    customersPage.setSearchInput(searchInput)
  }

  When("""the user selects value {string} in the drop-down""") { (value: String) =>
    customersPage.setSearchColumnDropDownListField(value)
  }

  When("""the user sets case sensitivity switch to {string}""") { (isCaseSensitive: String) =>
    customersPage.setMatchCaseCheckboxField(isCaseSensitive.toBoolean)
  }

  Then("""the user should see that the search results are as follows: {string}""") { (expectedResults: String) =>
    Assertions.assertThat(customersPage.getSearchResultsTableText.replaceAll("\\s+", " ").trim)
      .isEqualTo(expectedResults)
  }

  Then("""the user should see the following result summary {string}""") { (expectedSummary: String) =>
    Assertions.assertThat(customersPage.getSummaryText).isEqualTo(expectedSummary)
  }

  Then("""the user should see that the search term is {string}""") { (expectedTerm: String) =>
    Assertions.assertThat(customersPage.getSearchTermText).startsWith(expectedTerm)
  }

  When("""the user clears filters""") { () =>
    customersPage.clickClearFiltersButton
  }
  Then("""the user should see that search criteria are cleared""") { () =>
    Assertions.assertThat(customersPage.getSearchInputText).isEmpty()
  }

  Then("""the user should see that the search result summary is as in the very beginning""") { () =>
    Assertions.assertThat(customersPage.getSummaryText).isEqualTo(searchSummaryAtVeryBeginning)
  }
}
