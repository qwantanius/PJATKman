package src.services.utils;

import src.views.BasicView;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SerializationUtil {
    private static final String BASE_PATH = String.format("%s\\pacman\\src\\resources\\bin\\", BasicView.getCwd());

    public static String fromMillisToMinutesAndSeconds(long elapsedTime) {
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;
        return String.format("%s minutes %s seconds", minutes, seconds);
    }

    public static String fromMillisToDate(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(formatter);
    }

    public static <T extends Serializable> void serialize(T objectToSerialize) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BASE_PATH + System.currentTimeMillis()))) {
            oos.writeObject(objectToSerialize);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Object> deserializeAll() throws IOException, ClassNotFoundException {
        try {
            File folder = new File(BASE_PATH);
            File[] listOfFiles = folder.listFiles();
            ArrayList<Object> deserializedObjects = new ArrayList<>();

            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        deserializedObjects.add(ois.readObject());
                    }
                }
            }
            return deserializedObjects;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
