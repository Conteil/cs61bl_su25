package wordnet;


import edu.princeton.cs.algs4.In;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class WordnetGraph {
    HashMap<Integer, String[]> indexToWords = new HashMap<>();
    HashMap<String, LinkedList<Integer>> wordToIndexs = new HashMap<>();
    Graph wordnet;

    public WordnetGraph(String synsetsFilename, String hyponymsFilename) {
        In synsetsIn = new In(synsetsFilename);
        int numVertices = 0;
        while (synsetsIn.hasNextLine()) {
            String nextLine = synsetsIn.readLine();
            String[] splitLine = nextLine.split(",");
            int index = Integer.parseInt(splitLine[0]);
            numVertices = Math.max(numVertices, index + 1);
            String[] splitWord = splitLine[1].split(" ");
            for (String word: splitWord) {
                if (!wordToIndexs.containsKey(word)) {
                    LinkedList<Integer> lList = new LinkedList<>();
                    lList.add(index);
                    wordToIndexs.put(word, lList);
                } else {
                    wordToIndexs.get(word).add(index);
                }
            }
            indexToWords.put(index, splitWord);
        }

        wordnet = new Graph(numVertices);

        In hyponymsIn = new In(hyponymsFilename);
        while (hyponymsIn.hasNextLine()) {
            String nextLine = hyponymsIn.readLine();
            String[] splitLine = nextLine.split(",");
            int from = Integer.parseInt(splitLine[0]);
            for (int i = 1; i < splitLine.length; i++) {
                wordnet.addEdge(from, Integer.parseInt(splitLine[i]));
            }
        }
    }

    public List<Integer> wordIndexs(String word) {
        return wordToIndexs.get(word);
    }

    public String[] indexWords(int index) {
        return indexToWords.get(index);
    }

    public ArrayList<String> wordHyponyms(List<String> words) {
        HashSet<String> hyponymsSet = new HashSet<>();

        HashSet<Integer> nodeSet;
        HashSet<String> wordSet;
        LinkedList<Integer> nodes;
        for (String word: words) {
            nodes = new LinkedList<>();
            for (int index: wordIndexs(word)) {
                nodes.add(index);
            }
            nodeSet = wordnet.traversal(nodes);
            wordSet = new HashSet<>();
            for (int node : nodeSet) {
                for (String hyponymWord : indexWords(node)) {
                    wordSet.add(hyponymWord);
                }
            }

            if (hyponymsSet.isEmpty()) {
                hyponymsSet.addAll(wordSet);
            } else {
                hyponymsSet.retainAll(wordSet);
            }
        }

        ArrayList<String> hyponymsList = new ArrayList<>(hyponymsSet);
        Collections.sort(hyponymsList);
        return hyponymsList;
    }

    public List<String> wordHyponyms(List<String> words, int k, NGramMap map, int startYear, int endYear) {
        ArrayList<String> hyponymsList = wordHyponyms(words);
        if (k == 0) {
            return hyponymsList;
        } else {
            PriorityQueue<WordCountEntry> pq = new PriorityQueue<>(k);

            for (String word: hyponymsList) {
                TimeSeries ts = map.countHistory(word, startYear, endYear);
                double wordCount = 0;
                for (double count: ts.data()) {
                    wordCount += count;
                }
                if (wordCount > 0) {
                    if (pq.size() <= k) {
                        pq.offer(new WordCountEntry(word, wordCount));
                    } else {
                        Map.Entry<String, Double> min = pq.peek();
                        if (min.getValue() < wordCount) {
                            pq.poll();
                            pq.offer(new WordCountEntry(word, wordCount));
                        } else if (min.getValue() == wordCount && min.getKey().compareTo(word) > 0) {
                            pq.poll();
                            pq.offer(new WordCountEntry(word, wordCount));
                        }
                    }
                }
            }
            ArrayList<String> hyponyms = new ArrayList<>();
            for (WordCountEntry entry: pq) {
                hyponyms.add(entry.getKey());
            }
            Collections.sort(hyponyms);
            return hyponyms;
        }
    }
    private class WordCountEntry implements Map.Entry<String, Double>, Comparable<WordCountEntry> {
        String word;
        Double count;

        public WordCountEntry(String word, Double count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String getKey() {
            return word;
        }

        @Override
        public Double getValue() {
            return count;
        }

        @Override
        public Double setValue(Double value) {
            count = value;
            return count;
        }

        @Override
        public int compareTo(WordCountEntry o) {
            if (count - o.getValue() < 0) {
                return -1;
            } else if (count - o.getValue() == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}


