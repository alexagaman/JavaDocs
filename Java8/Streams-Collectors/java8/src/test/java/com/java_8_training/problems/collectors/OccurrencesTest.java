package com.java_8_training.problems.collectors;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static junit.framework.Assert.assertEquals;

@Ignore
public class OccurrencesTest {

    @Test
    public void occurrencesForAWord() {
        String word = "cool";
        //TODO #C9
        Map<String, Long> occ = new HashMap<>();
        Stream<String> lst = Arrays.stream(word.split(""));
        occ = lst.collect(groupingBy(x->x,counting()));

        assertEquals(2, (long) occ.get("o"));
        assertEquals(1, (long) occ.get("c"));
        assertEquals(1, (long) occ.get("l"));
        assertEquals(3, occ.size());

    }

    @Test
    public void occurrencesForAListOfSentences() {
        List<String> sentences = Arrays.asList("Hello everyone!", "Java 8 is here!");

        //TODO #C9
        Map<String, Long> occ = new HashMap<>();
        Stream<String> lst = sentences.stream().flatMap(x->Arrays.stream(x.split("")));
        occ = lst.collect(groupingBy(x->x,counting()));

        assertEquals(2, (long) occ.get("l"));
        assertEquals(4, (long) occ.get(" "));
        assertEquals(2, (long) occ.get("!"));
        assertEquals(2, (long) occ.get("a"));
        assertEquals(6, (long) occ.get("e"));
        assertEquals(16, occ.size());
    }

}
