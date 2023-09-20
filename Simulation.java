import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

class Simulation {
    // World width in meters
    static final int WIDTH = 80;
    // World height in meters
    static final int HEIGHT = 80;
    // Frames per second target
    static final double TARGET_FPS = 90;
    // How many pixels represent a meter
    static final int PIXELS_PER_METER = 10;

    static List<Obj> objects = new ArrayList<>();

    static {
        // Add objects
        objects.add(new FreierFall(20, 60, Color.RED));
        objects.add(new FreierFall(40, 50, Color.BLUE));
    }

    static void update(double deltaTime) {
        for (Obj obj : objects) {
            obj.update(deltaTime);
        }
    }

    static void render(Graphics g) {
        g.clearRect(0, 0, WIDTH * PIXELS_PER_METER, HEIGHT * PIXELS_PER_METER);
        for (Obj obj : objects) {
            obj.render(g);
        }
    }
}

abstract class Obj {
    double pos_x = 0;
    double pos_y = 0;
    double vel_x = 0;
    double vel_y = 0;
    double acc_x = 0;
    double acc_y = 0;

    int radius = 1;
    Color color = Color.BLACK;

    abstract void update(double deltaTime);

    void render(Graphics g) {
        g.setColor(color);
        g.fillOval(Math.round((float) (pos_x - radius) * Simulation.PIXELS_PER_METER),
                Simulation.HEIGHT * Simulation.PIXELS_PER_METER
                        - Math.round((float) (pos_y + radius) * Simulation.PIXELS_PER_METER),
                radius * 2 * Simulation.PIXELS_PER_METER, radius * 2 * Simulation.PIXELS_PER_METER);
    }
}