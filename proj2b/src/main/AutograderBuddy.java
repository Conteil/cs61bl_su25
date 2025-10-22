package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordnetGraph;

public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymsHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        NGramMap map = new NGramMap(wordFile, countFile);
        WordnetGraph wn = new WordnetGraph(synsetFile, hyponymFile);
        return new HyponymsHandler(map, wn);
    }
}
