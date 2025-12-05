package adventOfCode.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day4 {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String filename;
    private long count = 0;
    private String topLine = null;
    private String middleLine = null;
    private String bottomLine = null;
    private List<String> newLines = new ArrayList<>();

    public Day4(String filename) {
        this.filename = filename;
    }

    public void runP1() {
        LOG.info("Starting day4 P1: {}", filename);
        MessageUtils.readLines(filename, this::processLine);
        //last line
        count += process(middleLine, bottomLine, null);
    }


    public void runP2() {
        LOG.info("Starting day4 P2: {}", filename);

        //initial sweep
        MessageUtils.readLines(filename, this::processLine);
        //last line
        handle(middleLine, bottomLine, null);

        int safety = 0;
        //keep looping
        long oldCount = 0;
        do {
            safety++;
            LOG.debug("Sweep {}: {} -- {}", safety, count, count - oldCount);
            oldCount = count;

            List<String> lines = new ArrayList<>(newLines);
            newLines.clear();
            topLine = null;
            middleLine = null;
            bottomLine = null;
            lines.forEach(this::processLine);
            //last line
            handle(middleLine, bottomLine, null);
            //LOG.info("Sweep done {}: {}", safety, count, count - oldCount);
        } while (count != oldCount && safety < 200000);
    }


    private void processLine(String line) {
        //shift
        topLine = middleLine;
        middleLine = bottomLine;
        bottomLine = line;
        if (middleLine != null) {
            handle(topLine, middleLine, bottomLine);
        }
    }

    private void handle(String top, String middle, String bottom) {
        Res res = processP2(top, middle, bottom);
        count += res.count;
        newLines.add(res.newLine);
    }

    private void process() {

    }


    public long getCount() {
        return count;
    }


    private static Stream<Character> extract(String t, int pos) {
        return IntStream
                .rangeClosed(Math.max(0, pos - 1), Math.min(t.length() - 1, pos + 1))
                .mapToObj(t::charAt);
    }

    private static Stream<Character> extract2(String line, int pos) {
        return Optional.ofNullable(line)
                .stream()
                .flatMap(t -> extract(t, pos));
    }

    public static long process(String top, String middle, String bottom) {
        LOG.debug("Checking {} -- {} -- {}", top, middle, bottom);
        int total = 0;
        for (int i = 0; i < middle.length(); i++) {
            if (middle.charAt(i) != '@') {
                continue;
            }

            long count = Stream.of(
                            extract2(top, i),
                            extract2(middle, i),
                            extract2(bottom, i))
                    .flatMap(x -> x)
                    .filter(c -> c == '@')
                    .count();


            LOG.debug("i: {} -- count: {}", i, count);

            if (count <= 4) {
                total++;
            }
        }
        return total;
    }

    public record Res(int count, String newLine){}

    public static Res processP2(String top, String middle, String bottom) {
        LOG.debug("Checking {} -- {} -- {}", top, middle, bottom);
        int total = 0;
        StringBuilder newLine = new StringBuilder();
        for (int i = 0; i < middle.length(); i++) {
            if (middle.charAt(i) != '@') {
                newLine.append('.');
                continue;
            }

            long count = Stream.of(
                            extract2(top, i),
                            extract2(middle, i),
                            extract2(bottom, i))
                    .flatMap(x -> x)
                    .filter(c -> c == '@')
                    .count();


            LOG.debug("i: {} -- count: {}", i, count);

            if (count <= 4) {
                newLine.append('.');
                total++;
            } else {
                newLine.append('@');
            }
        }
        return new  Res(total, newLine.toString());
    }

//    public static long process2(String top, String middle, String bottom) {
//        LOG.debug("Checking {} -- {} -- {}", top, middle, bottom);
//
//        List<Character> list = IntStream.range(0, middle.length())
//                .filter(index -> middle.charAt(index) == '@')
//                .boxed()
//                .flatMap(i ->
//                        Stream.of(
//                                extract2(top, i),
//                                extract2(middle, i),
//                                extract2(bottom, i)))
//                .flatMap(x -> x)
//                .filter(c -> c == '@')
//                .toList();
//
//        int total = 0;
//        for (int i = 0; i < middle.length(); i++) {
//            if (middle.charAt(i) != '@') {
//                continue;
//            }
//
//            long count = Stream.of(
//                            extract2(top, i),
//                            extract2(middle, i),
//                            extract2(bottom, i))
//                    .flatMap(x -> x)
//                    .filter(c -> c == '@')
//                    .count();
//
//
//            LOG.debug("i: {} -- count: {}", i, count);
//
//            if (count <= 4) {
//                total++;
//            }
//        }
//        return total;
//
//    }


}
