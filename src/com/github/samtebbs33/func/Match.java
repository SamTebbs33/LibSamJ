package com.github.samtebbs33.func;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Created by samtebbs on 01/02/2016.
 */
public class Match<E> {

    private Matcher<E>[] matchers;

    public Match(BiPredicate<E, E> predicate, Matcher<E>... matchers) {
        this.matchers = matchers;
        for (Matcher<E> matcher : matchers) matcher.setPredicate(predicate);
    }

    public boolean match(E obj) {
        for (Matcher<E> matcher : matchers)
            if (matcher.matches(obj)) {
                matcher.run();
                return true;
            }
        return false;
    }

    public boolean multimatch(E obj) {
        boolean matched = false;
        for (Matcher<E> matcher : matchers)
            if (matcher.matches(obj)) {
                matched = true;
                matcher.run();
            }
        return matched;
    }

    public void match(E obj, Runnable def) {
        if (!match(obj)) def.run();
    }

}
