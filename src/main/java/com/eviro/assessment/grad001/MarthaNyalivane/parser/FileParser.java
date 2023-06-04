package com.eviro.assessment.grad001.MarthaNyalivane.parser;

import java.io.File;
import java.net.URL;

public interface FileParser{
    void parseCSV(File csvFile);
    File convertCSVDataToImage(String base64ImageData);
    URL createImageLink(File fileImage);
}