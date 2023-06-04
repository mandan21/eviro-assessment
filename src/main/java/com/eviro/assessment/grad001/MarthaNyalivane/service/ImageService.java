package com.eviro.assessment.grad001.MarthaNyalivane.service;

import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.net.URL;

public interface ImageService {
    void processCSV(File csvFile);

    File convertImageDataToImage(String base64ImageData);

    URL createImageLink(File fileImage);

    FileSystemResource getHttpImageLink(String name, String surname, String filename);
}

