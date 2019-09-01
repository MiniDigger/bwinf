package me.minidigger.bwinf;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task 1, 2019
 */
public class Parallelen {

    private static final String poem = """
                                       Es gingen zwei Parallelen
                                       ins Endlose hinaus,
                                       zwei kerzengerade Seelen
                                       und aus solidem Haus.

                                       Sie wollten sich nicht schneiden
                                       bis an ihr seliges Grab:
                                       Das war nun einmal der beiden
                                       geheimer Stolz und Stab.

                                       Doch als sie zehn Lichtjahre
                                       gewandert neben sich hin,
                                       da wards dem einsamen Paare
                                       nicht irdisch mehr zu Sinn.

                                       Warn sie noch Parallelen?
                                       Sie wußtens selber nicht, –
                                       sie flossen nur wie zwei Seelen
                                       zusammen durch ewiges Licht.

                                       Das ewige Licht durchdrang sie,
                                       da wurden sie eins in ihm;
                                       die Ewigkeit verschlang sie
                                       als wie zwei Seraphim.
                                       """;

    private static final List<String> words = Arrays.stream(poem.split("[ \\n]"))
            .map(word -> word.replaceAll("\\s|,|\\.|-|:|;|\\?|\\n", ""))
            .map(String::toLowerCase)
            .filter(world -> world.length() > 1).collect(Collectors.toUnmodifiableList());

    public static void main(String[] args) {
        for (String word : words.subList(0, (words.size() / 2) - 1)) {
            System.out.println("Proof for '" + word + "' was " + new Parallelen().proof(word));
        }

        // Result: all proofs are wrong, whats interesting is that all sequences end on "verschlang"
    }

    public boolean proof(String initialWord) {
        String lowerCaseWord = initialWord.toLowerCase();
        int index = words.indexOf(lowerCaseWord);
        if (index == -1) throw new IllegalArgumentException("Word not part of poem!");
        if (index > words.size() / 2) throw new IllegalArgumentException("Word not part of first half of poem!");

        return this.proof(lowerCaseWord, lowerCaseWord, index);
    }

    public boolean proof(String word, String initialWord, int index) {
        int n = word.length();
        int newIndex = index + n;
        if (newIndex >= words.size()) {
            // end, did we proof it?
            System.out.println("Proof: " + word + "==" + initialWord + "? " + word.equals(initialWord));
            return word.equals(initialWord);
        } else {
            //System.out.println("word=" + word + ", n=" + n + ", newIndex=" + newIndex);
            return proof(words.get(newIndex), initialWord, newIndex);
        }
    }
}
