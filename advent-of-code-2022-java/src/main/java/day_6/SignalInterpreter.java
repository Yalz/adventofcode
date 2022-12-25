package day_6;

import java.util.*;

public class SignalInterpreter {
    public static int interpret(String line, int markerSize) {
        Queue<Character> knownLetters = new ArrayDeque<>();
        int index = 0;

        char[] letters = line.toCharArray();
        for (int i=0; i<letters.length; i++) {
            if(knownLetters.contains(letters[i])){
                resetQueueAt(knownLetters, letters[i]);
            }
            else {
                knownLetters.add(letters[i]);
                if(knownLetters.size() == markerSize) {
                    index = i + 1;
                    break;
                }
            }
        }
        return index;
    }

    protected static void resetQueueAt(Queue<Character> queue, char c) {
        Character x;
        do {
            x = queue.poll();
        } while (!Objects.equals(x, c));
        queue.add(c);
    }
}
