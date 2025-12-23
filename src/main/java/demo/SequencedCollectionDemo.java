package demo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/*
新增
    SequencedCollection
    SequencedSet-LinkedHashSet
        addFirst/addLast
        removeFirst/removeLast
    SequencedMap-LinkedHashMap
        putFirst/putLast
        pollFirstEntry/pollLastEntry
统一 第一个和最后一个元素的访问方式
 */
public class SequencedCollectionDemo {
    public static void main(String[] args) {

        SequencedCollection<Integer> list=new ArrayList<>();

        /*
            点进去发现
            List<E> extends SequencedCollection<E>
         */

        list.addFirst(0);
        list.addLast(1);
        list.forEach(System.out::println);

        list.removeLast();
        list.forEach(System.out::println);

        SequencedSet<String> set=new LinkedHashSet<>();
        /*
        public class LinkedHashSet<E>
            extends HashSet<E>
            implements SequencedSet<E>, Cloneable, java.io.Serializable {
         */

        set.addFirst("1");
        set.addLast("2");
        System.out.println(set.getFirst());
        set.removeLast();

        SequencedMap<String, Integer> map = new LinkedHashMap<>();

        map.putLast("C", 3);
        map.putFirst("A", 1);
        map.putLast("B", 2);


        System.out.println(map.firstEntry()); // A=1
        System.out.println(map.lastEntry());  // C=3


//        map.pollFirstEntry();
//        map.pollLastEntry();

        System.out.println(map); // {B=2}

        map.forEach(new BiConsumer<>(){
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s+","+integer);
            }
        });





    }
}
