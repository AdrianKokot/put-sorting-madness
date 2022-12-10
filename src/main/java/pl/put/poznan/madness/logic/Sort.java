package pl.put.poznan.madness.logic;

public abstract class Sort {

    /**
     * Swap the contents of a[i] and a[j]
     */
    protected void swap(Object[] a, int i, int j){
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    public abstract <T extends Comparable<? super T>> void sort(T[] a);
}
