/***
 * Created by Ellen Veomett, using code from David Galles
 * for CS 245
 * A good deal of this code is from:
 * https://www.cs.usfca.edu/~galles/cs245/lecture/LinkedList.java.html
 * Note: right now we don't use the List interface or the ListIterator
 */

public class LinkedList<T> implements List<T> 
{
    private Link head;
    private Link tail;
    private int length;

    public LinkedList()
    {
        head = tail = new Link();
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public T get(int index)
    {
        assert (index >= 0 && index < length) : "Index not in list";
        Link tmp = head.next();
        for (int i = 0; i < index; i++)
        {
            tmp = tmp.next();
        }
        return tmp.element();
    }

    @Override
    public boolean add(T elem)
    {
        tail.setNext(new Link(elem, null));
        tail = tail.next();
        length++;
        
        return true;
    }

    @Override
    public void add(int index, T elem)
    {
        assert (index >= 0 && index <= length) : "Index not in list";
        
        Link tmp = head;
        for (int i = 0; i < index; i++)
        {
            tmp = tmp.next();
        }
        tmp.setNext(new Link(elem, tmp.next()));
        if (length == index) {
            tail = tmp.next();
        }
        
        length++;
    }

    @Override
    public T remove(int index)
    {
        Link removeLink = null;
        
        assert (index >= 0 && index < length) : "Index not in list";
        Link tmp = head;
        for (int  i = 0; i < index; i++)
        {
            tmp = tmp.next();
        }
        removeLink = tmp.next();
        tmp.setNext(removeLink.next());
        length--;
        
        return removeLink.element();
    }
    
    public Link getFirstLink()
    {
        return head.next();
    }


    public void clear()
    {
        head.setNext(null);
        tail = head;
        length = 0;
    }


    public void print()
    {
        Link curLink = head;
        while (curLink.next() != null){
            curLink = curLink.next();
            System.out.print(curLink.element() + " ");
        }
        System.out.print("\n");
    }


    /*----------------------------------------------------- */
    /* Nested class -- Link                                 */
    /*----------------------------------------------------- */


    public class Link {

        /*----------------------------------------------------- */
        /*  Private Data Members -- Link                        */
        /*----------------------------------------------------- */

        private T element;
        private Link next;

        /*----------------------------------------------------- */
        /*  Constructors -- Link                                */
        /*----------------------------------------------------- */

        Link(T elem, Link nextelem) {
            element = elem;
            next = nextelem;
        }
        
        Link() { }

        /*----------------------------------------------------- */
        /*  Access Methods -- Link                              */
        /*----------------------------------------------------- */

        Link next() {
            return next;
        }

        T element() {
            return element;
        }

        void setNext(Link nextelem) {
            next = nextelem;
        }

    }

}