package ohm.softa.a02;

import javax.swing.text.AbstractDocument;
import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object>
{

    Element head;


    @Override
    public void add(Object item)
    {
        Element curr = new Element(item);
        if(head == null){
            head = curr;
            head.next = null;
        }
        else{
            curr.next = head;
            head = curr;

        }
    }

    @Override
    public int size() {
        int i = 0;
        Element cuEle = head;
        while(cuEle != null){
            i++;
            cuEle = cuEle.next;
        }
        return i;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {

        SimpleListImpl result = new SimpleListImpl();
        for (Object o: this
             ) {
            if(filter.include(o)){
                result.add(o);
            }
        }
        return result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIterator();
    }
    private class SimpleIterator implements Iterator<Object>
    {

        Element currElement;
        @Override
        public boolean hasNext() {
            if(currElement == null && head != null){
                return true;
            }
            else{
                return currElement.next != null;
            }


        }

        @Override
        public Object next() {
            if(currElement == null && head != null){
                currElement = head;
                return currElement.item;
            }
            else{

                currElement = currElement.next;
                return currElement.item;
            }

        }
    }

    static class Element
    {
        Object item;
        Element next;

        public Element(Object Item){
            item = Item;

        }

    }



}
