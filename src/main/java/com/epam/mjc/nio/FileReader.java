package com.epam.mjc.nio;

import java.io.*;
import java.util.logging.Logger;


public class FileReader {

    private static final Logger logger = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        try (InputStream inputStream = new FileInputStream(file)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                setProfileField(profile, line);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return profile;
    }

    private void setProfileField(Profile profile, String line) {
        String value = line.split("\\s")[1];
        if (line.contains("Name")) {
            profile.setName(value);
        } else if (line.contains("Age")) {
            profile.setAge(Integer.parseInt(value));
        } else if (line.contains("Email")) {
            profile.setEmail(value);
        } else if (line.contains("Phone")) {
            profile.setPhone(Long.parseLong(value));
        } else {
            logger.info("Not existed field");
        }
    }
}
