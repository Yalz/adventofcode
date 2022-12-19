package day_6;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class SignalInterpreterTest {
    @Test
    public void testExamplesMarkerLenght4() {
        assertEquals(5, SignalInterpreter.interpret("bvwbjplbgvbhsrlpgdmjqwftvncz", 4));
        assertEquals(6, SignalInterpreter.interpret("nppdvjthqldpwncqszvftbrmjlhg", 4));
        assertEquals(10, SignalInterpreter.interpret("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4));
        assertEquals(11, SignalInterpreter.interpret("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4));
    }

    @Test
    public void testExamplesMarkerLenght14() {
        assertEquals(19, SignalInterpreter.interpret("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14));
        assertEquals(23, SignalInterpreter.interpret("bvwbjplbgvbhsrlpgdmjqwftvncz", 14));
        assertEquals(23, SignalInterpreter.interpret("nppdvjthqldpwncqszvftbrmjlhg", 14));
        assertEquals(29, SignalInterpreter.interpret("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14));
        assertEquals(26, SignalInterpreter.interpret("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14));
    }

    @Test
    public void testRemoveFromQueue() {
        Queue<Character> queue = new ArrayDeque<>();
        queue.add('a');
        queue.add('b');
        SignalInterpreter.resetQueueAt(queue, 'a');
        assertEquals(2, queue.size());
    }
}
