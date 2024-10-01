import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class Horse_Test {
    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n\n"})
    public void blankNameException(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test name", -1, 1));
    }

    @Test
    public void negativeSpeedMessage() {
        try {
            new Horse("test name", -1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test name", 1, -1));
    }

    @Test
    public void negativeDistanceMessage() {
        try {
            new Horse("test name", 1, -1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void getNameTest() {
        Horse horse = new Horse("qwerty", 1, 1);
        assertEquals("qwerty", horse.getName());
    }

    @Test
    public void getSpeedTest() {
        Horse horse = new Horse("qwerty", 123, 1);
        assertEquals(123, horse.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        Horse horse = new Horse("qwerty", 123, 1234);
        assertEquals(1234, horse.getDistance());
    }

    @Test
    public void zeroDistanceTest() {
        Horse horse = new Horse("qwerty", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void moveUsesGetRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("qwerty", 123, 1234).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 1.0, 999.999, 0.0})
    void moveTest(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("test name", 31, 283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(283 + 31 * random, horse.getDistance());
        }
    }
}
