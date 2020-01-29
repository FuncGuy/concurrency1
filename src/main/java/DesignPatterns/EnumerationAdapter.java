package DesignPatterns;

import java.util.Enumeration;
import java.util.Iterator;

/** Adapter converts Iterator to Enumeration  */
public class EnumerationAdapter implements Enumeration {

    private final Iterator adaptee;

    public EnumerationAdapter(Iterator adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public boolean hasMoreElements() {
        return adaptee.hasNext();
    }

    @Override
    public Object nextElement() {
        return adaptee.next();
    }
}
