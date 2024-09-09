import javax.swing.JFrame;

public class checkingTest extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Checker!");
		Checking myGame = new Checking();
		frame.add(myGame);
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
