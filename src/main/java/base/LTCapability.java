package base;

import com.google.gson.JsonObject;
import config.Config;

public class LTCapability {
    public static JsonObject getDefaultTestCapability() {
        JsonObject capabilities1 = new JsonObject();
        JsonObject ltOptions1 = new JsonObject();

        String user = Config.getUser();
        String accessKey = Config.getAccessKey();

        capabilities1.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`,
        // `pw-chromium`, `pw-firefox` and `pw-webkit`
        capabilities1.addProperty("browserVersion", "latest");
        ltOptions1.addProperty("platform", "Windows 10");
        ltOptions1.addProperty("name", "Playwright Test");
        ltOptions1.addProperty("build", "Playwright POM Parallel Demo");
        ltOptions1.addProperty("user", user);
        ltOptions1.addProperty("accessKey", accessKey);
        capabilities1.add("LT:Options", ltOptions1);

        return capabilities1;
    }
}
