package pl.put.poznan.madness.logic;

import java.util.Arrays;

public class JavaSort extends Sort {
  public <T extends Comparable<? super T>> void sort(T[] a) {
    Arrays.sort(a);
  };
}
