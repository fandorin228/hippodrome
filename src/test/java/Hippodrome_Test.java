import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Hippodrome_Test {
    @Test
    public void emptyHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));

    }

    @Test
    public void nullHorsesException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

    }

    @Test
    public void nullHorsesMessage() {
        try {
            new Hippodrome(null);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    public void emptyHorsesMessage() {
        try {
            new Hippodrome(new ArrayList<>());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertSame(horses.get(29), hippodrome.getWinner());
    }
}
