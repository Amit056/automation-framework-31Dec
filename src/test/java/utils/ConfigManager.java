package utils;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class is final so that it can't be inherited
 */

public final class ConfigManager {
 /**
  * can't be instiated from outside the class
  */
    private ConfigManager() {} 

/**
 * Load the configuration properties only when they are first needed, and do it in a thread-safe way without synchronization.
 */
    private static class Holder {
        private static final Properties PROPS = load();
    }

    /**
     * Loads environment-specific configuration values from a .properties file into a Properties object and returns it.
     * @return
     */
    private static Properties load() {
        Properties props = new Properties();
        String env = System.getProperty("env", "devSm");

        try (InputStream is = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config/" + env + ".properties")) {

            if (is == null) {
                throw new RuntimeException("Config file not found for env: " + env);
            }
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return props;
    }

    public static String get(String key) {
        return Holder.PROPS.getProperty(key);
    }

     // ===============================
    // BOOLEAN
    // ===============================
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(getRequired(key));
    }

    // ===============================
    // INTEGER
    // ===============================
    public static int getInt(String key) {
        try {
            return Integer.parseInt(getRequired(key));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Invalid integer value for key: " + key, e);
        }
    }

    // ===============================
    // LONG
    // ===============================
    public static float getFloat(String key) {
        try {
            return Long.parseLong(getRequired(key));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Invalid long value for key: " + key, e);
        }
    }

    // ===============================
    // DOUBLE
    // ===============================
    public static double getDouble(String key) {
        try {
            return Double.parseDouble(getRequired(key));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Invalid double value for key: " + key, e);
        }
    }

    // ===============================
    // REQUIRED KEY CHECK
    // ===============================
    private static String getRequired(String key) {
        String value = Holder.PROPS.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing required config key: " + key);
        }
        return value.trim();
    }
}
