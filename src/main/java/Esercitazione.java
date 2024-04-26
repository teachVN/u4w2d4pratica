import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Esercitazione {
    public static void main(String[] args){
        Prodotto libro1 = new Prodotto(100l, "Il signore degli anelli", "Libri", 101);
        Prodotto libro2 = new Prodotto(101L, "Il giorno nero", "Libri", 102);
        Prodotto libro3 = new Prodotto(102L, "Collision", "Libri", 10);
        Prodotto libro4 = new Prodotto(103L, "Marracash", "Libri", 13);
        Prodotto baby1 = new Prodotto(104L, "pannolini", "Baby", 7);
        Prodotto baby2 = new Prodotto(105L, "tutina", "Baby", 15);
        Prodotto baby3 = new Prodotto(106L, "cappello", "Baby", 10);
        Prodotto boys1 = new Prodotto(107L, "scarpe", "Boys", 70);
        Prodotto boys2 = new Prodotto(108L, "felpa", "Boys", 40);
        Prodotto boys3 = new Prodotto(109L, "pantalone", "Boys", 40);

        List<Prodotto> prodotti = List.of(libro1, libro2,libro3,libro4,boys1,boys2,boys3,baby1,baby2,baby3);

        Cliente carlo = new Cliente(1l, "Carlo", 2);
        Cliente roberta = new Cliente(2l, "Roberta", 2);
        Cliente sara = new Cliente(3l, "Sara", 2);

        List<Cliente> clienti = List.of(carlo, roberta, sara);

        Ordine o1 = new Ordine(1000l,"aperto", LocalDate.of(2021, 2, 10), LocalDate.of(2021, 3, 10),
                List.of(libro1, libro2, baby1), carlo);

        Ordine o2 = new Ordine(1001l,"chiuso", LocalDate.of(2021, 1, 10), LocalDate.of(2021, 3, 10),
                List.of(boys1, boys2, baby1), roberta);

        Ordine o3 = new Ordine(1002l,"chiuso", LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 20),
                List.of(libro3, libro4, boys3), sara);

        List<Ordine> ordini = List.of(o1, o2, o3);

        //Esercizio1
        Map<Cliente, List<Ordine>> m1 =ordini.stream().collect(Collectors.groupingBy(Ordine::getCliente));
        m1.forEach((cliente, ordines) -> System.out.println(cliente + " -> " + ordines));

        //Esercizio2
        Map<Cliente, Double> m2 = ordini.stream().collect(Collectors.groupingBy(Ordine::getCliente,
                Collectors.summingDouble(value -> value.getProdotti().stream().
                        mapToDouble(Prodotto::getPrezzo).sum())));
        m2.forEach((cliente, sommaOrdini) -> System.out.println(cliente + " -> " + sommaOrdini));

        //Esercizio3
        Map<String, Optional<Prodotto>> m3 = prodotti.stream().collect(Collectors.groupingBy(Prodotto::getCategoria,
                Collectors.maxBy(Comparator.comparingDouble(Prodotto::getPrezzo))));
        m3.forEach((categoria, prodottoMax) -> System.out.println(categoria + " -> " + prodottoMax.get()));

        //Esercizio4
//        OptionalDouble media = ordini.stream().flatMap(ordine -> ordine.getProdotti().stream()).
//                mapToDouble(Prodotto::getPrezzo).average();
//
//        System.out.println(media.getAsDouble());

        Double media2 = ordini.stream().collect(Collectors.averagingDouble(value -> value.getProdotti().stream().
                mapToDouble(Prodotto::getPrezzo).sum()));
        System.out.println(media2);
        System.out.println();


        //creazione di una stringa che contenga tutti le caratteristiche dei prodotti in
        //questo formato prop1Prodotto1@prop2Prodotto1@prop3Prodotto1#prop1Prodotto2....
        String prodottiString =prodotti.stream().
                map(prodotto -> prodotto.getId().longValue()+"@"+prodotto.getNome()+"@"
                +prodotto.getCategoria()+"@"+ prodotto.getPrezzo()).collect(Collectors.joining("#"));

        System.out.println(prodottiString);

        File file = new File("./../persitence/prova.txt");

        try {//inserimento in un file della stringa prodotta
            FileUtils.writeStringToFile(file, prodottiString, Charset.defaultCharset());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }


        try {//lettura dal file della stringa prodotta e split delle stringhe per ottenere
            //le stringhe dei singoli prodotti
            String str = FileUtils.readFileToString(file, Charset.defaultCharset());

            String[] prodottiStr =  str.split("#");

            Arrays.stream(prodottiStr).forEach(s -> System.out.println(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }

    }
}
