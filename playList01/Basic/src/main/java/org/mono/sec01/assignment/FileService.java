package org.mono.sec01.assignment;


import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
public class FileService {

    private final static String PATH1 = "C:\\Users\\samsung\\Desktop\\SpringProjects\\reactive programming\\playList01\\Mono\\src\\main\\resources\\assignment\\sec01\\file01.txt";
    private final static String PATH2 = "C:\\Users\\samsung\\Desktop\\SpringProjects\\reactive programming\\playList01\\Mono\\src\\main\\resources\\assignment\\sec01\\file02.txt";

    public static void main(String[] args) throws IOException {

        File file1 = new File(PATH1) ;

        System.out.println(file1.exists()); ;

        String contentFile1 = readFile(PATH1);
        String contentFile2 = readFile(PATH2);



        writeFile(PATH1,"new data for file 1") ;
        writeFile(PATH2,"new data for file 2") ;

         contentFile1 = readFile(PATH1);
         contentFile2 = readFile(PATH2);


    }


    private static String readFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path)).toString();
    }

    private static void writeFile(String path,String content) throws IOException {
        Files.writeString(Paths.get(path),content) ;
    }


}
