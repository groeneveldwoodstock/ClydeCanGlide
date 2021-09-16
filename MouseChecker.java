import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseChecker implements MouseListener
{
    GamePanel panel;
    public MouseChecker(GamePanel panel){
        this.panel = panel;
    }
    @Override
    public void mousePressed(MouseEvent me){
        
    }
    @Override
    public void mouseReleased(MouseEvent me){
        
    }
    @Override
    public void mouseEntered(MouseEvent me){
        
    }
    @Override
    public void mouseExited(MouseEvent me){
        
    }
    @Override
    public void mouseClicked(MouseEvent me){
        panel.mouseClicked(me);
    }
}
