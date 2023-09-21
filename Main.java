import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Physics Sandbox", null);
        frame.setSize(Simulation.WIDTH * Simulation.PIXELS_PER_METER, Simulation.HEIGHT * Simulation.PIXELS_PER_METER);
        Insets insets = frame.getInsets();
        int insetWide = insets.left + insets.right;
        int insetTall = insets.top + insets.bottom;
        frame.setSize(frame.getWidth() + insetWide,
                frame.getHeight() + insetTall);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        double dt = 1000 / Simulation.TARGET_FPS;

        frame.createBufferStrategy(2);
        while (true) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < Simulation.PHYSICS_SUBSTEPS; i++) {
                Simulation.update(dt / 1000 / Simulation.PHYSICS_SUBSTEPS);
            }
            BufferStrategy bufferStrategy = frame.getBufferStrategy();
            try {
                Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
                g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

                Simulation.render(g);
                g.dispose();
                if (!bufferStrategy.contentsLost()) {
                    bufferStrategy.show();
                }
            } catch (IllegalStateException e) {
            }
            Thread.sleep((long) Math.max(0, (1000 / Simulation.TARGET_FPS) - (System.currentTimeMillis() - start)));
            dt = System.currentTimeMillis() - start;
        }
    }
}