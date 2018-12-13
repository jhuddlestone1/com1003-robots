

public class Assignment3 {
	
	/**	Main function
	*	@param args: the command line arguments
	*/
	public static void main(String[] args) {
	
		OurRobot ourRobot = new OurRobot();
		
		ourRobot.forward(5000);
		ourRobot.left(100);
		ourRobot.forward(5000);
		
		ourRobot.robot.close();
		
	}
}