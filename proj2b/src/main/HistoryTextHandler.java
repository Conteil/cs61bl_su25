package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap map;

    public HistoryTextHandler(NGramMap map) {
        this.map = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        String response = "";
        for (String word: q.words()) {
            response += word + ": " + map.weightHistory(word, q.startYear(), q.endYear()).toString() + '\n';
        }
        return response;
    }
}
