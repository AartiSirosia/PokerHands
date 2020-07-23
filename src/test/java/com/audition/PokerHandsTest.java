import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PokerHandsTest {

    @Test
    public void high_card_win() throws Exception {
        assertThat(new Poker("2H 3D 5S 9C KD 2C 3H 4S 8C AH").winner(), is(2));
    }

    @Test
    public void high_card_same_second_high_card_wins() throws Exception {
        assertThat(new Poker("2H 3D 5S 9C KD 2C 3H 4S 8C KH").winner(), is(1));
    }

    @Test
    public void one_pair_win_on_high_cards() throws Exception {
        assertThat(new Poker("2H 2D 5S 9C KD 2C 3H 4S 8C AH").winner(), is(1));
    }
    
    @Test
    public void both_one_pair_high_pair_wins() throws Exception {
        assertThat(new Poker("2H 2D 5S 9C KD 3C 3H 4S 8C AH").winner(), is(2));
    }
    
    @Test
    public void two_pair_wins_on_one_pair() throws Exception {
        assertThat(new Poker("2H 2D 5S 9C KD 3C 3H 4S 4C AH").winner(), is(2));
    }
    
    @Test
    public void both_two_pair_highest_win() throws Exception {
        assertThat(new Poker("2H 2D 9S 9C KD 3C 3H 4S 4C AH").winner(), is(1));
    }

    @Test
    public void two_pair_highest_pair_same_second_higher_pair_win() throws Exception {
        assertThat(new Poker("2H 2D 9S 9C KD 3C 3H 9H 9D AH").winner(), is(2));
    }
    
    @Test
    public void both_two_pair_same_higher_card_win() throws Exception {
        assertThat(new Poker("3C 3D 9S 9C KD 3C 3H 9H 9D AH").winner(), is(2));
    }

    @Test
    public void three_of_kind_wins_on_two_pair() throws Exception {
        assertThat(new Poker("3C 3D 3C 9C AD 4C 4H 9H 9D AH").winner(), is(1));
    }
    
    @Test
    public void both_three_of_kind_higher_win() throws Exception {
        assertThat(new Poker("3C 3D 3C 9C 8D 4C 4H 4C 9D AH").winner(), is(2));
    }

    @Test
    public void straight_wins_on_three_of_kind() throws Exception {
        assertThat(new Poker("3C 3D 3C 9C AD 4C 5H 6C 7D 8H").winner(), is(2));
    }
    
    @Test
    public void two_straight_higher_wins() throws Exception {
        assertThat(new Poker("3C 4D 5C 6C 7D 4C 5H 6C 7D 8H").winner(), is(2));
    }

    @Test
    public void flush_wins_straight() throws Exception {
        assertThat(new Poker("3C 8C 5C 6C JC 4C 5H 6C 7D 8H").winner(), is(1));
    }

    @Test
    public void two_flush_higher_wins_straight() throws Exception {
        assertThat(new Poker("3C 8C 5C 6C JC 4H 9H JH QH AH").winner(), is(2));
    }

    @Test
    public void full_house_wins_on_flush() throws Exception {
        assertThat(new Poker("3C 3H 3D 6C 6D 4H 9H JH QH AH").winner(), is(1));
    }

    @Test
    public void two_full_house_higher_wins() throws Exception {
        assertThat(new Poker("3C 3H 3D 6C 6D 4H 4D 4C QH QD").winner(), is(2));
    }

    @Test
    public void four_of_kind_wins_on_full_house() throws Exception {
        assertThat(new Poker("3C 3H 3D 3S 6D 4H 4D 4C QH QD").winner(), is(1));
    }
    
    @Test
    public void two_four_of_kinds_higher_wins() throws Exception {
        assertThat(new Poker("3C 3H 3D 3S 6D 4H 4D 4C 4S QD").winner(), is(2));
    }

    @Test
    public void straight_flush_wins_on_four_of_kind() throws Exception {
        assertThat(new Poker("5C 6C 7C 8C 9C 4H 4D 4C 4S QD").winner(), is(1));
    }
    
    @Test
    public void two_straight_flush_higher_wins() throws Exception {
        assertThat(new Poker("5C 6C 7C 8C 9C 7H 8H 9H TH JH").winner(), is(2));
    }
}