package game;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerUlti {
    BufferedImage kamejoko0;
    Vector2D position;
    Vector2D velocity;

    public PlayerUlti() {
        kamejoko0 = SpriteUtils.loadImage("assets/images/player-bullets/a/ulti.png");
        position = new Vector2D();
        velocity = new Vector2D(1, -5);
    }

    public void render(Graphics g) {
        g.drawImage(kamejoko0, (int) position.x, (int) position.y, null);
    }

    public void run() {
        position.add(velocity.x, velocity.y);
        changeDirection();
    }

    private void changeDirection() {
        if (position.x > 384 - 28 && velocity.x > 0) {
            velocity.set(-velocity.x, velocity.y);
        }
        if (position.x < 0 && velocity.x < 0) {
            velocity.set(-velocity.x, velocity.y);
        }
    }
}
