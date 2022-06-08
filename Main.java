import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.concurrent.*;

// holds max of 42 labels, but will modify code to make the GUI automatically expand the frame/rescale the labels
public class Main {
    static Gui project = new Gui();
    static final String green = "#00897b";
    static ArrayList<JLabel> labels;
    static int animationIdx, tmpHold = -1;
    static ScheduledExecutorService animation;
    public static void main(String[] args) {}  

    public static void chooseRandom() {
        labels = project.getArrList();
        if (labels.size() > 0) {    
            int rand = (int) (Math.random() * labels.size());
            tmpHold = rand;
            animate(rand);
        }
    }

    public static void animate(int idx) {
        animationIdx = 0;
        animation = Executors.newScheduledThreadPool(1);
        animation.scheduleAtFixedRate(() -> {
            if (animationIdx == idx + 1) {
                animation.shutdown();
                Gui.save.setEnabled(true);
                Gui.addButton.setEnabled(true);
                Gui.select.setEnabled(true);
                Gui.remove.setEnabled(true);
                Gui.field.setEditable(true);
                Gui.sort.setEnabled(true);
            } else {
                labels.get(animationIdx).setBackground(Color.decode(green));
                if (animationIdx != 0) labels.get(animationIdx-1).setBackground(Color.decode(Gui.blue));
                animationIdx++;
            }
        }, 0, 200, TimeUnit.MILLISECONDS);
    }

    public static void insertionSort() {
        labels = project.getArrList();
        for (int i = 1; i < labels.size(); i++) {
            int j = i;
            String tmp = labels.get(i).getText();
            while ((j > 0) && (tmp.compareToIgnoreCase(labels.get(j-1).getText()) < 0)) {
                labels.get(j).setText(labels.get(j-1).getText());
                j--;
            }
            labels.get(j).setText(tmp);
        } 
    }

    public static void selectionSort() {
        labels = project.getArrList();
        for  (int i = 0; i < labels.size()-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < labels.size(); j++) {
                if (labels.get(j).getText().compareToIgnoreCase(labels.get(minIdx).getText()) < 0) {
                    minIdx = j;
                }
            }
            String tmp = labels.get(minIdx).getText();
            labels.get(minIdx).setText(labels.get(i).getText());
            labels.get(i).setText(tmp);
        }
    }
}