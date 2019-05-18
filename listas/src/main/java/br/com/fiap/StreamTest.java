package br.com.fiap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

        String filename = "C:\\Users\\logonrmlocal\\Documents\\Aula\\fiap-java\\listas\\lines.txt";

        List<String> list = new ArrayList<String>();

        try (Stream<String> stream = Files.lines(Paths.get(filename))) {

            list = stream
                .filter(line -> !line.startsWith("line 3"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

//        list.forEach(System.out::println);
    }
}
