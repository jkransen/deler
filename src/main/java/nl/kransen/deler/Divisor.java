package nl.kransen.deler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Divisor {

    private static final long BATCH_SIZE = 1000;
    private final List<Integer> divisors;

    private Divisor(List<Integer> divisors) {
        this.divisors = divisors;
    }

    public boolean isMatch(long candidate) {
        boolean match = false;
        for (int divisor : divisors) {
            if (candidate % divisor != 0) {
                match = false;
                break;
            }
            match = true;
        }
        return match;
    }

    private List<Long> matches(long startCandidate, long endCandidate) {
        List<Long> matches = new ArrayList<>();
        for (long candidate = startCandidate; candidate < endCandidate; candidate++) {
            if (isMatch(candidate)) {
                matches.add(candidate);
            }
        }
        return matches;
    }

    public long firstMatch() {
        return Stream.iterate(1L, i -> i + BATCH_SIZE)
                .parallel()
                .flatMap(i -> matches(i, i + BATCH_SIZE).stream())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not enough Long values"));
    }

    public static Divisor create(int range) {
        Set<Integer> covered = new HashSet<>();
        List<Integer> divisors = new ArrayList<>();

        for (int divisor = range; divisor > 1; divisor--) {
            if (!covered.contains(divisor)) {
                divisors.add(divisor);
                for (int smaller = divisor - 1; smaller > 1; smaller--) {
                    if (divisor % smaller == 0) {
                        covered.add(smaller);
                    }
                }
            }
        }

        return new Divisor(divisors);
    }
}
