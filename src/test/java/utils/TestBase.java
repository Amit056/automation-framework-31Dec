package utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

@Listeners(AllureTestNg.class)
public class TestBase {

    private static final ThreadLocal<RequestSpecification> REPORTS_SPEC = new ThreadLocal<>();

    private static final ThreadLocal<RequestSpecification> TEACHER_SPEC = new ThreadLocal<>();

    @BeforeClass
    public void setup() {

        REPORTS_SPEC.set(new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("reportsBaseUrl"))
                .addHeader("Content-Type", ConfigManager.get("ContentType"))
                .addHeader("org-id", ConfigManager.get("orgId"))
                .addHeader("user-id", ConfigManager.get("userId"))
                .addHeader("Authorization", ConfigManager.get("authToken"))
                .addFilter(new AllureRestAssured()) // ⭐ KEY LINE
                .build());

        TEACHER_SPEC.set(new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("teacherBaseUrl"))
                .addHeader("Content-Type", ConfigManager.get("ContentType"))
                .addHeader("org-id", ConfigManager.get("orgId"))
                .addHeader("user-id", ConfigManager.get("userId"))
                .addHeader("Authorization", ConfigManager.get("authToken"))
                .addFilter(new AllureRestAssured()) // ⭐ KEY LINE
                .build());
    }

    protected RequestSpecification reportsRequest() {
        return REPORTS_SPEC.get();
    }

    protected RequestSpecification teacherRequest() {
        return TEACHER_SPEC.get();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        REPORTS_SPEC.remove();
        TEACHER_SPEC.remove();
    }
}
