package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordnetGraph;

import java.util.List;

import static main.Main.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    NGramMap map;
    WordnetGraph wn;

    public HyponymsHandler(NGramMap map, WordnetGraph wn) {
        this.map = map;
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        return wn.wordHyponyms(words, k, map, startYear, endYear).toString();
    }
}
