package di.uniba.map.b.adventure.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class RoomDesc {
    private final String[] descriptions;
    private final String[] titles;

    public RoomDesc(String descFilePath, String titleFilePath) throws IOException {
        descriptions = readLines(descFilePath);
        titles = readLines(titleFilePath);
    }

    private String[] readLines(String filePath) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            return reader.lines().toArray(String[]::new);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public RoomDesc(String[] descriptions, String[] titles) {
        this.descriptions = descriptions;
        this.titles = titles;
    }

    public String getDescription(int index) {
        if (index >= 0 && index < descriptions.length) {
            return descriptions[index];
        }
        return "";
    }

    public String getTitle(int index) {
        if (index >= 0 && index < titles.length) {
            return titles[index];
        }
        return "";
    }
}
