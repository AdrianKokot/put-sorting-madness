package pl.put.poznan.madness.logic;

import java.util.Collections;
import java.util.List;

public class JavaSort extends Sort {
  public <T extends Comparable<? super T>> void sort(List<T> a) {
    Collections.sort(a);
  };
}
