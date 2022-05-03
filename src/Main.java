import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress drenor = new GameProgress(100, 15, 10, 3.5);
        GameProgress kadgar = new GameProgress(80, 13, 12, 4.1);
        GameProgress danat = new GameProgress(80, 13, 12, 4.1);

        saveGame("/IdeaProjects/Java Core/4. Serialization/savegames/saveDrenor.dat", drenor);
        saveGame("/IdeaProjects/Java Core/4. Serialization/savegames/saveKadgar.dat", kadgar);
        saveGame("/IdeaProjects/Java Core/4. Serialization/savegames/saveDanat.dat", danat);

        File dir = new File("/IdeaProjects/Java Core/4. Serialization/savegames");
//        File[] filesToZip = dir.listFiles();
        List<File> list = Arrays.asList(dir.listFiles());

        zipFile("/IdeaProjects/Java Core/4. Serialization/savegames/save.zip", list);
    }

    public static void saveGame(String url, Object GameProgress) {
        try
                (FileOutputStream fos = new FileOutputStream(url);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(GameProgress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void zipFile(String url, List<File> list) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(url))) {
            for (File file : list) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    zip.putNextEntry(new ZipEntry(file.getName()));
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zip.write(buffer);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            zip.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File file : list) {
            file.delete();
        }
    }
}