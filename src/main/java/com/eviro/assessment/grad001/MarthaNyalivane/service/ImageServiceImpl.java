package com.eviro.assessment.grad001.MarthaNyalivane.service;

import com.eviro.assessment.grad001.MarthaNyalivane.parser.FileParser;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;

@Service
public class ImageServiceImpl implements ImageService {
    private final FileParser fileParser;

    @Autowired
    public ImageServiceImpl(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    @Override
    public void processCSV(File csvFile) {
        fileParser.parseCSV(csvFile);
    }

    @Override
    public File convertImageDataToImage(String base64ImageData) {
        return fileParser.convertCSVDataToImage(base64ImageData);
    }

    @Override
    public URL createImageLink(File fileImage) {
        return fileParser.createImageLink(fileImage);
    }

    @Override
    public FileSystemResource getHttpImageLink(String name, String surname, String filename) {
        return null;
    }
}