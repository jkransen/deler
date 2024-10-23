package nl.kransen.deler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Deler {

    private static final long BATCH_SIZE = 1000;
    private final List<Integer> delers;

    private Deler(List<Integer> delers) {
        this.delers = delers;
    }

    public boolean isMatch(long kandidaat) {
        boolean match = false;
        for (int deler : delers) {
            if (kandidaat % deler != 0) {
                match = false;
                break;
            }
            match = true;
        }
        return match;
    }

    private List<Long> matches(long beginKandidaat, long eindKandidaat) {
        List<Long> matches = new ArrayList<>();
        for (long kandidaat = beginKandidaat; kandidaat < eindKandidaat; kandidaat++) {
            if (isMatch(kandidaat)) {
                matches.add(kandidaat);
            }
        }
        return matches;
    }

    public long eersteMatch() {
        return Stream.iterate(1L, i -> i + BATCH_SIZE)
                .parallel()
                .flatMap(i -> matches(i, i + BATCH_SIZE).stream())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not enough Long values"));
    }

    public static Deler create(int range) {
        Set<Integer> gedekt = new HashSet<>();
        List<Integer> delers = new ArrayList<>();

        for (int deler = range; deler > 1; deler--) {
            if (!gedekt.contains(deler)) {
                delers.add(deler);
                for (int kleiner = deler - 1; kleiner > 1; kleiner--) {
                    if (deler % kleiner == 0) {
                        gedekt.add(kleiner);
                    }
                }
            }
        }

        return new Deler(delers);
    }
}
