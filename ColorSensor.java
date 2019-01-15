//protected methods, should we use (it is in a sample of code)/ get rid of int count in scan methods, get rid of boolean forward//should we use waitfor function

import ShefRobot.*;

public class ColorSensor{
	//global variables
	private static Robot robot;
    private static Motor leftMotor;
    private static Motor rightMotor;
    private static Speaker speaker;
    private static ColorSensor sensor;
    private static ColorSensor.Color col;
	private static final int WALKING_SPEED=500;
	
	public static void main(String[]args){	
	
		// get a robot object
    	robot = new Robot("dia-lego-..");
    	// get left and right Motor objects
    	leftMotor = robot.getLargeMotor(Motor.Port.A);
    	rightMotor = robot.getLargeMotor(Motor.Port.B);
		
		//set speed
    	leftMotor.setSpeed(WALKING_SPEED);
    	rightMotor.setSpeed(WALKING_SPEED);
    	
    	// get a colour sensor 
    	sensor = robot.getColorSensor(Sensor.Port.S1);
    	
    	// the robot goes forward till black color is detected
    	scanBlack(true,20,ColorSensor.Color.BLACK); // a wrong value 20
		
		//once the robot finds black, it turns left 90 degrees
		turnLeft(90); // a wrong value of 90, adjust it
		
		// go forward till the end of the black path
		scanOffThePath(true,20,ColorSensor.Color.BLACK); // a wrong value 20 
		//once the robot loose the black line, it turns right 90 degrees
		turnRight(90); // a wrong value of 90, adjust it
		//robot keeps track the black line 
		scanOffThePath(true,20,ColorSensor.Color.BLACK); // a wrong value 20 
		//once the robot is out of the path, it is to go back to the centre of the circle 
		blackDot();
		scanRed(true,20,ColorSensor.Color.RED);
    	// Close the robot and clean up all the connections to ports.
    	robot.close();
    }
	
		private static void blackDot(){
			while (col != color){
				//WORK ON MORE ELABORATE METHOD, HOW TO MEASURE THE DISTANCE TO CENTRE
				//OF CIRCLE? MANUAL MEASURE OR ROBOT SHOULD FIGURE OUT THE CENTRE	
				goBackward(20); // a wrong value
				turnRight(5); // a wrong value
				scanOffThePath(true,20,ColorSensor.Color.BLACK);
				col = sensor.getColor();	
			}
		}
		
		 private static void scanRed(boolean forward, int count, ColorSensor.Color color) {
    	//System.out.println("Scanning " + (right ? "right" : "left") + " for " + count);
			for(int i = 0; i < count; i++) {
				if(forward) {
					goForward(5); //a wrong value
				} else {
					goBackward(5); //a wrong value
				}
				col = sensor.getColor();			
				if(col == color) {
					//make a sound
				}
			}
    }
    
    // This method moves the robot forward by count "steps"
    // Technically, the steps are degrees of rotation of the motors
    // but how this translates to actual distances moved will depend
    // on various factors...
    protected static void goForward(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.forward();
    	rightMotor.forward();
    	waitfor(count,false,false);
    }
	
    // This turns the robot to the right by movinf the left
    // motor forward and the right motor backwards.
    // The count value is the number of degrees of rotation 
    // *of the motors*. How this relates to the rotation of the 
    // robot will depend on the size of the wheels, how far
    // apart they are, and various other factors.
    protected static void turnRight(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.forward();
    	rightMotor.backward();
    	waitfor(count,false,true);
    }
	
	
	 protected static void goBackward(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.backward();
    	waitfor(count,false,false);
    }
    
    // This rotates the robot the other way...
    protected static void turnLeft(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.forward();
    	waitfor(count,true,false);
    }
    
    // This is a simple scanning method. It will
    // rotate the robot a short distance either 
    // to the right or "not right" (a.k.a. left!)
    // and check the currently sensed colour.
    // It looks for the specified colour and stops 
    // if it finds it. It will repeat this a 
    // maximum of 'count' times.
    // This method updates the col variable, so 
    // that will be set to the last colour scanned
    // when the method completes.
    private static void scanBlack(boolean forward, int count, ColorSensor.Color color) {
    	//System.out.println("Scanning " + (right ? "right" : "left") + " for " + count);
    	for(int i = 0; i < count; i++) {
    		if(forward) {
				goForward(5); //a wrong value
    		} else {
    			goBackward(5); //a wrong value
    		}
    		col = sensor.getColor();			
    		if(col == color) {
    			break;
    		}
    	}
    }
	private static void scanOffThePath(boolean forward, int count, ColorSensor.Color color) {
    	//System.out.println("Scanning " + (right ? "right" : "left") + " for " + count);
		//change for loops to while loops ! (delete int count)
    	for(int i = 0; i < count; i++) {
    		col = sensor.getColor();			
    		if(col == color) {
				if(forward) {
					goForward(5); //a wrong value
				} else {
					goBackward(5); //a wrong value
				}
			}
			else {
				break;
			}
    	}
    }
    // This is a somewhat complicated method for monitoring the motors and 
    // stopping them once they have rotated far enough.
    private static void waitfor(int count, boolean leftback, boolean rightback) {
    	boolean lf = false; 
    	boolean rf = true;
    	do {
    		int ltc = leftMotor.getTachoCount();
    		int rtc = rightMotor.getTachoCount();
    		if(leftback && (ltc < (0-count))) {
    			leftMotor.stop();
    			lf = true;
    		} else if(ltc > count) {
    			leftMotor.stop();
    			lf = true;
    		}
    		if(rightback && (rtc < (0-count))) {
    			rightMotor.stop();
    			rf = true;
    		} else if(rtc > count) {
    			rightMotor.stop();
    			rf = true;
    		}
    	} while(!(lf && rf));
    }
	
}

	}
}
