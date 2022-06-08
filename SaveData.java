import java.io.*;
import java.util.ArrayList;
import javax.swing.JLabel;

public class SaveData extends Gui{
    private static File file = new File("data.txt");
    public static void makeFile() {
        try {
            if (file.createNewFile()) System.out.println("File created: " + file.getName());
            else editFile();
        } catch (IOException e) {
            System.out.println("Error creating file.");
            e.printStackTrace();
        }
    }

    public static void editFile() {
        try {
            FileWriter write = new FileWriter("data.txt");
            ArrayList<JLabel> labels = Main.project.getArrList();
            for (JLabel i : labels) {
                write.write("\n" + i.getText());
            }
            write.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
            e.printStackTrace();
        }
    }

    public static void readFile() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line = br.readLine();
            while (line != null) {
                addLabel(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    } 
}