package adventOfCode.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String filename;
    private long count = 0;

    public Day2(String filename) {
        this.filename = filename;
    }

    public void run() {
        LOG.info("Starting day2: {}", filename);
        MessageUtils.readLines(filename, this::processLine);
    }

    public void runP2() {
        LOG.info("Starting day2 P2: {}", filename);
        MessageUtils.readLines(filename, this::processLineP2);
    }

    private void processLine(String line) {
        String[] inputs = line.split(",", -1);
        for (String input : inputs) {
            count += process(input).stream().mapToLong(Long::longValue).sum();
        }
    }

    private void processLineP2(String line) {
        String[] inputs = line.split(",", -1);
        for (String input : inputs) {
            count += processP2(input).stream().mapToLong(Long::longValue).sum();
        }
    }

    public long getCount() {
        return count;
    }

    //In the above example:
    //
    //    11-22 has two invalid IDs, 11 and 22.
    //    95-115 has one invalid ID, 99.
    //    998-1012 has one invalid ID, 1010.
    //    1188511880-1188511890 has one invalid ID, 1188511885.
    //    222220-222224 has one invalid ID, 222222.
    //    1698522-1698528 contains no invalid IDs.
    //    446443-446449 has one invalid ID, 446446.
    //    38593856-38593862 has one invalid ID, 38593859.
    //    The rest of the ranges contain no invalid IDs.

    public static List<Long> process(String input) {
        LOG.debug("Checking input {}", input);
        String[] ids = input.split("-", -1);
        if (ids.length != 2) {
            throw new IllegalArgumentException("Wrong number of ids for input " + input);
        }
        long begin = Long.parseLong(ids[0]);
        long end = Long.parseLong(ids[1]);
        List<Long> list = new ArrayList<>();
        for (long i = begin; i <= end; i++) {
            String toCheck = Long.toString(i);
            if (toCheck.length() % 2 == 0) {
                //length should be a factor of 2
                long part1 = Long.parseLong(toCheck.substring(0, (toCheck.length() / 2)));
                long part2 = Long.parseLong(toCheck.substring(toCheck.length() / 2));
                LOG.debug("Comparing {} -- {} and {}", i, part1, part2);
                if (part1 == part2) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    public static List<Long> processP2(String input) {
        LOG.debug("Checking input {}", input);
        String[] ids = input.split("-", -1);
        if (ids.length != 2) {
            throw new IllegalArgumentException("Wrong number of ids for input " + input);
        }
        long begin = Long.parseLong(ids[0]);
        long end = Long.parseLong(ids[1]);
        List<Long> list = new ArrayList<>();

        realouter: for (long i = begin; i <= end; i++) {
            String toCheck = Long.toString(i);
            LOG.debug("Checking out {}", toCheck);
            //12341234
            outer:
            for (int numPieces = 2; numPieces <= toCheck.length(); numPieces++) {
                //numPieces=2
                LOG.debug("numPieces {}", numPieces);
                if (toCheck.length() % numPieces == 0) {
                    //it can be divided in these parts
                    int pieceLength = toCheck.length() / numPieces;
                    LOG.debug("pieceLength {}", pieceLength);
                    long previous = -1;
                    for (int k = 0; k < numPieces; k++) {
                        long part1 = Long.parseLong(toCheck.substring(k * pieceLength, (k + 1) * pieceLength));
                        if (previous == -1) {
                            previous = part1;
                        } else if (previous != part1) {
                            //no good
                            continue outer;
                        }
                    }

                    LOG.debug("FOUND: {}", i);
                    list.add(i);
                    continue realouter; //make sure the same number is not added twice
                }
            }

        }
        return list;
    }
}
