package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener {

    private ExtentSparkReporter htmlReporter;
    private ExtentReports reports;

    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    public void configureReport() {

        String timestamp = new SimpleDateFormat(
                "yyyy.MM.dd HH.mm.ss"
        ).format(new Date());

        String reportDirectory =
                System.getProperty("user.dir")
                + File.separator
                + "Reports";

        File directory = new File(reportDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String reportPath =
                reportDirectory
                + File.separator
                + "PetStore-Reports-"
                + timestamp
                + ".html";

        htmlReporter =
                new ExtentSparkReporter(reportPath);

        reports = new ExtentReports();

        reports.attachReporter(htmlReporter);

        reports.setSystemInfo("Machine", "testpc1");
        reports.setSystemInfo("OS", "Windows 11");
        reports.setSystemInfo("Username", "Suraj");

        htmlReporter.config().setDocumentTitle(
                "PetStore API Automation Report"
        );

        htmlReporter.config().setReportName(
                "PetStore Automation Test Report"
        );

        htmlReporter.config().setTheme(
                Theme.DARK
        );
    }

    @Override
    public void onStart(ITestContext context) {

        configureReport();

        System.out.println(
                "Extent Report Started..."
        );
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                reports.createTest(
                        result.getMethod().getMethodName()
                );

        extentTest.set(test);

        test.log(
                Status.INFO,
                "Test execution started."
        );

        System.out.println(
                "Test Started: "
                + result.getName()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = extentTest.get();

        if (test != null) {

            test.log(
                    Status.PASS,
                    "Test executed successfully."
            );
        }

        System.out.println(
                "Test Passed: "
                + result.getName()
        );

        extentTest.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = extentTest.get();

        if (test != null) {

            test.log(
                    Status.FAIL,
                    "Test execution failed."
            );

            if (result.getThrowable() != null) {

                test.log(
                        Status.FAIL,
                        result.getThrowable().toString()
                );
            }

            String screenshotPath =
                    System.getProperty("user.dir")
                    + File.separator
                    + "ScreenShots"
                    + File.separator
                    + result.getName()
                    + ".png";

            File screenshotFile =
                    new File(screenshotPath);

            if (screenshotFile.exists()) {

                try {

                    test.addScreenCaptureFromPath(
                            screenshotPath
                    );

                } catch (Exception e) {

                    test.log(
                            Status.WARNING,
                            "Unable to attach screenshot: "
                            + e.getMessage()
                    );
                }
            }
        }

        System.out.println(
                "Test Failed: "
                + result.getName()
        );

        extentTest.remove();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = extentTest.get();

        if (test != null) {

            test.log(
                    Status.SKIP,
                    "Test execution skipped."
            );

            if (result.getThrowable() != null) {

                test.log(
                        Status.SKIP,
                        result.getThrowable().toString()
                );
            }
        }

        System.out.println(
                "Test Skipped: "
                + result.getName()
        );

        extentTest.remove();
    }

    @Override
    public void onFinish(ITestContext context) {

        if (reports != null) {

            reports.flush();
        }

        System.out.println(
                "Extent Report Finished..."
        );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {
    }
}