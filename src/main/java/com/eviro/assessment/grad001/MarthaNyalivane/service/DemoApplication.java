package com.eviro.assessment.grad001.MarthaNyalivane.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        // Get the ImageService bean
        ImageService imageService = context.getBean(ImageService.class);

        // Process the CSV file
        File csvFile = new File("path/to/csv/GraduateDev_AssessmentCsv_Ref003.csv");
        imageService.processCSV(csvFile);

        // Cleanup and close the application context
        context.close();
    }
}
