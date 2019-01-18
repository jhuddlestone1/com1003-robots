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
		 // a wrong value 20 
			
		//robot keeps track the black line 
		//scanOffThePath(ColorSensor.Color.BLACK); // a wrong value 20 
		
		//once the robot is out of the path, it is to go back to the centre of the circle 
		//blackDot();

		
    	// Close the robot and clean up all the connections to ports.
    	robot.close();
    }
    
    // This method moves the robot forward by count "steps"
    // Technically, the steps are degrees of rotation of the motors
    // but how this translates to actual distances moved will depend
    // on various factors...
    private static void goForward() {
		leftMotor.forward();
		rightMotor.forward();	
    }

	private static void turnRight() {
    	leftMotor.forward();
    	rightMotor.backward();
	}
	 //  This stops the robot
	private static void stop(){
		leftMotor.stop();
		rightMotor.stop();
	}
	 //  This method moves the robot backward by count "steps"
	 private static void goBackward() {
    	leftMotor.backward();
    	rightMotor.backward();
    }
    
	 private static void turnLeft() {
    	leftMotor.backward();
    	rightMotor.forward();
    }

	//This makes robot move along the black line
	private static void scan() {
		while (true){		
    		if(sensor.getColor() == ColorSensor.Color.BLACK) {
				goForward();
				turnLeft();
			}
			else if (sensor.getColor() == ColorSensor.Color.RED){
				break;
			}
			else if (sensor.getColor() == ColorSensor.Color.YELLOW){
				break;
			}
			else {
				while (sensor.getColor() != ColorSensor.Color.BLACK){
					//works when robot is on the right side from the line
					goForward();
					turnRight();
				}
			} 
    	}
    }
}