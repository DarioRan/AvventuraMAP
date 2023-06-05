package di.uniba.map.b.adventure.misc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RoomDesc {
    private final String[] descriptions;
    private final String[] titles;

    public RoomDesc(String descFilePath, String titleFilePath) throws IOException {
        descriptions = Files.readAllLines(Paths.get(descFilePath)).toArray(new String[0]);
        titles = Files.readAllLines(Paths.get(titleFilePath)).toArray(new String[0]);
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
