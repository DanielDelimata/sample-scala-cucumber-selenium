package eu.delimata.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory

abstract class AbstractPageObject {
  private var driver: WebDriver = null

  def this(driver: WebDriver) {
    this()
    this.driver = driver
    val ajaxElementLocatorFactory = new AjaxElementLocatorFactory(this.driver, 1)
    PageFactory.initElements(ajaxElementLocatorFactory, this)
  }
}