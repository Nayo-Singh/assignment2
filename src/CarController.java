import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();

                int maxX = frame.drawPanel.getWidth() - 100; // 100 is the width of the car image
                int maxY = frame.drawPanel.getHeight() - 60; // 60 is the height of the car image
                if (car.getX() < 0 || car.getX() > maxX || car.getY() < 0 || car.getY() > maxY) {
                    // if the car goes outside the boundaries, we move it back inside and turn it around
                    // clamp(value, min, max) is used to keep the value within range (min, max)
                    int newX = Math.clamp(car.getX(), 0, maxX);
                    int newY = Math.clamp(car.getY(), 0, maxY);
                    car.setPosition(newX, newY);
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                }
            }

            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.revalidate();
            frame.drawPanel.repaint();
        }
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) car.gas(gas);
    }

    void brake(int amount) {
        double b = ((double) amount) / 100;
        for (Car car : cars) car.brake(b);
    }

    void turnLeft() {
        for (Car car : cars) car.turnLeft();
    }

    void turnRight() {
        for (Car car : cars) car.turnRight();
    }

    void startAll() {
        for (Car car : cars) car.startEngine();
    }

    void stopAll() {
        for (Car car : cars) car.stopEngine();
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95 s) s.setTurboOn();
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95 s) s.setTurboOff();
        }
    }
}
