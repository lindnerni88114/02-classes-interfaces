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
        Element newElement = new Element(item);
        if(head == null){
            head = newElement;

        }
        else{
            Element curr = this.head;
            while(curr.getNext() != null){
                curr = curr.getNext();
            }
            curr.setNext(item);


        }
    }

    @Override
    public int size() {
        int i = 0;
        Element cuEle = head;
        while(cuEle != null){
            i++;
            cuEle = cuEle.getNext();
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
                return currElement.getNext() != null;
            }


        }

        @Override
        public Object next() {
            if(currElement == null && head != null){
                currElement = head;
                return currElement.getItem();
            }
            else{

                currElement = currElement.getNext();
                return currElement.getItem();
            }

        }
    }

    //static for less complexity because static doesn´t allow access to objects from outer class
    // private because we use this class just for intern logic
    private static class Element
    {
        private Object item;
        private Element next;

        public Element(Object Item){
            item = Item;

        }

        public Object getItem(){
            return item;
        }

        public Element getNext(){
            return next;
        }

        public Element setNext(Object o){
            return next = new Element(o);
        }



    }



}
