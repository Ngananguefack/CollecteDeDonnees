package com.example.demo.Services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImportLocalitespdf {
    private static final String EXCEL_FILE_PATH = "G:/test_localite.pdf";

    private InputStream getFilePath() throws IOException {
        return new ClassPathResource(EXCEL_FILE_PATH).getInputStream();
    }


}
