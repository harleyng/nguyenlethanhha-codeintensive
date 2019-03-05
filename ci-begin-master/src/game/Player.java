package game;

import tklibs.SpriteUtils;

import javax.swing.text.MutableAttributeSet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    BufferedImage image;
    Vector2D position;
    ArrayList<PlayerBullet> bullets;
    ArrayList<PlayerUlti> ulti;
    Vector2D velocity;


    public Player() {
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        position = new Vector2D(175,540);
        bullets = new ArrayList<>();
        ulti = new ArrayList<>();
        velocity = new Vector2D(0, 2);
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) position.x, (int) position.y, null);
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.render(g);
        }
        for (int i = 0; i < ulti.size(); i++) {
            PlayerUlti kamejoko = ulti.get(i);
            kamejoko.render(g);
        }
    }


    public void run() {
        move();
        limit();
        fire();
        bulletsRun();
        ultiRun();
    }

    private void move() {
        int playerSpeed = 3;
        int vx = 0;
        int vy = 0;
        if(GameWindow.isUpPress) {
            vy -= playerSpeed;
        }

        if(GameWindow.isDownPress) {
            vy += playerSpeed;
        }
        if(GameWindow.isLeftPress) {
            vx -= playerSpeed;
        }
        if(GameWindow.isRightPress) {
            vx += playerSpeed;
        }
        position.add(vx,vy);
    }

    private void limit() {
        if(position.x < 0) {
            position.set(0, position.y);
        }
        if(position.x > 384 - image.getWidth()) {
            position.set(384 - image.getWidth(), position.y);
        }
        if(position.y < 0) {
            position.set(position.x, 0);
        }
        if(position.y > 600 - image.getHeight()) {
            position.set(position.x, 600 - image.getHeight());
        }
    }

    // TODO: remove fireCount
    int frameCount;
    int loading;
    private void fire() {
        frameCount ++;
        loading ++;
        if(GameWindow.isFirePress && frameCount > 20) {
            if (loading < 90) {
                for (int i = 0; i < 10; i++) {
                    PlayerBullet bullet = new PlayerBullet();
                    bullet.position.set(position.x, position.y);
                    bullet.velocity.setAngle(-3*Math.PI/4 - ( i * Math.PI / 5));
                    bullets.add(bullet);
                    frameCount = 0;

                }
            }
            if(loading > 90) {
                for (int i = 0; i < 9; i++) {
                    PlayerUlti kamejoko0 = new PlayerUlti();
                    kamejoko0.position.set(position.x, position.y);
                    kamejoko0.velocity.setAngle(- 2 * Math.PI/5 - ( i * Math.PI / 4.5));
                    ulti.add(kamejoko0);
                }
                loading = 0;
            }
        }
    }

    private void bulletsRun() {
        for (int i = 0; i < bullets.size(); i++) {
            PlayerBullet bullet = bullets.get(i);
            bullet.run();
        }
    }

    private void ultiRun() {
        for (int i = 0; i < ulti.size(); i++) {
            PlayerUlti kamejoko = ulti.get(i);
            kamejoko.run();
        }
    }
}
