

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
//        LinkedList<Integer> ll = new LinkedList<>();
//        ll.add(4, 56);
//        System.out.println(ll.getFirst());

        MyLinkedList<Integer> mll = new MyLinkedList<>();

       mll.insertFirst(4);
      mll.insertFirst(5);
       mll.insertFirst(6);
     //   mll.insertFirst(7);
     //   mll.insertFirst(8);

        System.out.println(mll);

        ListIterator<Integer> listIter = mll.listIterator();

    /*    System.out.println("hasNext(): " + listIter.hasNext());
        System.out.println("next(): " + listIter.next());
        System.out.println("hasPrevious(): " + listIter.hasPrevious());
        System.out.println("previous(): " + listIter.previous());
        System.out.println("nextIndex():" + listIter.nextIndex());
        System.out.println("previous(): " + listIter.previousIndex()); */


         listIter.next();
     //   listIter.next();

     //   listIter.remove();
     //   listIter.set(3);
      listIter.add(3);
       listIter.add(5);
   //    listIter.add(2);

        System.out.println(mll);


  //      mll.insertLast(10);
  //      mll.insertLast(11);
  //      System.out.println(mll);
//
//        System.out.println(mll.deleteFirst());
//        System.out.println(mll.deleteFirst());
//////        System.out.println(mll.indexOf(4));
////
//
//////        mll.insert(5, 99);
//        System.out.println(mll);
//        System.out.println(mll.deleteLast());
//        System.out.println(mll.deleteLast());
//        System.out.println(mll);
//
//        mll.delete(5);
//        mll.delete(4);
//        System.out.println(mll);

//        for (Integer i : mll) {
//            System.out.print(i + " ");
//        }

//        ListIterator<Integer> listIterator = mll.listIterator();
//        while (listIterator.hasNext()){
//            System.out.println(listIterator.next());
//        }
//        while (listIterator.hasPrevious()){
//            System.out.println(listIterator.previous());
//        }

    }
}
