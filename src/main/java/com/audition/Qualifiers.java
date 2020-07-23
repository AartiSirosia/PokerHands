package com.audition;

import com.audition.Rank.Qualifier;

import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

class Qualifiers {
    static Qualifier shape(Integer... shape) {
        String expected = stream(shape).map(String::valueOf).collect(joining());
        return hand -> hand.shape().equals(expected);
    }

    static Qualifier consecutive() {
        return compose(shape(1, 1, 1, 1, 1),
                hand -> hand.max().value() - hand.min().value() == hand.cards().size() - 1);
    }

    static Qualifier sameSuit() {
        return hand -> hand.cards().stream()
                .map(Card::suit)
                .collect(Collectors.toSet()).size() == 1;
    }

    static Qualifier compose(Qualifier... qualifiers) {
        return hand -> stream(qualifiers).allMatch(qualifier -> qualifier.qualify(hand));
    }
}