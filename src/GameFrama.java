import javax.swing.*;

public class GameFrama extends JFrame {
    GameFrama(){

        this.add(new GamePanel());
        this.setTitle("Sniek");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
