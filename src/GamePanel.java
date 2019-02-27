import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    BufferedImage playerImage;
    int playerX;
    int playerY;
    BufferedImage backgroundImage;
    int backgroundX;
    int backgroundY;

    public GamePanel() {
        playerImage = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        backgroundImage = SpriteUtils.loadImage("assets/images/background/0.png");
        playerX = 100;
        playerY = 100;
        backgroundX = 0;
        backgroundY = 600 - backgroundImage.getHeight();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backgroundImage, backgroundX, backgroundY, null);
        g.drawImage(playerImage, playerX, playerY, null);
    }

    public void gameLoop() {
        long lastLoop = 0;
        long delay = 1000 / 60;
        while(true) {
            long currentTime = System.currentTimeMillis();
            if(currentTime - lastLoop > delay) {
                runAll(); // logic game
                renderAll(); // render anh cua game
                lastLoop = currentTime;
            }
        }
    }

    private void renderAll() {
        repaint(); // goi lai ham paint
    }

    private void runAll() {
        backgroundY += 10;
        if (backgroundY > 0) {
            backgroundY = 0;
        }

        int playerSpeed = 3;
        if(GameWindow.isUpPress) {
            playerY-= playerSpeed;
        }

        if(GameWindow.isDownPress) {
            playerY+= playerSpeed;
        }
        if(GameWindow.isLeftPress) {
            playerX-= playerSpeed;
        }
        if(GameWindow.isRightPress) {
            playerX += playerSpeed;
        }
        if(playerY < 0) {
            playerY = 0;
        }
        if(playerY > 525) {
            playerY = 525;
        }
        if(playerX < 0) {
            playerX = 0;
        }
        if(playerX > 350) {
            playerX = 350;
        }
    }
}
