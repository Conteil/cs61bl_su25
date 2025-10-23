import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;
import wordnet.WordnetGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.*/
public class TestOneWordK0Hyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    // ngrams files
    public static final String VERY_SHORT_WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    private static final String SMALL_WORDS_FILE = "data/ngrams/top_14377_words.csv";
    private static final String WORDS_FILE = "data/ngrams/top_49887_words.csv";

    // wordnet Files
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    private static final String HYPONYMS_FILE_SUBSET = "data/wordnet/hyponyms1000-subgraph.txt";
    private static final String SYNSETS_FILE_SUBSET = "data/wordnet/synsets1000-subgraph.txt";

    // EECS files
//    private static final String FREQUENCY_EECS_FILE = "data/ngrams/frequency-EECS.csv";
//    private static final String HYPONYMS_EECS_FILE = "data/wordnet/hyponyms-EECS.txt";
//    private static final String SYNSETS_EECS_FILE = "data/wordnet/synsets-EECS.txt";


    @Test
    public void testGraph() {
//        WordnetGraph wn = new WordnetGraph(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
//        // public WordnetGraph.wordnet to test
//
//        LinkedList<Integer> test1 = new LinkedList<>();
//        test1.add(9);
//        test1.add(10);
//        assertThat(wn.wordnet.neighbors(8)).isEqualTo(test1);
//
//        LinkedList<Integer> test2 = new LinkedList<>();
//        test2.add(12);
//        test2.add(13);
//        assertThat(wn.wordnet.neighbors(11)).isEqualTo(test2);
//
//        assertThat(wn.indexWords(6)).isEqualTo(new String[]{"act", "human_action", "human_activity"});
//
//        HashSet<Integer> test3 = new HashSet<>();
//        test3.add(6);
//        test3.add(7);
//        test3.add(8);
//        test3.add(9);
//        test3.add(10);
//        assertThat(wn.wordnet.traversal(6)).isEqualTo(test3);
    }

    @Test
    public void testSmallActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);

        List<String> words2 = new ArrayList<>();
        words2.add("change");

        NgordnetQuery nq2 = new NgordnetQuery(words2, 0, 0, 0);
        String actual2 = studentHandler.handle(nq2);
        String expected2 = "[alteration, change, demotion, increase, jump, leap, modification, saltation, transition, variation]";
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void testActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = new ArrayList<>();
        words.add("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testLargeActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                SMALL_WORDS_FILE, TOTAL_COUNTS_FILE, SYNSETS_FILE_SUBSET, HYPONYMS_FILE_SUBSET);
        List<String> words = new ArrayList<>();
        words.add("action");

        NgordnetQuery nq = new NgordnetQuery(words, 1450, 1461, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);

        words.add("action");

    }
}
