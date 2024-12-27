import java.awt.BorderLayout;
import javax.swing.JFrame;

public class PingPongTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JFrame frame = new JFrame("Ping-Pong Game");
        frame.add(new ScoreBoard(),BorderLayout.NORTH);
        frame.add(new PingPongShow());
        frame.setSize(800, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
