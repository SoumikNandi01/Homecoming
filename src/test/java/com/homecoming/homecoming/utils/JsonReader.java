package com.homecoming.homecoming.utils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JsonReader {

    public Reader getJsonReaderFromFile(String filePath) throws IOException {
        return Files.newBufferedReader(Paths.get(filePath));
    }
}
