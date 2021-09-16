import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player
{
    GamePanel panel;
    int x;
    int y;
    int width;;
    int height;
     
    double xspeed;
    double yspeed;
    
    Rectangle hitBox;
    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    private Image image;
    private String playerimage = "/images/character.png";
    
    public Player(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;
        
        
        
        hitBox = new Rectangle(x,y,42, 55);
    }
     public Image getPlayerImage(){
    ImageIcon i = new ImageIcon(getClass().getResource(playerimage));
    return i.getImage();
  }
    public Image getImage() {
        
        return image;
    }
    public void set(){
        if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8;
        else if(keyLeft && !keyRight) xspeed --;
        else if(keyRight && !keyLeft) xspeed ++;
        
        if(xspeed > 0 && xspeed < 0.75) xspeed = 0;
        if(xspeed < 0 && xspeed > -0.75) xspeed = 0;
        
        if(xspeed > 7) xspeed = 7;
        if(xspeed < -7) xspeed = -7;
        
        if(keyUp){
            //Check if touching ground
            hitBox.y ++;
            for (Wall wall: panel.walls){
                if(wall.hitBox.intersects(hitBox)) yspeed = -10;
            }
            
            hitBox.y --;
        }
        
        yspeed += 0.5;
        
        //Horizontal Collision
        hitBox.x += xspeed;
        for(Wall wall: panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.x -= xspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                panel.cameraX += x - hitBox.x;
                xspeed = 0;
                hitBox.x = x;
            }
        }
        //Vertical Collision
        hitBox.y += yspeed;
        for(Wall wall: panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.y -= yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y -= Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }
        
        panel.cameraX -= xspeed;
        y += yspeed;
        
        hitBox.x = x;
        hitBox.y = y;
        
        //Death Code
        if(y > 800) panel.reset();
         
    }
    
    public void draw(Graphics2D gtd){
        gtd.drawImage(getPlayerImage(), x, y, null);
        gtd.setColor(Color.WHITE);
        //gtd.fillRect(x, y, 50, 60);
        Font f = new Font("Arial", Font.BOLD, 40);
        gtd.setFont(f);
        gtd.drawString("Clyde Can Glide", 175, 35);
    }
    
}
