package com.audition;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

class PokerHands {

    private final Hand first;
    private final Hand second;

    PokerHands(String cards) {
        first = new Hand(first5(of(cards)));
        second = new Hand(last5(of(cards)));
    }

    int winner() {
        return first.compareTo(second) > 0 ? 1 : 2;
    }

    private List<Card> last5(List<Card> cards) {
        return cards.stream().skip(5).collect(Collectors.toList());
    }

    private List<Card> first5(List<Card> cards) {
        return cards.stream().limit(5).collect(Collectors.toList());
    }

    private List<Card> of(String cards) {
        return stream(cards.split(" ")).map(Card::new).collect(Collectors.toList());
    }
}