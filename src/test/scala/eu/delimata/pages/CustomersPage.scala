package eu.delimata.pages

import eu.delimata.TestRunner1
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.{CacheLookup, FindBy, PageFactory}
import org.openqa.selenium.{WebDriver, WebElement}

class CustomersPage() extends AbstractPageObject {
  private var driver: WebDriver = TestRunner1.driver
  PageFactory.initElements(new AjaxElementLocatorFactory(driver, 1), this)

  @FindBy(id = "clear-button")
  @CacheLookup
  private var clickToClearFilters: WebElement = _

  @FindBy(id = "search-input")
  @CacheLookup
  private var searchInput: WebElement = _

  @FindBy(id = "search-column")
  @CacheLookup
  private var searchColumn: WebElement = _

  @FindBy(id = "match-case")
  @CacheLookup
  private var matchCase: WebElement = _

  @FindBy(id = "table-resume")
  private var summary: WebElement = _

  @FindBy(id = "search-slogan")
  private var searchTerm: WebElement = _

  @FindBy(xpath = "//table")
  private var searchResultsTable: WebElement = _

  /**
   * Click on Clear Filters Button.
   *
   * @return the CustomersPage class instance.
   */
  def clickClearFiltersButton: CustomersPage = {
    clickToClearFilters.click()
    this
  }

  /**
   * Set value to searchInput field.
   *
   * @return the CustomersPage class instance.
   */
  def setSearchInput(searchInput: String): CustomersPage = {
    this.searchInput.sendKeys(searchInput)
    this
  }

  /**
   * Set value to Search Column Drop Down List field.
   *
   * @param value String which should match with one of values visible on the dropdown
   * @return the CustomersPage class instance.
   */
  def setSearchColumnDropDownListField(value: String): CustomersPage = {
    new Select(searchColumn).selectByVisibleText(value)
    this
  }

  /**
   * Set Match Case Checkbox field to required value.
   *
   * @param value boolean value of the checkbox status true - checked, false - unchecked
   * @return the CustomersPage class instance.
   */
  def setMatchCaseCheckboxField(value: Boolean): CustomersPage = {
    if (value != matchCase.isSelected) matchCase.click()
    this
  }

  def getSummaryText: String = this.summary.getText

  def getSearchTermText: String = this.searchTerm.getAttribute("innerText")

  def getSearchInputText: String = this.searchInput.getAttribute("innerText")

  def getSearchResultsTableText: String = this.searchResultsTable.getText

  def open: CustomersPage = {
    val pageUrl = "https://danieldelimata.github.io/sample-page/"
    driver.get(pageUrl)
    this
  }
}
