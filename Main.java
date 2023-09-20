import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Physics Sandbox", null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Simulation.render(g);
            }
        };
        canvas.setPreferredSize(new Dimension(Simulation.WIDTH * Simulation.PIXELS_PER_METER,
                Simulation.HEIGHT * Simulation.PIXELS_PER_METER));

        frame.setContentPane(canvas);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        double dt = 1000 / Simulation.TARGET_FPS;

        while (true) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < Simulation.PHYSICS_SUBSTEPS; i++) {
                Simulation.update(dt / 1000 / Simulation.PHYSICS_SUBSTEPS);
            }
            canvas.repaint();
            Thread.sleep((long) Math.max(0, (1000 / Simulation.TARGET_FPS) - (System.currentTimeMillis() - start)));
            dt = System.currentTimeMillis() - start;
        }
    }
}