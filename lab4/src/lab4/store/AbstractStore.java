package lab4.store;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class AbstractStore implements Serializable, Iterable<Object> {
    protected Object[] arr = new Object[3];
    protected int count = 0;

    protected void add(Object newObject) {
        if (arr.length == count)
            arr = Arrays.copyOf(arr, count + count / 2);
        arr[count++] = newObject;
    }

    public Object[] getArr() {
        return Arrays.copyOf(arr, count);
    }

    public Object getLastElement() {
        return arr[count - 1];
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Timber catalog: \n");
        for (Object obj : this) {
            sb.append(obj).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Object> iterator() {
        return new StoreIterator();
    }

    public ListIterator<Object> listIterator() {
        return new StoreListIterator();
    }

    private class StoreIterator implements Iterator<Object> {
        int current = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current < count;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[current++];
        }

        @Override
        public void remove() {
            System.arraycopy(arr, current, arr, current - 1, count-- - current--);
        }
    }

    private class StoreListIterator extends StoreIterator implements ListIterator<Object> {

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the reverse direction.  (In other words,
         * returns {@code true} if {@link #previous} would return an element
         * rather than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the reverse direction
         */
        @Override
        public boolean hasPrevious() {
            return current >= 0;
        }

        /**
         * Returns the previous element in the list and moves the cursor
         * position backwards.  This method may be called repeatedly to
         * iterate through the list backwards, or intermixed with calls to
         * {@link #next} to go back and forth.  (Note that alternating calls
         * to {@code next} and {@code previous} will return the same
         * element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous
         *                                element
         */
        @Override
        public Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (!hasNext()) {
                current--;
            }
            return arr[current--];
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #next}. (Returns list size if the list
         * iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code next}, or list size if the list
         * iterator is at the end of the list
         */
        @Override
        public int nextIndex() {
            return current + 1;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #previous}. (Returns -1 if the list
         * iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code previous}, or -1 if the list
         * iterator is at the beginning of the list
         */
        @Override
        public int previousIndex() {
            return current - 1;
        }

        /**
         * Replaces the last element returned by {@link #next} or
         * {@link #previous} with the specified element (optional operation).
         * This call can be made only if neither {@link #remove} nor {@link
         * #add} have been called after the last call to {@code next} or
         * {@code previous}.
         *
         * @param o the element with which to replace the last element returned by
         *          {@code next} or {@code previous}
         * @throws UnsupportedOperationException if the {@code set} operation
         *                                       is not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of the specified
         *                                       element prevents it from being added to this list
         * @throws IllegalStateException         if neither {@code next} nor
         *                                       {@code previous} have been called, or {@code remove} or
         *                                       {@code add} have been called after the last call to
         *                                       {@code next} or {@code previous}
         */
        @Override
        public void set(Object o) {
            arr[current] = o;
        }

        /**
         * Inserts the specified element into the list (optional operation).
         * The element is inserted immediately before the element that
         * would be returned by {@link #next}, if any, and after the element
         * that would be returned by {@link #previous}, if any.  (If the
         * list contains no elements, the new element becomes the sole element
         * on the list.)  The new element is inserted before the implicit
         * cursor: a subsequent call to {@code next} would be unaffected, and a
         * subsequent call to {@code previous} would return the new element.
         * (This call increases by one the value that would be returned by a
         * call to {@code nextIndex} or {@code previousIndex}.)
         *
         * @param o the element to insert
         * @throws UnsupportedOperationException if the {@code add} method is
         *                                       not supported by this list iterator
         * @throws ClassCastException            if the class of the specified element
         *                                       prevents it from being added to this list
         * @throws IllegalArgumentException      if some aspect of this element
         *                                       prevents it from being added to this list
         */
        @Override
        public void add(Object o) {
            AbstractStore.this.add(o);
        }
    }
}
