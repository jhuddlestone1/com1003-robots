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
		
		// Set conditions to make robot perform 
		boolean run = true;
		boolean outward = true;
		
		// The robot moves forward until it finds a black line
		robot.scanForward();
		
		// Once the robot find a line, it turns left 90 degrees
		robot.turnLeft(90);
		
		// The movement algorithm
		while (run) {
			
			// Detect current colour and perform actions based on results
			switch (robot.getColor()) {
				
				case NONE:
					// Stop if no colour detected, eg. robot lifted off ground
					robot.stop();
				
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
					// Switch to homeward leg of journey
					outward = false;
					// Robot heads back towards the line
					robot.turnLeft(180);
					robot.scanForward();
					break;

				case BLACK:
					// When robot detects black, it moves forward and left (towards white area)
					if (outward)
						robot.scanLeft();
					else
						robot.scanRight();
					break;
					
				case WHITE:
					// When robot detects white, it moves forward and right (towards black line)
					if (outward)
						robot.scanRight();
					else
						robot.scanLeft();
					break;
					
				default:
					// Scan forward if unsure what colour
					robot.scanForward();
			}
		}
		// Close the robot and clean up all the connections to ports.
		robot.close();
	}
}