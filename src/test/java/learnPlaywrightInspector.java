import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import config.Config;

public class learnPlaywrightInspector {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
        page.pause();
        Locator myAccount = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("My account"));
        myAccount.click();
        page.getByPlaceholder("E-Mail Address")
                .fill(Config.getUsername());
        page.getByPlaceholder("Password")
                .fill(Config.getPassword());
        page.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Login"))
                .click();
        page.getByRole(AriaRole.LINK,
                        new Page.GetByRoleOptions()
                                .setName("Edit your account information"))
                .click();
        page.getByPlaceholder("Last Name")
                .fill("Editado");
        page.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions()
                                .setName("Continue"))
                .click();
        Locator successMessage = page
                .getByText("Success: Your account has been successfully updated.");
        assertThat(successMessage).isVisible();
        myAccount.hover();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions()
                        .setName("Logout").setExact(true))
                .click();
        Locator logoutHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions()
                .setName("Account Logout"));
        assertThat(logoutHeader).isVisible();
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}

