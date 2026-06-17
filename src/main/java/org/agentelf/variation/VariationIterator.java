package org.agentelf.variation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VariationIterator implements Iterator<Object[]> {

    private final Iterable[] variations;
    private final Iterator[] iterators;
    private final Object[] currentValues;

    private boolean hasNext;

    public VariationIterator(Iterable[] variations) {
        this.variations = variations;
        this.iterators = new Iterator[variations.length];
        this.currentValues = new Object[variations.length];
        if (variations.length == 0) {
            hasNext = false;
            return;
        }
        for (int i = 0; i < variations.length; i++) {
            iterators[i] = variations[i].iterator();
            if (!iterators[i].hasNext()) {
                hasNext = false;
                return;
            }
            currentValues[i] = iterators[i].next();
        }
        hasNext = true;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Object[] next() {
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        Object[] result = currentValues.clone();
        advance();
        return result;
    }

    private void advance() {
        for (int i = variations.length - 1; i >= 0; i--) {
            if (iterators[i].hasNext()) {
                currentValues[i] = iterators[i].next();
                // Reset all iterators to the right
                for (int j = i + 1; j < variations.length; j++) {
                    iterators[j] = variations[j].iterator();
                    if (!iterators[j].hasNext()) {
                        hasNext = false;
                        return;
                    }
                    currentValues[j] = iterators[j].next();
                }
                return;
            }
        }
        hasNext = false;
    }
}