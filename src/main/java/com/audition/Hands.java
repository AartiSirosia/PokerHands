package com.audition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.joining;
import static com.audition.Rank.rank;

class Hands implements Comparable<Hands> {

    private final List<Card> cards;

    private final Map<Integer, Integer> counts = new HashMap<>();

    Hands(List<Card> cards) {
        count(cards);
        this.cards = cards.stream()
                .sorted(reverseOrder())
                .sorted((card1, card2) -> counts.get(card2.value()) - counts.get(card1.value()))
                .collect(Collectors.toList());
    }

    @Override
    public int compareTo(Hands hand) {
        int ranking = rank(this).compareTo(rank(hand));
        return same(ranking) ? compareHighCard(hand) : ranking;
    }

    String shape() {
        return this.counts.values()
                .stream()
                .map(String::valueOf)
                .collect(joining());
    }

    Card max() {
        return this.cards.get(0);
    }

    Card min() {
        return this.cards.get(this.cards.size() - 1);
    }

    List<Card> cards() {
        return cards;
    }

    private boolean same(int rank) {
        return rank == 0;
    }

    private Integer compareHighCard(Hands hand) {
        return IntStream.range(0, 5)
                .map(index -> cards.get(index).compareTo(hand.cards.get(index)))
                .filter(integer -> integer != 0)
                .findFirst()
                .orElse(0);
    }

    private void count(List<Card> cards) {
        cards.forEach(card -> {
            Integer integer = counts.get(card.value());
            counts.put(card.value(), (integer == null ? 0 : integer) + 1);
        });
    }
}