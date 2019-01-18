/** 
* Utility functions for Ev3 robot
*@author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
*/

import ShefRobot.*;

public class Ev3 {
	
	// Robot geometry constants
	final int WHEEL_DIAMETER = 55;	// mm
	final int WHEEL_CIRCUMFERENCE = (int) Math.round(WHEEL_DIAMETER * Math.PI);
	final int WHEEL_TRACK = 120;	// mm
	final int WHEEL_TURN_CIRCLE = (int) Math.round(WHEEL_TRACK * Math.PI);
	final double TURN_RATIO = WHEEL_TURN_CIRCLE / WHEEL_CIRCUMFERENCE;

	// Instance variables
	Robot ev3;
	int motorSpeed;
	Speaker speaker;
	Motor grabMotor;
	Motor leftMotor;
	Motor rightMotor;
	ColorSensor colorSensor;
	UltrasonicSensor distanceSensor;

	/**
	* Constructor function for an instance of Ev3 object
	* @param id     The id of the robot, e.g. "dia-lego-e2"
	*/
	public Ev3(String id) {
		ev3 = new Robot(id);
		
		// get Motor objects
		grabMotor = ev3.getMediumMotor(Motor.Port.A);
		leftMotor = ev3.getLargeMotor(Motor.Port.B);
		rightMotor = ev3.getLargeMotor(Motor.Port.C);
		
		// get a colour sensor 
		colorSensor = ev3.getColorSensor(Sensor.Port.S1);
		
		// get a distance sensor
		distanceSensor = ev3.getUltrasonicSensor(Sensor.Port.S2);
		
		// get a speaker
		speaker = ev3.getSpeaker();
		
		// Set the initial value of motor speed
		motorSpeed = 60;
		setSpeed(motorSpeed);
	}

	/**
	* This grabs the ball 
	* @author Jamie Huddlestone
	*/
	public void grab() {
		grabMotor.forward();
		sleep(500);
		grabMotor.stop();
	}
	 
	/**
	* This releases the ball 
	* @author Jamie Huddlestone
	*/ 
	public void release() {
		grabMotor.backward();
		sleep(500);
		grabMotor.stop();
	}
	
	/**
	* This moves robot forward
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public void goForward() {
		leftMotor.forward();
		rightMotor.forward();
	}
	
	/**
	* This moves robot backward
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public void goBackward() {
		leftMotor.backward();
		rightMotor.backward();
	}

	/**
	* This turns the robot left and moves it forward
	*  @author Jamie Huddlestone
	*/
	public void turnLeft() {
		rightMotor.forward();
	}

	/**
	* This turns the robot left a set number of degrees
	* @param deg     specifies the number of degrees that robot turns left
	* @author Jamie Huddlestone
	*/
	public void turnLeft(int deg) {
		rightMotor.rotate((int) Math.round(deg * 2 * TURN_RATIO));
	}

	/**
	* This turns the robot right and moves it forward
	*  @author Jamie Huddlestone
	*/
	public void turnRight() {
		leftMotor.forward();
	}
	
	/**
	* This turns the robot right a set number of degrees
	* @param deg     specifies the number of degrees that robot turns right
	* @author Jamie Huddlestone
	*/
	public void turnRight(int deg) {
		leftMotor.rotate((int) Math.round(deg * 2 * TURN_RATIO));
	}
	
	/**
	* This stops both motors of robot
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public void stop() {
		leftMotor.stop();
		rightMotor.stop();
	}

	/**
	* @return ColorSensor.Color    the detected colour in enum
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public ColorSensor.Color getColor() {
		return colorSensor.getColor();
	}

	/**
	* This gets a distance from a sensor in meters 
	* @return double    the distance in meters
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public double getDistance() {
		return (double) distanceSensor.getDistance();
	}
	
	/**
	* temporarily suspend robot action
	* @param ms     the number of miliseconds robot sleeps
	* @author Jamie Huddlestone
	*/
	public void sleep(int ms) {
		ev3.sleep(ms);
	}

	/**
	* This disconnects robot
	* @author Jamie Huddlestone
	*/
	public void close() {
		ev3.close();
	}

	/**
	* The robot moves forward until it detects a change in color
	* @author Jamie Huddlestone, Karolina Debowska
	*/
	public ColorSensor.Color scanForward() {
		ColorSensor.Color originalColor = getColor();
		ColorSensor.Color newColor = originalColor;
		goForward();
		do newColor = getColor();
		while (newColor == originalColor);
		stop();
		return newColor;
	}
	
