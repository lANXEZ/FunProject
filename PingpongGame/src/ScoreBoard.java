import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScoreBoard extends JPanel implements ActionListener{
    Timer refreshTimer = new Timer(5, this);
    public ScoreBoard() {
        // TODO Auto-generated constructor stub
        refreshTimer.start();
        setPreferredSize(new Dimension(getWidth(), 70));
    }
    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        g.drawString(PingPongShow.aScore+" : "+PingPongShow.bScore, getWidth()/2-50, getHeight()/2+15);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==refreshTimer) {
            repaint();
        }

    }

}
