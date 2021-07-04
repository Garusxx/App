import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 600;
    static final int UNITE_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNITE_SIZE;
    int deley = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    ArrayList<WallsVoordinates> walls = new ArrayList<WallsVoordinates>();
    int wallSize;
    int level = 20;
    char direction = 'R';
    boolean runing = false;
    Timer timer;
    Random random;

    GamePanel(){

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeysAdapter());
        startGame();

    }

    public void startGame(){
        newApple();
        newWall();
        appleColison();
        runing = true;
        timer = new Timer(deley, this);
        timer.start();

    }

    public void paintComponent(Graphics g){
       super.paintComponent(g);
       draw(g);

    }

    public void draw(Graphics g){

        if(runing) {

            for (int i = 0; i < SCREEN_HEIGHT / UNITE_SIZE; i++) {
                g.drawLine(0, i * UNITE_SIZE, SCREEN_WIDTH, i * UNITE_SIZE);
            }
            for (int i = 0; i < SCREEN_WIDTH / UNITE_SIZE; i++) {
                g.drawLine(i * UNITE_SIZE, 0, i * UNITE_SIZE, SCREEN_HEIGHT);
            }


            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNITE_SIZE, UNITE_SIZE);

            for(int k =0; k < level; k++) {
                for (int j = 0; j < walls.get(k).size; j++) {
                    if(walls.get(k).direction == 1) {
                        g.setColor(Color.BLUE);
                        g.fillRect(walls.get(k).x + (UNITE_SIZE * j), walls.get(k).y, UNITE_SIZE, UNITE_SIZE);
                    }else if(walls.get(k).direction == 2){
                        g.setColor(Color.BLUE);
                        g.fillRect(walls.get(k).x, walls.get(k).y + (UNITE_SIZE * j), UNITE_SIZE, UNITE_SIZE);
                    }else if(walls.get(k).direction == 3){
                        g.setColor(Color.BLUE);
                        g.fillRect(walls.get(k).x, walls.get(k).y - (UNITE_SIZE * j), UNITE_SIZE, UNITE_SIZE);
                    }else{
                        g.setColor(Color.BLUE);
                        g.fillRect(walls.get(k). x- (UNITE_SIZE * j), walls.get(k).y, UNITE_SIZE, UNITE_SIZE);
                    }
                }
            }

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNITE_SIZE, UNITE_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNITE_SIZE, UNITE_SIZE);
                }

            }

            g.setColor(Color.yellow);
            g.setFont(new Font("Inke Free",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score : " + applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score :" + applesEaten))/2,g.getFont().getSize());

        }else{
            gameOver(g);
        }
    }

    public void newApple(){

        appleX = random.nextInt((int)(SCREEN_WIDTH/UNITE_SIZE))*UNITE_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNITE_SIZE))*UNITE_SIZE;

    }

    public void newWall(){

        int wallX;
        int wallY;



        for( int i =0; i < level;i++) {
            wallX = (random.nextInt((int)(SCREEN_WIDTH /UNITE_SIZE))*UNITE_SIZE);
            wallY = (random.nextInt((int)(SCREEN_HEIGHT/UNITE_SIZE))*UNITE_SIZE);

            if(wallX < (SCREEN_WIDTH*0.2) && wallY < (SCREEN_HEIGHT*0.2)) {
                wallX = wallX + 4*UNITE_SIZE;
                wallY = wallY + 4*UNITE_SIZE;
            }else if( wallX > (SCREEN_WIDTH - SCREEN_WIDTH *0.2) && wallY > (SCREEN_HEIGHT - SCREEN_HEIGHT*0.2)){
                wallX = (random.nextInt((int)(SCREEN_WIDTH -150 /UNITE_SIZE))*UNITE_SIZE);
                wallY = (random.nextInt((int)(SCREEN_HEIGHT -100/UNITE_SIZE))*UNITE_SIZE);
            }

            wallSize = random.nextInt(5);
            int direction = random.nextInt(4);
            WallsVoordinates wallsVoordinates = new WallsVoordinates(wallX,wallY,wallSize,direction);
            walls.add(wallsVoordinates);
        }
    }

    public void move(){

        for( int i = bodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction){
            case 'U':
                y[0] = y[0] - UNITE_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNITE_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNITE_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNITE_SIZE;
                break;

        }

    }

    public void checkApple(){

        if((x[0] == appleX)&&(y[0] == appleY)){
            bodyParts = bodyParts + 3;
            applesEaten++;
            newApple();
        }

    }

    public void appleColison(){

        for( int i = bodyParts; i >0 ;i--){
            if((appleX == x[i])&&(appleY == y[i])){
                newApple();
            }
        }

        for(int k =0; k < level; k++) {
            for (int j = 0; j < walls.get(k).size; j++) {
                if(((walls.get(k).direction == 1)&&(appleX == walls.get(k).x + (UNITE_SIZE * j))&&(appleY ==  walls.get(k).y))||((appleX == walls.get(k).x)&&(appleY == walls.get(k).y))) {
                    newApple();
                }else if((walls.get(k).direction == 2)&&(appleX == walls.get(k).x)&&(appleY ==  walls.get(k).y + (UNITE_SIZE * j))||((appleX == walls.get(k).x)&&(appleY == walls.get(k).y))){
                    newApple();
                }else if((walls.get(k).direction == 3)&&(appleX == walls.get(k).x)&&(appleY ==  walls.get(k).y - (UNITE_SIZE * j))||((appleX == walls.get(k).x)&&(appleY == walls.get(k).y))){
                    newApple();
                }else if(((walls.get(k).direction == 0)&&(appleX == walls.get(k).x- (UNITE_SIZE * j))&&(appleY ==  walls.get(k).y ))||((appleX == walls.get(k).x)&&(appleY == walls.get(k).y))){
                    newApple();
                }
            }
        }
    }

    public void checkColisohn(){

        for( int i = bodyParts; i >0 ;i--){
            if((x[0] == x[i])&&(y[0] == y[i])){
                runing = false;
            }
        }

        for(int k =0; k < level; k++) {
            for (int j = 0; j < walls.get(k).size; j++) {
                if(((walls.get(k).direction == 1)&&(x[0] == walls.get(k).x + (UNITE_SIZE * j))&&(y[0] ==  walls.get(k).y))||((x[0] == walls.get(k).x)&&(y[0] == walls.get(k).y))) {
                    runing = false;
                }else if((walls.get(k).direction == 2)&&(x[0] == walls.get(k).x)&&(y[0] ==  walls.get(k).y + (UNITE_SIZE * j))||((x[0] == walls.get(k).x)&&(y[0] == walls.get(k).y))){
                    runing = false;
                }else if((walls.get(k).direction == 3)&&(x[0] == walls.get(k).x)&&(y[0] ==  walls.get(k).y - (UNITE_SIZE * j))||((x[0] == walls.get(k).x)&&(y[0] == walls.get(k).y))){
                    runing = false;
                }else if(((walls.get(k).direction == 0)&&(x[0] == walls.get(k).x- (UNITE_SIZE * j))&&(y[0] ==  walls.get(k).y ))||((x[0] == walls.get(k).x)&&(y[0] == walls.get(k).y))){
                    runing = false;
                }
            }
        }

        if(x[0] < 0){
            runing = false;
        }
        if(x[0] > SCREEN_WIDTH){
            runing = false;
        }
        if(y[0] < 0){
            runing = false;
        }
        if(y[0] > SCREEN_HEIGHT){
            runing = false;
        }

        if(!runing){
            timer.stop();
        }

    }

    public void gameOver(Graphics g){

        g.setColor(Color.red);
        g.setFont(new Font("Inke Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("GAME OVER",(SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2,SCREEN_HEIGHT/2);

        g.setColor(Color.red);
        g.setFont(new Font("Inke Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score : " + applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score :" + applesEaten))/2,g.getFont().getSize());


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(runing){
            move();
            checkApple();
            appleColison();
            checkColisohn();
        }
        repaint();

    }

    public class MyKeysAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
            }

        }
    }
}
