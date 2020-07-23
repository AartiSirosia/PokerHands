package com.audition;

import java.util.Optional;

import static java.util.Arrays.stream;
import static com.audition.Card.Face.face;

class Card implements Comparable<Card> {

    private final int value;
    private final Suit suit;

    Card(String card) {
        value = value(card.substring(0, 1));
        suit = suit(card.substring(1, 2));
    }

    @Override
    public int compareTo(Card o) {
        return this.value - o.value;
    }

    Integer value() {
        return value;
    }

    Suit suit() {
        return suit;
    }

    private Suit suit(String suit) {
        return Suit.valueOf(suit);
    }

    private int value(String value) {
        return face(value)
                .map(face -> face.value)
                .orElseGet(() -> Integer.valueOf(value));
    }

    enum Suit {
        S, H, C, D
    }

    enum Face {
        TEN(10, "T"), JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K"), ACE(14, "A");

        private int value;
        private String symbol;

        Face(int value, String symbol) {
            this.value = value;
            this.symbol = symbol;
        }

        static Optional<Face> face(String symbol) {
            return stream(Face.values())
                    .filter(card -> symbol.equals(card.symbol))
                    .findFirst();
        }
    }
}