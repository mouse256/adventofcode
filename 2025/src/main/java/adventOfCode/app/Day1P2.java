package adventOfCode.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1P2 {
    public record Result(int res, int count0) { }

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Pattern PATTERN = Pattern.compile("^([RL])(\\d+)$");
    private final String filename;
    private int old = 50;
    private int count0 = 0;

    public Day1P2(String filename) {
        this.filename = filename;
    }

    public void run() {
        LOG.info("Starting day1: {}", filename);
        MessageUtils.readLines(filename, this::processLine);
    }

    private void processLine(String line) {
        Result res = process(old, line);
        LOG.debug("processing: {} -- {} -> {}", line, old, res);
        old = res.res;
        count0 += res.count0;
        if (old == 0) {
            count0++;
        }
    }

    public int getCount0() {
        return count0;
    }

    public static Result process(int old, String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalStateException("cannot parse line: " + line);
        }
        int val = Integer.parseInt(matcher.group(2));
        int n = switch (matcher.group(1)) {
            case "L" -> old - val;
            case "R" -> old + val;
            default -> throw new IllegalStateException("cannot parse line RL: " + line);
        };
        int count0 = 0;
        if (old == 0 && n < 0) {
            count0--;
        }
        while (n < 0) {
            n += 100;
            count0++;
        }
        if (n > 0 && n % 100 == 0) {
            count0--;
        }
        while (n >= 100) {
            n -= 100;
            count0++;
        }
        int res =  n % 100;
        return new Result(res, count0);
    }
}
