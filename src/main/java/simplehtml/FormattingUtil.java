package simplehtml;

/**
 * Utility class that helps with formatting.
 * 
 * @author c.timpert
 *
 */
class FormattingUtil {

    private FormattingUtil() {
    }

    static String repeatString(String string, int times) {
        if (times == 0) {
            return "";
        }
        if (times == 1) {
            return string;
        }
        return String.valueOf(new char[times]).replace("\0", string);
    }

}