	/**
	* The robot moves forward and left until it detects a change in color
	* @author Jamie Huddlestone, Karolina Debowska
	*/
	public ColorSensor.Color scanLeft() {
		ColorSensor.Color originalColor = getColor();
		ColorSensor.Color newColor = originalColor;
		turnLeft();
		do newColor = getColor();
		while (newColor == originalColor);
		stop();
		return newColor;
	}
	
	/**
	* The robot moves forward and right until it detects a change in color
	* @author Jamie Huddlestone, Karolina Debowska
	*/
	public ColorSensor.Color scanRight() {
		ColorSensor.Color originalColor = getColor();
		ColorSensor.Color newColor = originalColor;
		turnRight();
		do newColor = getColor();
		while (newColor == originalColor);
		stop();
		return newColor;
	}
	
	/**
	* This sets speed of both main motors of robot
	* @param speed     the speed of robot in degrees of motor rotation per second
	* @author Jamie Huddlestone, Karolina Debowska
	*/
	public void setSpeed(int speed) {
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);	
		motorSpeed = speed;
	}
	
	/**
	* Performs a piece of music
	* @author Jamie Huddlestone
	*/
	public void sing() {
		playMusic("G;A;B;c;d;c;B;A;G", 100);
	}
	
	/** Converts a semicolon-delimited string of note names / relative note duration into frequencies / absolute duration,
	* e.g. "pitch[.duration];pitch[.duration]"
	* @author Jamie Huddlestone
	* @param music	String representing score into individual notes, delimited by semicolons
	* @param tempo	Default tempo (modified by duration multiplier on a note-by-note basis)
	*/
	
	private void playMusic(String music, double tempo) {
		
		// Split string representing score into individual notes, delimited by semicolons
		String[] score = music.split(";");
		
		// For each note name, determine pitch and duration
		int scoreLength = score.length;
		for (int n=0; n < scoreLength; n++) {
			
			// Split note by delimiter as "pitch.duration"
			String[] note = score[n].split("-");

			// Determine pitch
			int frequency = 0;
			switch (note[0]) {
				case "G,":	frequency = 196;	break;
				case "#G,":	frequency = 208;	break;
				case "A,":	frequency = 220;	break;
				case "#A,":	frequency = 233;	break;
				case "B,":	frequency = 247;	break;
				case "C":	frequency = 262;	break;
				case "#C":	frequency = 277;	break;
				case "D":	frequency = 294;	break;
				case "#D":	frequency = 311;	break;
				case "E":	frequency = 330;	break;
				case "F":	frequency = 349;	break;
				case "#F":	frequency = 370;	break;
				case "G":	frequency = 392;	break;
				case "#G":	frequency = 415;	break;
				case "A":	frequency = 440;	break;
				case "#A":	frequency = 466;	break;
				case "B":	frequency = 494;	break;
				case "c":	frequency = 523;	break;
				case "#c":	frequency = 554;	break;
				case "d":	frequency = 587;	break;
				case "#d":	frequency = 622;	break;
				case "e":	frequency = 659;	break;
				case "f":	frequency = 698;	break;
				case "#f":	frequency = 740;	break;
				case "g":	frequency = 784;	break;
				case "#g":	frequency = 831;	break;
				case "a":	frequency = 880;	break;
				case "#a":	frequency = 932;	break;
				case "b":	frequency = 988;	break;
				case "c'":	frequency = 1047;	break;
				case "#c'":	frequency = 1109;	break;
				case "d'":	frequency = 1175;	break;
				case "#d'":	frequency = 1245;	break;
				case "e'":	frequency = 1319;	break;
			}
			
			// Play tone depending on presence of duration multiplier
			if (note.length > 1) {
				double duration = Double.valueOf(note[1]);
				speaker.playTone(frequency, (int) Math.round(tempo*duration));
			}
			else
				speaker.playTone(frequency, (int) Math.round(tempo));
		}
	}

	/**
	* Robot performs a dance 
	*@author JIAHUI LYU, Karolina Debowska
	*/
	public void dance(){
		// increase speed for dance 
		setSpeed(200);
		for (int i=0;i<2;i++){
			turnLeft(360);
			turnRight(360);
		}
	}
	
	/**
	* test harness
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public static void main(String[] args) {
		
		// Create a new instance of class representing a robot
		Ev3 robot = new Ev3("dia-lego-e2");
		
		robot.sing();
		/*
		while (true) {
			System.out.println(robot.getColor());
		}
		*/
		robot.close();
	}
}