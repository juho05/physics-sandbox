import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

class Simulation {
    // World width in meters
    static final double WIDTH = 80;
    // World height in meters
    static final double HEIGHT = 80;
    // Frames per second target
    static final double TARGET_FPS = 60;
    // How many pixels represent a meter
    static final double PIXELS_PER_METER = 10;
    // Physics updates per frame
    static final int PHYSICS_SUBSTEPS = 4;

    static List<Obj> objects = new ArrayList<>();

    static {
        // Add objects
        objects.add(new FallingObject(20, 60, Color.RED));
        objects.add(new FallingObject(40, 50, Color.BLUE));
    }

    static void update(double deltaTime) {
        for (Obj obj : objects) {
            obj.update(deltaTime);
        }
    }

    static void render(Graphics g) {
        g.clearRect(0, 0, (int) Math.round(WIDTH * PIXELS_PER_METER), (int) Math.round(HEIGHT * PIXELS_PER_METER));
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

    double radius = 1;
    double mass = 1;
    Color color = Color.BLACK;

    abstract void update(double deltaTime);

    void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (Math.round((pos_x - radius) * Simulation.PIXELS_PER_METER)),
                (int) (Simulation.HEIGHT * Simulation.PIXELS_PER_METER
                        - Math.round((pos_y + radius) * Simulation.PIXELS_PER_METER)),
                (int) Math.round(radius * 2 * Simulation.PIXELS_PER_METER),
                (int) Math.round(radius * 2 * Simulation.PIXELS_PER_METER));
    }
}