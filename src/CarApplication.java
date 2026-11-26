void main() {
    // Instance of this class
    CarController cc = new CarController();

    Car volvo = new Volvo240();
    cc.cars.add(volvo);
    
    Car saab = new Saab95();
    saab.setPosition(0, 100);
    cc.cars.add(saab);
    // Start a new view and send a reference to the controller
    cc.frame = new CarView("CarSim 1.0", cc);
    cc.frame.drawPanel.setCars(cc.cars);
    cc.startTimer();
}