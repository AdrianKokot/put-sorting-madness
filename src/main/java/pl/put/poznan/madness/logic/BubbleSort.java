// package pl.put.poznan.madness.logic;

// import java.util.List;

// public class BubbleSort extends Sort{

//     @Override
//     public <T extends Comparable<? super T>> void sort(List<T> a){
//         boolean swapped = true;
//         int i = a.size()-1;
//         while(swapped && i>=0){
//             swapped = false;
//             for(int j = 0; j < i; j++){
//                 if(a.get(j).compareTo(a[j+1]) > 0){
//                     swap(a,j,j+1);
//                     swapped = true;
//                 }
//             }
//             i--;
//         }
//     }
// }
