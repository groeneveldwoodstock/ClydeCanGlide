
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;





public class GamePanel extends javax.swing.JPanel implements ActionListener
{
    /**
     * 
     */
    private static final long serialVersionUID = 1342618565265751614L;
    Player player;
    ArrayList<Wall> walls = new ArrayList<>();
    Timer gameTimer;
    int cameraX;
    int offset;
    
    int index;
    int score= 10;
    int lives = 6;
    int highScore = GetHighScoreValue();
    
    String jumpSound = "sounds/jump.wav";
    String endSound = "sounds/gameover.wav";
    
    Rectangle restartRect;
    Rectangle homeRect;
    
    
    
    public GamePanel(){
        restartRect = new Rectangle(495, 25, 100, 50);
        homeRect = new Rectangle(600, 25, 75, 50);
        
        player= new Player(400, 300, this);
        
        reset();
        
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(walls.get(walls.size()-1).x <800){
                    offset += 700;
                    makeWalls(offset);
                    score++;
                    
                    
                    FileWriter writeFile = null;
                    BufferedWriter writer = null;
                    if(score>highScore)
                    {
                        highScore=score;
                        try
                        {
                            writeFile = new FileWriter("highscore.txt");
                            writer = new BufferedWriter(writeFile);
                            writer.write(Integer.toString(highScore));
                            writer.close();
                        }
                        catch (Exception e)
                        {
                            //errors
                        }
                    }
                }
                
                player.set();
                for(Wall wall: walls) wall.set(cameraX);
                
                for(int i=0; i<walls.size(); i++){
                    if (walls.get(i).x<-800) walls.remove(i);
                }
                
                repaint();
            }
            
        }, 0, 17);
        
    }
    public void reset(){
        player.x = 200;
        player.y = 150;
        cameraX = 150;
        player.xspeed = 0;
        player.yspeed = 0;
        walls.clear();
        offset = -150;
        makeWalls(offset);
        lives--;
        playSound(endSound);
        if (lives<0){
            lives = 0;
        }
        score = score - 10;
    }
    public void makeWalls(int offset){
        int s = 50;
        Random rand = new Random();
        index = rand.nextInt(9);
        
        if (index == 0){
            for(int i = 0; i<14; i++) walls.add(new Wall(Color.RED, offset + i*50, 600, s, s));
        }
        else if(index ==1){
            walls.add(new Wall(Color.WHITE, offset + 350, 450, s, s));
            walls.add(new Wall(Color.WHITE, offset + 350, 500, s, s));
            walls.add(new Wall(Color.WHITE, offset + 300, 500, s, s));
            walls.add(new Wall(Color.WHITE, offset + 300, 550, s, s));
            walls.add(new Wall(Color.WHITE, offset + 300, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 250, 550, s, s));
            walls.add(new Wall(Color.WHITE, offset + 250, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 200, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 150, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 100, 550, s, s));
            walls.add(new Wall(Color.WHITE, offset + 100, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 50, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 350, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 400, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 350, 550, s, s));
            walls.add(new Wall(Color.WHITE, offset + 450, 550, s, s));
            walls.add(new Wall(Color.WHITE, offset + 450, 600, s, s));
            walls.add(new Wall(Color.WHITE, offset + 500, 550, s, s));
            walls.add(new Wall(Color.WHITE, offset + 550, 550, s, s));
            walls.add(new Wall(Color.GREEN, offset + 500, 400, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 400, s, s));
            walls.add(new Wall(Color.GREEN, offset + 600, 400, s, s));
        }
        else if(index == 2){
            for (int i = 0; i<5; i++) walls.add(new Wall(Color.GREEN, offset +i*50, 600, s, s));
            walls.add(new Wall(Color.GREEN, offset + 450, 600, s, s));
            walls.add(new Wall(Color.GREEN, offset + 500, 600, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 600, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 550, s, s));
            walls.add(new Wall(Color.GREEN, offset + 600, 600, s, s));
        }
        else if(index == 3){
            for (int i = 0; i<14; i++) walls.add(new Wall(Color.YELLOW, offset +i*50, 600, s, s));
            for (int i = 0; i<12; i++) walls.add(new Wall(Color.MAGENTA,offset +50 + i*50, 550, s, s));
            for (int i = 0; i<10; i++) walls.add(new Wall(Color.YELLOW,offset +100+i*50, 500, s, s));
            for (int i = 0; i<8; i++) walls.add(new Wall(Color.MAGENTA,offset +150+i*50, 450, s, s));
            for (int i = 0; i<6; i++) walls.add(new Wall(Color.BLUE,offset +200+i*50, 400, s, s));
        }
        else if(index == 4){
            for (int i = 0; i<12; i++) walls.add(new Wall(Color.YELLOW, offset +i*50, 550, s, s));
            for (int i = 0; i<8; i++) walls.add(new Wall(Color.ORANGE, offset +50 + i*50, 500, s, s));
            for (int i = 0; i<7; i++) walls.add(new Wall(Color.BLUE, offset +100 + i*50, 450, s, s));
            for (int i = 0; i<6; i++) walls.add(new Wall(Color.RED, offset +150+i*50, 400, s, s));
            for (int i = 0; i<5; i++) walls.add(new Wall(Color.GREEN, offset +200+i*50, 350, s, s));
            for (int i = 0; i<4; i++) walls.add(new Wall(Color.MAGENTA, offset +250+i*50, 300, s, s));
        }
        else if(index == 5){
            for (int i = 0; i<14; i++) walls.add(new Wall(Color.RED, offset +i*50, 600, s, s));
            for (int i = 0; i<9; i++) walls.add(new Wall(Color.ORANGE,offset +50 + i*50, 550, s, s));
            for (int i = 0; i<8; i++) walls.add(new Wall(Color.RED, offset +100 + i*50, 500, s, s));
            for (int i = 0; i<7; i++) walls.add(new Wall(Color.BLUE,offset +200 + i*50, 450, s, s));
            for (int i = 0; i<6; i++) walls.add(new Wall(Color.RED, offset +350+i*50, 400, s, s));
            for (int i = 0; i<5; i++) walls.add(new Wall(Color.GREEN,offset +400+i*50, 350, s, s));
            for (int i = 0; i<4; i++) walls.add(new Wall(Color.RED, offset +500+i*50, 300, s, s));
            for (int i = 0; i<3; i++) walls.add(new Wall(Color.WHITE, offset +550+i*50, 250, s, s));
            for (int i = 0; i<2; i++) walls.add(new Wall(Color.WHITE, offset +600+i*50, 200, s, s));
            for (int i = 0; i<2; i++) walls.add(new Wall(Color.WHITE, offset +650+i*50, 150, s, s));
        }
        else if(index == 6){
            for (int i = 0; i<3; i++) walls.add(new Wall(Color.RED, offset +i*50, 600, s, s));
            for (int i = 0; i<3; i++) walls.add(new Wall(Color.ORANGE,offset +250 + i*50, 600, s, s));
            for (int i = 0; i<3; i++) walls.add(new Wall(Color.RED, offset +450 + i*50, 600, s, s));
            for (int i = 0; i<7; i++) walls.add(new Wall(Color.BLUE,offset +200 + i*50, 450, s, s));
            walls.add(new Wall(Color.BLUE, offset + 50, 450, s, s));
            walls.add(new Wall(Color.BLUE, offset + 100, 400, s, s));
            for (int i = 0; i<2; i++) walls.add(new Wall(Color.GREEN, offset +600+i*50, 350, s, s));
            for (int i = 0; i<3; i++) walls.add(new Wall(Color.RED, offset +650+i*50, 600, s, s));
        
        }
        else if(index ==7){
            walls.add(new Wall(Color.GREEN, offset + 50, 550, s, s));
            walls.add(new Wall(Color.GREEN, offset + 50, 600, s, s));
            walls.add(new Wall(Color.GREEN, offset + 100, 500, s, s));
            walls.add(new Wall(Color.GREEN, offset + 100, 550, s, s));
            walls.add(new Wall(Color.GREEN, offset + 150, 450, s, s));
            walls.add(new Wall(Color.GREEN, offset + 150, 500, s, s));
            walls.add(new Wall(Color.WHITE, offset + 200, 400, s, s));
            walls.add(new Wall(Color.WHITE, offset + 200, 450, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 250, 350, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 250, 400, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 300, 300, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 300, 350, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 350, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 300, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 400, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 450, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 450, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 500, s, s));
            walls.add(new Wall(Color.GREEN, offset + 600, 500, s, s));
            walls.add(new Wall(Color.GREEN, offset + 600, 550, s, s));
        }
        
        else if(index ==8){
            walls.add(new Wall(Color.GREEN, offset + 50, 550, s, s));
            walls.add(new Wall(Color.GREEN, offset + 50, 600, s, s));
            walls.add(new Wall(Color.GREEN, offset + 100, 500, s, s));
            walls.add(new Wall(Color.GREEN, offset + 100, 550, s, s));
            walls.add(new Wall(Color.GREEN, offset + 150, 450, s, s));
            walls.add(new Wall(Color.GREEN, offset + 150, 500, s, s));
            walls.add(new Wall(Color.WHITE, offset + 200, 400, s, s));
            walls.add(new Wall(Color.WHITE, offset + 200, 450, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 250, 350, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 250, 400, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 300, 300, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 300, 350, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 350, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 300, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 400, s, s));
            walls.add(new Wall(Color.ORANGE, offset + 500, 450, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 400, s, s));
            walls.add(new Wall(Color.GREEN, offset + 550, 450, s, s));
            walls.add(new Wall(Color.RED, offset + 400, 600, s, s));
            walls.add(new Wall(Color.RED, offset + 450, 600, s, s));
            walls.add(new Wall(Color.RED, offset + 500, 600, s, s));
            walls.add(new Wall(Color.RED, offset + 550, 600, s, s));
            walls.add(new Wall(Color.RED, offset + 600, 600, s, s));
            walls.add(new Wall(Color.RED, offset + 650, 600, s, s));
        }
        
        
    }
    public void paint(Graphics g){
        super.paint(g);
        
        Graphics2D gtd = (Graphics2D) g;
        player.draw(gtd);
        for(Wall wall: walls) wall.draw(gtd);
        Font f = new Font("Arial", Font.BOLD, 25);
        gtd.setFont(f);
        if (lives < 1){
            gtd.drawString("GAME OVER" , 20, 100);
            gtd.drawString("PRESS R OR CLICK RESTART", 20, 150);
        }
        else{
            gtd.drawString("Next Terrain: " + Integer.toString(index), 20, 100);
            gtd.drawString("Score: " + Integer.toString(score), 20, 150);
            gtd.drawString("Lives: " + Integer.toString(lives), 20, 50);
        }
        //score and data
        
        
        gtd.drawString("High Score: " + Integer.toString(highScore), 495, 100);
        
        //buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Font smallFont = new Font("Arial", Font.BOLD, 15);
        gtd.setColor(Color.BLACK);
        gtd.drawRect(495,25,100,50);
        gtd.drawRect(600,25,75,50);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(496,26,98,48);
        gtd.fillRect(601,26,73,48);
        gtd.setColor(Color.BLUE);
        gtd.setFont(buttonFont);
        gtd.drawString("RESTART", 503, 58);
        gtd.setColor(Color.BLACK);
        gtd.setFont(smallFont);
        gtd.drawString("Click or", 608, 45);
        gtd.drawString("press (R)", 608, 62);
        gtd.drawString("arrow left or right to move", 482, 120);
        gtd.drawString("space bar or arrow up to jump", 482, 132);
    }
   
    void keyPressed(KeyEvent e){
        if(e.getKeyCode() ==37) player.keyLeft = true;
        if(e.getKeyCode() ==38 || e.getKeyCode() ==32) {
            player.keyUp = true;
            playSound(jumpSound);
        }
        if(e.getKeyCode() ==40) player.keyDown = true;
        if(e.getKeyCode() ==39) player.keyRight = true;
        if(e.getKeyChar() =='r'){
            score = 0;
            lives = 5; 
            playSound(endSound);
        }
    }
    void keyReleased(KeyEvent e){
        if(e.getKeyCode() ==37) player.keyLeft = false;
        if(e.getKeyCode() ==38 || e.getKeyCode() ==32) player.keyUp = false;
        if(e.getKeyCode() ==40) player.keyDown = false;
        if(e.getKeyCode() ==39) player.keyRight = false;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
    }
    
    void mouseClicked(MouseEvent me){
        if (restartRect.contains(new Point(me.getPoint().x, me.getPoint().y - 27))) {
            score = 0;
            lives = 5;
            playSound(endSound);
        }
    }
    public void playSound(String soundName)
     {
       try 
       {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
        Clip clip = AudioSystem.getClip( );
        clip.open(audioInputStream);
        clip.start( );
       }
       catch(Exception ex)
       {
         System.out.println("Error with playing sound.");
         ex.printStackTrace( );
       }
     }
     public int GetHighScoreValue()
     {
         FileReader readFile = null;
         BufferedReader reader = null;
         try
         {
             readFile = new FileReader("highscore.txt");
             reader = new BufferedReader(readFile);
             int highscore = Integer.parseInt(reader.readLine());
             return highscore;
        }
        catch (Exception e)
        {
            return 0;
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
    
