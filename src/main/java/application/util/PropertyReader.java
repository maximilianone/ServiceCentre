package application.util;

import application.util.constants.ErrorMessages;
import application.util.constants.RequestParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * property reader
 */

public class PropertyReader implements ErrorMessages {
    private static Logger logger = LogManager.getLogger(PropertyReader.class.getName());

    /**
     * read properties from file
     * @param name file name
     * @return properties
     */

    public static Properties readProperties(String name) {

        try (InputStream inputStream1 = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(name)) {
            Properties properties = new Properties();
            properties.load(inputStream1);
            return properties;
        } catch (IOException e) {
            logger.error(PROPERTY_FILE_ERROR);
            throw new UncheckedIOException(e);
        }
    }
}

