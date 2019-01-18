/**	
* COM1003 Assignment 3
* Assessment date: 18/01/2019
* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
*/

import ShefRobot.*;

public class Assignment3 {	
	/**	
	* Main function
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public static void main(String[] args) {
		
		// Create a new instance of class representing a robot
		Ev3 robot = new Ev3("dia-lego-e2");
		
		// Set conditions to make robot perform 
		boolean run = true;
		
		// The robot moves forward until it finds a black line
		robot.scanForward();
		
		// Once the robot find a line, it turns left 90 degrees
		robot.turnLeft(100);
		
		// The movement algorithm
		while (run) {
	
			// Detect current colour and perform actions based on results
			if (robot.getColor() == ColorSensor.Color.BLACK) {
				robot.goForward();
				robot.turnLeft();
			}
				
			else if (robot.getColor() == ColorSensor.Color.YELLOW) {
				robot.stop();
				
				// Robot releases the ball, sings and dances
				robot.release();
				robot.sing();
				robot.dance();
				
				// Stop main algorithm move
				run = false;
			}

			else if (robot.getColor() == ColorSensor.Color.RED) {
				robot.goForward();
				
				// Keep going forward until distance sensor detects object less than 5cm away
				double distance;
				do distance = robot.getDistance();
				while (distance > 0.05 && distance < 1);
				robot.stop();
				
				// Robot grabs the ball
				robot.grab();
				robot.sing();
				
				// Robot heads back towards the line
				robot.turnRight(200);
				robot.scanForward();
			}

			else {
				
				//This works when robot is on the right side from the line
				robot.goForward();
				robot.turnRight();
			}
		}
		
		// Close the robot and clean up all the connections to ports
		robot.close();
	}
}