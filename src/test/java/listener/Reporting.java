package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;


    public void onStart(ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "Test-Report-"+timeStamp+".html";
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/"+repName);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");

        extentReports = new ExtentReports();

        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Host name","localhost");
        extentReports.setSystemInfo("Environment","QA");
        extentReports.setSystemInfo("user","Amingo");

        htmlReporter.config().setDocumentTitle("InetBanking Test Project");
        htmlReporter.config().setReportName("Functional Test Automation Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

        String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";

        File file = new File(screenshotPath);

        if (file.exists()) {
            try {
                extentTest.fail("Screen is below:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
    }


    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}
