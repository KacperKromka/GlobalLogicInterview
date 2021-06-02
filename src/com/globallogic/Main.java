package com.globallogic;

import java.util.*;

import java.util.stream.Collectors;

public class Main {

    private static final List<Character> characters = Arrays.asList('L', 'O', 'G', 'I', 'C', 'l', 'o', 'g', 'i', 'c');
    private static long wordsLength1 = 0;

    public static void checkFrequency(String phrase){
        String withoutSpecialChars = hideSpecialChars(phrase);
        String[] array = toArray(withoutSpecialChars);
        LinkedHashMap<Key, ComperableAtomicInteger> map = toMap(array);
        int wordsLength = map.values()
                .stream()
                .map(atomInteger -> atomInteger.get())
                .reduce(0, Integer::sum);

        StringBuilder sb = new StringBuilder();
        for (Key key : map.keySet()){
            sb.append(key);
            sb.append(" = ");
            sb.append(String.format("%.2f", map.get(key).doubleValue()/ wordsLength));
            sb.append(" (" );
            sb.append(map.get(key));
            sb.append("/");
            sb.append(wordsLength);
            sb.append(")");
            sb.append(System.lineSeparator());
        }

        sb.append("TOTAL Frequency: ");
        sb.append(String.format("%.2f", wordsLength/ (double)wordsLength1));
        sb.append(" (");
        sb.append(wordsLength);
        sb.append("/");
        sb.append(wordsLength1);
        sb.append(")");

        System.out.println(sb);
    }

    private static LinkedHashMap<Key, ComperableAtomicInteger> toMap(String[] words) {
        LinkedHashMap<Key, ComperableAtomicInteger> map = new LinkedHashMap<>();

        for (String word : words) {
            List<Character> characterList =
                    word.chars()
                            .mapToObj(intChar -> new Character((char) intChar))
                            .filter(characters::contains)
                            .collect(Collectors.toList());
            if (!characterList.isEmpty()) {
                Key key = new Key(new HashSet<Character>(characterList), word.length());
                map.computeIfAbsent(key, keyV -> new ComperableAtomicInteger()).addAndGet(characterList.size());
            }
        }
        LinkedHashMap<Key, ComperableAtomicInteger> sorted = new LinkedHashMap<>();
                map.entrySet()
                .stream()
                .sorted(Map.Entry.<Key,ComperableAtomicInteger>comparingByValue())
                .forEach(e -> sorted.put(e.getKey(), e.getValue()));
        return sorted;
    }

    private static String hideSpecialChars(String s) {
        return s.replaceAll("[^A-Za-z0-9 ]", "");
    }

    private static String[] toArray(String s) {
        wordsLength1 = s.chars().count();
        return s.split(" ");
    }


    public static void main(String[] args) {
       checkFrequency("I would love to work in global logic! ;)");
    }
}