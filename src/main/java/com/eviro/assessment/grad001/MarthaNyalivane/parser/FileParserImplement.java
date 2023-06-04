package com.eviro.assessment.grad001.MarthaNyalivane.parser;


import com.eviro.assessment.grad001.MarthaNyalivane.model.AccountProfile;
import com.eviro.assessment.grad001.MarthaNyalivane.repository.AccountProfileRepository;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Component
public class FileParserImplement implements FileParser {
    private final AccountProfileRepository accountProfileRepository;
    @Value("${image.storage.directory}")
    private String imageStorageDirectory;

    @Autowired
    public FileParserImplement(AccountProfileRepository accountProfileRepository) {
        this.accountProfileRepository = accountProfileRepository;
    }

    @Override
    public void parseCSV(File csvFile) {
        try (FileReader fileReader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord : csvParser) {
                String name = csvRecord.get(0); // Assuming the name is in the first column
                String surname = csvRecord.get(1); // Assuming the surname is in the second column
                String imageFormat = csvRecord.get(2); // Assuming the image format is in the third column
                String imageData = csvRecord.get(3); // Assuming the image data is in the fourth column

                // Process the CSV record and store the data into the database
                File imageFile = convertCSVDataToImage(imageData);
                URL httpImageLink = createImageLink(imageFile);

                // Create an AccountProfile object and save it to the database
                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setAccountHolderName(name);
                accountProfile.setAccountHolderSurname(surname);
                accountProfile.setHttpImageLink(httpImageLink.toString());

                accountProfileRepository.save(accountProfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        try {
            byte[] decodedBytes = Base64.decodeBase64(base64ImageData);

            // Generate a unique filename for the image
            String filename = System.currentTimeMillis() + ".jpg";
            String filePath = imageStorageDirectory + "/" + filename;

            // Save the image file to the storage directory

            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(decodedBytes);
            outputStream.close();

            return new File(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URL createImageLink(File fileImage) {
        try {
            // Create a new directory for storing the images if it doesn't exist
            Path storageDirectory = Path.of(imageStorageDirectory);
            Files.createDirectories(storageDirectory);

            // Move the image file to the storage directory
            Path destination = storageDirectory.resolve(fileImage.getName());
            Files.move(fileImage.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // Construct the URL for accessing the image file
            URL imageUrl = new URL("http://localhost:8080/v1/api/image/" + fileImage.getName());
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}