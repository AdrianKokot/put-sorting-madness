package pl.put.poznan.madness.logic;

import java.util.Collections;
import java.util.List;

public class JavaSort extends Sort {
  public <T extends Comparable<? super T>> List<T> sort(List<T> a) {
    Collections.sort(a);
    return a;
  };
}
