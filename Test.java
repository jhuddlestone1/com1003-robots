
public class Test {
	
	private static void goForward() {
		leftMotor.forward();
		rightMotor.forward();	
	}
	
	private static void goBackward() {
		leftMotor.backward();
		rightMotor.backward();
	}
	
	private static void turnLeft() {
		leftMotor.backward();
		rightMotor.forward();
	}
		
	private static void turnRight() {
		leftMotor.forward();
		rightMotor.backward();
	}
	
	private static void stop() {
		leftMotor.stop();
		rightMotor.stop();
	}
	
	public static void main(String[] args) {
	
		// Robot setup
		Robot robot = new Robot("dia-lego-e2");
		Speaker speaker = robot.getSpeaker();
		Motor leftMotor = robot.getLargeMotor(Motor.Port.B);
		Motor rightMotor = robot.getLargeMotor(Motor.Port.C);
		
		boolean dirIsLeft = false;

		while (true) {

			switch (sensor.getColor()) {
				/*
				case YELLOW:
					yellowDot();
					break;
					
				case RED:
					redDot();
					break;
				*/
				case WHITE:
					dirIsLeft ? turnLeft() : turnRight();
					dirIsLeft = !dirIsLeft;
				
				case BLACK:
				default:
					goForward();
				
			}
		}
		
		robot.close();
		
	}
}