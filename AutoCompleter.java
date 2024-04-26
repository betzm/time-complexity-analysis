/*
 * Course: CSC1120A - 121
 * Spring 2024
 * Lab 8 - JUnit Testing
 * Name: Madison Betz
 * Created: 3/5/2024
 */
package betzm;

/**
 * interface that will be tested and performs multiple functions of a list
 */
public interface AutoCompleter {
    /**
     * returns true if word is added to the object
     * (a word should not be added if it is already
     * in the auto completer). If word is null or an
     * empty string, an IllegalArgumentException is thrown.
     * @param word being added
     * @return true if word is added
     * @throws IllegalArgumentException if word is null or empty
     */
    boolean add(String word);

    /**
     * returns true if target is found in the auto completer.
     * If target is null or an empty string, the method returns false.
     * @param target word that is being looked for
     * @return true if target is found, false if target is not found
     * or is empty or null
     */
    boolean exactMatch(String target);

    /**
     * returns the number of items in the auto completer.
     * @return number of items in auto completer
     */
    int size();

    /**
     * returns a String indicating the fully qualified name
     * of the data structure used to store the words for the
     * AutoCompleter. E.g., "java.util.ArrayList".
     * @return string of data structure used
     */
    String getBackingClass();

    /**
     * returns an array of all the strings in the object
     * that begin with the prefix. If prefix is an empty
     * string, an array of all the strings in the auto completer
     * are returned. If prefix is null, an empty array is returned.
     * @param prefix prefix being found in words in auto completer
     * @return an array of the words with the prefix
     */
    String[] allMatches(String prefix);

    /**
     * returns a human-friendly string representing the number of
     * nanoseconds
     * @param nanoseconds nanoseconds in test
     * @return string representing the nanoseconds
     */
    static String format(long nanoseconds) {
        final long BILLION = 1_000_000_000L;
        final long MILLION = 1_000_000L;
        final long THOUSAND = 1_000L;
        final long SECONDS = 60L;
        final long MINUTES = 60L;
        final long HOURS = 24L;

        if (nanoseconds < THOUSAND) {
            return nanoseconds + " nanosecond(s)";
        } else if (nanoseconds < MILLION) {
            double microseconds = nanoseconds / 1_000.0;
            return String.format("%.1f microsecond(s)", microseconds);
        } else if (nanoseconds < BILLION) {
            double milliseconds = nanoseconds / 1_000_000.0;
            return String.format("%.1f millisecond(s)", milliseconds);
        } else {
            long seconds = nanoseconds / BILLION;
            nanoseconds %= BILLION;
            long minutes = seconds / SECONDS;
            seconds %= SECONDS;
            long hours = minutes / MINUTES;
            minutes %= MINUTES;
            long days = hours / HOURS;
            hours %= HOURS;

            StringBuilder result = new StringBuilder();
            if (days > 0) {
                result.append(days).append(" day(s) ");
            }
            if (hours > 0) {
                result.append(hours).append(" hour(s) ");
            }
            if (minutes > 0) {
                result.append(minutes).append(" minute(s) ");
            }
            if (seconds > 0 || nanoseconds > 0) {
                result.append(seconds);
                if (nanoseconds > 0) {
                    double secondsDecimal = nanoseconds / 1_000_000_000.0;
                    result.append(".").append(String.format("%.1f", secondsDecimal).substring(2));
                }
                result.append(" second(s)");
            }
            return result.toString();
        }
    }
}
