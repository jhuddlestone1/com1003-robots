/**	
* COM1003 Assignment 3
* Assessment date: 18/01/2019
* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
*/

public class Assignment3 {	
	/**	
	* Main function
	* @author Jamie Huddlestone, JIAHUI LYU, Karolina Debowska
	*/
	public static void main(String[] args) {
		
		// Create a new instance of class representing a robot
		Ev3 robot = new Ev3("dia-lego-e2");
		
		// Set condition to make robot perform 
		boolean run = true;
		
		// The robot moves forward until it finds a black line
		robot.scanForward();
		
		// Once the robot find a line, it turns left 90 degrees
		robot.turnLeft(90);
		
		// The movement algorithm
		while (run) {
			
			// Detect current colour and perform actions based on results
			switch (robot.getColor()) {
				
				case YELLOW:
					robot.stop();
					// Robot releases the ball and dances
					robot.release();
					robot.dance();
					// Stop main algorithm move
					run = false;
					break;

				case RED:
					robot.goForward();
					// Keep going forward until distance sensor detects object less than 5cm away
					while (robot.getDistance() > 0.05);
					robot.stop();
					// Robot grabs the ball
					robot.grab();
					robot.sing();
					robot.turnRight(180);
					// Robot heads back towards the line
					robot.scanForward();
					break;

				case WHITE:
					// When robot detects white, it moves forward and right (towards black line)
					robot.scanRight();
					break;

				case BLACK:
				default:
					// When robot detects black, it moves forward and left (towards white area)
					robot.scanLeft();
					break;
			}
		}
		// Close the robot and clean up all the connections to ports.
		robot.close();
	}
}