package eu.delimata

import io.cucumber.junit.{Cucumber, CucumberOptions}
import io.cucumber.scala.Scenario
import io.qameta.allure.Allure
import org.junit.runner.RunWith
import org.junit.{After, AfterClass, BeforeClass}
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.{OutputType, TakesScreenshot, WebDriver}

import java.io.{File, FileOutputStream}
import java.nio.file.{Files, Paths}
import java.util.Properties

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("src/test/resources/features"),
  glue = Array("eu.delimata.stepdefinitions"),
  tags = "not @ignore",
  plugin = Array("io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"),
  monochrome = true
)
class TestRunner1 {

}

object TestRunner1 {
  var driver: WebDriver = _

  @BeforeClass
  def openTheBrowser(): Unit = {
    val options = new ChromeOptions()
      .addArguments("--start-maximized")
      .addArguments("--headless")
      .addArguments("--remote-allow-origins=*")
    driver = new ChromeDriver(options)
  }

  @AfterClass
  def closeTheBrowser(): Unit = {
    driver.quit()
  }

  @After
  def catchExceptions(scenario: Scenario): Unit = {
    if (scenario.isFailed) captureScreenshot(scenario.getName)
  }

  def captureScreenshot(fileName: String): Unit = {
    try {
      new File("allure-results/").mkdirs
      val path = "allure-results/screenshot-" + fileName + ".png"
      val out = new FileOutputStream(path)
      out.write(driver.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.BYTES))
      out.close()
      val content = Paths.get(path)
      val inputStream = Files.newInputStream(content)
      try Allure.addAttachment("Screenshot taken on failure", inputStream)
      finally if (inputStream != null) inputStream.close()
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }
}