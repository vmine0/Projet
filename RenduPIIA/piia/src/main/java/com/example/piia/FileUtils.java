package com.example.piia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {
    public static List<String> readFileLines(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath));
    }


}
