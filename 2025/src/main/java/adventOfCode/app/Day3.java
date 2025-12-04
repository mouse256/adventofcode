package adventOfCode.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.function.Function;

public class Day3 {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String filename;
    private long count = 0;
    private final Function<String, Long> process;

    public Day3(String filename, Function<String, Long> process) {
        this.filename = filename;
        this.process = process;
    }

    public void run() {
        LOG.info("Starting day3: {}", filename);
        MessageUtils.readLines(filename, this::processLine);
    }


    private void processLine(String line) {
        count += process.apply(line);
    }


    public long getCount() {
        return count;
    }


    /**
     * You'll need to find the largest possible joltage each bank can produce. In the above example:
     * <p>
     * In 987654321111111, you can make the largest joltage possible, 98, by turning on the first two batteries.
     * In 811111111111119, you can make the largest joltage possible by turning on the batteries labeled 8 and 9, producing 89 jolts.
     * In 234234234234278, you can make 78 by turning on the last two batteries (marked 7 and 8).
     * In 818181911112111, the largest joltage you can produce is 92.
     */
    public static long process(String input) {
        LOG.debug("Checking {}", input);
        int first = -1;
        int firstPosition = -1;
        for (int i = 0; i < input.length() - 1; i++) { //don't go to the end, there must always be 1 left
            int c = Character.getNumericValue(input.charAt(i));
            if (c > first) {
                firstPosition = i;
                first = c;
            }
        }
        LOG.debug("First digit {} @ {}", first, firstPosition);

        int second = -1;
        for (int i = firstPosition + 1; i < input.length(); i++) {
            int c = Character.getNumericValue(input.charAt(i));
            if (c > second) {
                second = c;
            }
        }
        LOG.debug("Second digit {}", second);
        return first * 10 + second;
    }

    public static long processP2(String input) {
        LOG.debug("Checking {}", input);

        int firstPosition = -1;
        StringBuilder res = new StringBuilder();
        for (int d = 0; d < 12; d++) {
            int first = -1;
            int endPos = 11 - d;
            for (int i = firstPosition+1; i < input.length() - endPos; i++) { //don't go to the end, there must always be 1 left
                int c = Character.getNumericValue(input.charAt(i));
                if (c > first) {
                    firstPosition = i;
                    first = c;
                }
            }
            res.append(first);
        }

        return Long.parseLong(res.toString());
    }

}
