import java.awt.Color;

class FallingObject extends Obj {
    FallingObject(double x, double y, Color c) {
        pos_x = x;
        pos_y = y;
        color = c;
    }

    @Override
    void update(double deltaTime) {
        acc_x = 0;
        acc_y = -9.81;

        vel_x = vel_x + acc_x * deltaTime;
        vel_y = vel_y + acc_y * deltaTime;

        pos_x = pos_x + vel_x * deltaTime;
        pos_y = pos_y + vel_y * deltaTime;

        if (pos_y - radius <= 0 && vel_y < 0) {
            vel_y = -vel_y;
        }
    }
}
