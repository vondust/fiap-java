package br.com.fiap;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
        hashSet();
        sortedSet();
        arrayList();
        hashMap();
    }
    static void hashSet() {
        Set<String> nomes = new HashSet<String>();
        nomes.add("Rafael");
        nomes.add("Bruno");
        nomes.add("Thiago");
        nomes.add("Thiago");
        System.out.println("HashSet");
        System.out.println(nomes);
//        System.out.println(nomes.contains("teste"));
    }

    static void sortedSet() {
        Set<String> nomes = new LinkedHashSet<>();
        nomes.add("Rafael");
        nomes.add("Bruno");
        nomes.add("Thiago");
        nomes.add("Thiago");
        System.out.println("LinkedHashSet");
        System.out.println(nomes);
    }

    static void arrayList() {
        List<String> nomes = new ArrayList<String>();
        nomes.add("Rafael");
        nomes.add("Bruno");
        nomes.add("Thiago");
        nomes.add("Thiago");
        System.out.println("ArrayList");
        System.out.println(nomes);
    }

    static void hashMap() {
        Map<String, String> nomes = new HashMap<>();
        nomes.put("1", "Rafael");
        nomes.put("2", "Bruno");
        nomes.put("3","Thiago");
        nomes.put("3","Teste");
        System.out.println("HashMap");
        System.out.println(nomes.get("2"));
        System.out.println(nomes);
    }


}
