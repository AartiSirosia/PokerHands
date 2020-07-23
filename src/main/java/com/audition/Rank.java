package com.audition;

import static java.util.Arrays.stream;
import static java.util.Comparator.reverseOrder;
import static com.audition.Qualifiers.compose;
import static com.audition.Qualifiers.consecutive;
import static com.audition.Qualifiers.sameSuit;
import static com.audition.Qualifiers.shape;

public enum Rank {

    HIGH(shape(1, 1, 1, 1, 1)),
    ONE_PAIR(shape(2, 1, 1, 1)),
    TWO_PAIR(shape(2, 2, 1)),
    THREE_OF_KIND(shape(3, 1, 1)),
    STRAIGHT(consecutive()),
    FLUSH(sameSuit()),
    FULL_HOUSE(shape(3, 2)),
    FOUR_OF_KIND(shape(4, 1)),
    STRAIGHT_FLUSH(compose(consecutive(), sameSuit()));

    private Qualifier qualifier;

    Rank(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    static Rank rank(Hands hand) {
        return stream(Rank.values())
                .sorted(reverseOrder())
                .filter(rank -> rank.qualifier.qualify(hand))
                .findFirst()
                .orElse(HIGH);
    }

    interface Qualifier {
        boolean qualify(Hands hand);
    }
}