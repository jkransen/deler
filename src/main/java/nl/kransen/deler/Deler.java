package nl.kransen.deler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deler {

    private final List<Integer> delers;

    private Deler(List<Integer> delers) {
        this.delers = delers;
    }

    public long eersteMatch() {
        boolean match = false;
        for (long kandidaat = 1; kandidaat < Long.MAX_VALUE; kandidaat++) {
            for (int deler : delers) {
                if (kandidaat % deler != 0) {
                    match = false;
                    break;
                }
                match = true;
            }
            if (match) {
                return kandidaat;
            }
        }
        throw new IllegalStateException("We zijn door kandidaten heen");
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
