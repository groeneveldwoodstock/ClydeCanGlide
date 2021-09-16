import java.awt.Dimension;
import java.awt.Toolkit;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Write a description of class PlatformerGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlatformerGame
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class PlatformerGame
     */
    
        public static void main(String[] args)
        {
            MainFrame frame = new MainFrame();
            frame.setSize(700,700);
            
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((int)(screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int)(screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
            
            frame.setResizable(false);
            frame.setTitle("Platformer Game");
            frame.setVisible(true);
            
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        }
    

    
    
}
