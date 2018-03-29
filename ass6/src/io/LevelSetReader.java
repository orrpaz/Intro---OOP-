package io;

import levels.LevelInformation;
import menu.MenuSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


/**
 * this class is a level set reader.
 */
public class LevelSetReader {
    /**
     * gets a reader object and returns a list of
     * MenuSelection objects.
     * @param args the path.
     * @return a list of LevelInformation objects.

     */
    public static List<MenuSelection> fromReader(String[] args) {
//        Map<String, String> levelsSet = new HashMap<String, String>();
        List<MenuSelection> returnVal = new ArrayList<MenuSelection>();
        List<LevelInformation> levelSet;
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        String line;
        String path;
        try {
            String key = null;
            String message = null;
            if (args.length == 0) {
                path = ("level_sets.txt");
            } else {
                path = (args[0]);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader()
                            .getResourceAsStream(path)));
            while ((line = ((BufferedReader) reader).readLine()) != null) {
                String[] param = line.split(":");
                key = param[0];
                message = param[1];
                line = ((BufferedReader) reader).readLine();

                Reader levelsReader = new InputStreamReader(
                        ClassLoader.getSystemClassLoader().
                                getResourceAsStream(line));
                levelSet = levelSpecificationReader.fromReader(levelsReader);
                returnVal.add(new MenuSelection(key, message, levelSet));
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error in opening file.");
        }
        return returnVal;
    }
}
