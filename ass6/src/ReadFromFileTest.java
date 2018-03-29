import biuoop.DialogManager;
import biuoop.GUI;
import game.HighScoresTable;
import game.ScoreInfo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * test files.
 */
public class ReadFromFileTest {

    /**
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        HighScoresTable table = new HighScoresTable(3);
        ScoreInfo scoreInfo1 = new ScoreInfo("shimon", 100);
        ScoreInfo scoreInfo2 = new ScoreInfo("other", 10000);
        ScoreInfo scoreInfo3 = new ScoreInfo("dosent matter", 10);
        ScoreInfo scoreInfo4 = new ScoreInfo("nobody", 1000);
        ScoreInfo scoreInfo5 = new ScoreInfo("nobody", 100000);
        table.add(scoreInfo1);
        table.add(scoreInfo2);
        table.add(scoreInfo3);
        table.add(scoreInfo4);
        table.add(scoreInfo5);
        HighScoresTable s = table;
        for (int i = 0; i < s.getHighScores().size(); i++) {
            System.out.print(s.getHighScores().get(i).getName() + " holds the score of: ");
            System.out.println(s.getHighScores().get(i).getScore());
        }
        //System.out.println(table.getRank(5));
        File f = new File("scores");
        try {
            table.save(f);
        } catch (Exception e) {
        }
        //HighScoresTable s = null;
        List l = null;
        try {
            table.load(f);
            //s = table.getHighScores();
            //s = HighScoresTable.loadFromFile(f);
            l = s.getHighScores();
        } catch (Exception e) {

        }
        for (int i = 0; i < s.size(); i++) {
            System.out.print(((ScoreInfo) l.get(i)).getName() + " holds the score of: ");
            System.out.println(((ScoreInfo) l.get(i)).getScore());
        }
    }
}