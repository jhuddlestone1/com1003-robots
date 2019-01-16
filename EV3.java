
import ShefRobot.*;

public class EV3 {

	final double WHEEL_DIAMETER = 0.055;	// metres
	final double WHEEL_CIRCUMFERENCE =  WHEEL_DIAMETER * Math.PI;
	
	final double WHEEL_TRACK = 0.120;		// metres
	final double WHEEL_TURN_CIRCLE = WHEEL_TRACK * Math.PI;

	Speaker speaker;
	Motor grabMotor;
	Motor leftMotor;
	Motor rightMotor;
	ColorSensor colorSensor;
	UltrasonicSensor distanceSensor;
	
	/* Constructor */
	
	public EV3(String id) {
		Robot ev3 = new Robot(id);
		grabMotor = ev3.getMediumMotor(Motor.Port.A);
		leftMotor = ev3.getLargeMotor(Motor.Port.B);
		rightMotor = ev3.getLargeMotor(Motor.Port.C);
		colorSensor = ev3.getColorSensor(Sensor.Port.S1);
		distanceSensor = ev3.getUltrasonicSensor(Sensor.Port.S2);
		speaker = ev3.getSpeaker();
	}
	
	/* Basic functions */
	
	public void grab() {
		grabMotor.forward();
		while (!grabMotor.isStalled());
		grabMotor.stop();
	}
	
	public void release() {
		grabMotor.backward();
		while (!grabMotor.isStalled());
		grabMotor.stop();
	}
	
	public void goForward() {
		leftMotor.forward();
		rightMotor.forward();	
	}
	
	public void goForward(double metres) {
		leftMotor.rotate(metres / WHEEL_CIRCUMFERENCE * 360, true);
		rightMotor.rotate(metres / WHEEL_CIRCUMFERENCE * 360, true);	
	}
	
	public void goBackward() {
		leftMotor.backward();
		rightMotor.backward();
	}
	
	public void goBackward(double metres) {
		leftMotor.rotate(-metres / WHEEL_CIRCUMFERENCE * 360, true);
		rightMotor.rotate(-metres / WHEEL_CIRCUMFERENCE * 360, true);	
	}
	
	public void turnLeft() {
		leftMotor.backward();
		rightMotor.forward();
	}
	
	public void turnLeft(double degrees) {
		leftMotor.rotate(-, true);
		rightMotor.rotate(, true);	
	}
	
	public void turnRight() {
		leftMotor.forward();
		rightMotor.backward();
	}
	
	public void turnRight(double degrees) {
		leftMotor.rotate(, true);
		rightMotor.rotate(-, true);	
	}
	
	public void stop() {
		leftMotor.stop();
		rightMotor.stop();
	}
	
	public ColorSensor.Color getColor() {
		return colorSensor.getColor();
	}
	
	public double getDistance() {
		return (double) distanceSensor.getDistance();
	}
	
	/* Composite functions */
	
	public void scanLeft() {
		
	}
	
	public void scanLeft(double degrees) {
		
	}
	
	public void scanRight() {
		
	}
	
	public void scanRight(double degrees) {
		
	}
	
	public ColorSensor.Color scanforColor() {
		
	}
	
	/* Main function */
	
	public static void main(String[] args) {
	
		EV3 robot = new EV3("dia-lego-e2");
		
		while (true) {

			switch (robot.getColor()) {
				/*
				case YELLOW:
					robot.dance();
					break;
					
				case RED:
					robot.grab();
					break;
				*/
				case WHITE:
					robot.turnRight();
				
				case BLACK:
				default:
					robot.goForward();
				
			}
		}
		
		//robot.close();
		
	}
}