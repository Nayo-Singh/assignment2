import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    private Map<String, BufferedImage> carImages = new HashMap<>();
    private ArrayList<Car> cars = new ArrayList<>();

    void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    private BufferedImage imageFor(Car car) {
        String name = car.getName();
        if (!carImages.containsKey(name)) {
            try {
                BufferedImage image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + name + ".jpg"));
                carImages.put(name, image);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return carImages.get(name);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : cars) {
            g.drawImage(imageFor(car), car.getX(), car.getY(), null);
        }
    }
}
