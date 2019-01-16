// I changed protected methods for private ones, should we use (it is in a sample of code)/should we use a waitfor function
//upload a class to gitlab!
import ShefRobot.*;

public class MyColorSensor {
	//global variables
	 static Robot robot;
     static Motor leftMotor;
     static Motor rightMotor;
     static Speaker speaker;
     static ColorSensor sensor;
     static ColorSensor.Color col;
	 static final int WALKING_SPEED=100;
	 
	public static void main(String[] args){	
	
		// get a robot object
    	robot = new Robot("dia-lego-e2");
		
    	// get left and right Motor objects
    	leftMotor = robot.getLargeMotor(Motor.Port.B);
    	rightMotor = robot.getLargeMotor(Motor.Port.C);
		
		//set speed
    	leftMotor.setSpeed(WALKING_SPEED);
    	rightMotor.setSpeed(WALKING_SPEED);
    	
    	// get a colour sensor 
    	sensor = robot.getColorSensor(Sensor.Port.S1);
    	
    	// the robot goes forward till black color is detected
    	//scanBlack(ColorSensor.Color.BLACK); // a wrong value 20
		
		// go forward till the end of the black path
		scanOffThePath(ColorSensor.Color.BLACK); // a wrong value 20 
			
		//robot keeps track the black line 
		//scanOffThePath(ColorSensor.Color.BLACK); // a wrong value 20 
		
		//once the robot is out of the path, it is to go back to the centre of the circle 
		//blackDot();

		
    	// Close the robot and clean up all the connections to ports.
    	robot.close();
    }
	
	// This is the method that tells how the robot behaves when get black colour from a colour sensor
    private static void scanBlack(ColorSensor.Color color) {
    	while (col != color) {
			goForward(); //a wrong value
    		col = sensor.getColor();			
    		if(col == color) {
    			break;
    		}
    	}
    }
	
	private static void yellowDot(){
		//It releases the ball 
		//It performs a dance
		for (int i=0;i<3;i++){
			turnLeft(5);
			turnRight(5);
		}
	}

	private static void blackDot(){
		while (col != ColorSensor.Color.BLACK){
			goBackward(20); // a wrong value
			turnRight(5); // a wrong value
			scanOffThePath(ColorSensor.Color.BLACK);
			col = sensor.getColor();	
		}
	}
		
	private static void redDot(ColorSensor.Color color) {
		if (col == color){
			//It can recognise the red dot by singing
			//It attempts to pick up its ping pong ball
			//It turns 180 degress
			//It goes back to the yellow dot: 
				//scanOffThePath(ColorSensor.Color.BLACK);
		}
	}
    
    // This method moves the robot forward by count "steps"
    // Technically, the steps are degrees of rotation of the motors
    // but how this translates to actual distances moved will depend
    // on various factors...
    private static void goForward() {
		leftMotor.forward();
		rightMotor.forward();	
    }
	/*private static void goForward() {
		leftMotor.forward();
		rightMotor.forward();
		robot.sleep(duration);
		leftMotor.stop();
		rightMotor.stop();		
    }*/
	
    // This turns the robot to the right by moving the left
    // motor forward and the right motor backwards.
    // The count value is the number of degrees of rotation 
    // *of the motors*. How this relates to the rotation of the 
    // robot will depend on the size of the wheels, how far
    // apart they are, and various other factors.
   /*private static void turnRight() {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.forward();
    	rightMotor.backward();
    }*/
	
	private static void turnRight() {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.forward();
    	rightMotor.backward();
    	waitfor(count,false,true);
	}
	 //  This stops the robot
	private static void stop(){
		leftMotor.stop();
		rightMotor.stop();
	}
	 //  This method moves the robot backward by count "steps"
	 private static void goBackward(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.backward();
    	waitfor(count,false,false);
    }
    
    //  This turns the robot to the left by moving the left
    // motor forward and the right motor backwards.
    /*private static void turnLeft() {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.forward();
    }*/
	
	 private static void turnLeft(int count) {
    	leftMotor.resetTachoCount();
    	rightMotor.resetTachoCount();
    	leftMotor.backward();
    	rightMotor.forward();
    	waitfor(count,true,false);
    }

	
	//This makes robot move along the black line
	private static void scanOffThePath(ColorSensor.Color color) {
		while (true){
    		col = sensor.getColor();			
    		if(col == color) {
				goForward();
				col = sensor.getColor();

			}
			else if (col == ColorSensor.Color.RED){
				stop();
				redDot(ColorSensor.Color.RED);
			}
			else if (col == ColorSensor.Color.YELLOW){
				stop();
				yellowDot();
			}
			else {
				while (sensor.getColor() != color){
					//works when robot is on the right side from the line
					goForward();
					turnRight();
					//turnLeft();
				}
			} 
    	}
		
    	/*while (true){
    		col = sensor.getColor();			
    		if(col == color) {
				turnRight(2);
				goForward();
				col = sensor.getColor();
			}
			else if (col == ColorSensor.Color.RED){
				break;
			}
			else {
				turnLeft(4);
				goForward();
				col = sensor.getColor();
			} 
    	}*/

		/*another version
		   	while (true){
    		col = sensor.getColor();			
    		if(col == color) {
				goForward();
			}
			else if (col == ColorSensor.Color.RED){
				break;
			}
			else {
				rightMotor.stop();
			} 
		}*/

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