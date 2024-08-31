package org.fluxAdvanced.sec01.assignemnt;

import org.flux.courseutil.Util;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class ReadFile {

    public static void main(String[] args) throws IOException {

        String  pathFile = "C:\\Users\\samsung\\Desktop\\SpringProjects\\reactive programming\\playList01\\Reactor\\src\\main\\java\\org\\fluxAdvanced\\sec01\\assignemnt\\data.txt" ;


        readLineByLine(pathFile).subscribe(Util.onNext());


    }


    private static Flux<String> readLineByLine(String pathFile) throws FileNotFoundException {
        return Flux.generate(
                () -> new Scanner(new File(pathFile)), // Initial state: Scanner
                (scanner, sink) -> {
                    if (scanner.hasNextLine()) {
                        String currentLine = scanner.nextLine() ;
                 //     System.out.println("Emmit : "+currentLine);
                        sink.next(currentLine); // Emit the next line
                    } else {
                        sink.complete(); // Complete when no more lines
                    }
                    return scanner; // Return the updated state
                }
        );

    }
}
