import ShefRobot.*;

public class OurRobot {
	
	public ShefRobot.Robot robot;
	private ShefRobot.Motor leftMotor;
	private ShefRobot.Motor rightMotor;
	private ShefRobot.Speaker speaker;
	
	public OurRobot() {	
		// Robot setup
		robot = new ShefRobot.Robot("dia-lego-e2");
		
		leftMotor = robot.getLargeMotor(Motor.Port.B);
		rightMotor = robot.getLargeMotor(Motor.Port.C);
		
		speaker = robot.getSpeaker();
	}
	
	public void forward(int duration) {
		
		leftMotor.forward();
		rightMotor.forward();
		robot.sleep(duration);
		leftMotor.stop();
		rightMotor.stop();
	}	
	
	public void backward(int duration) {
		
		leftMotor.backward();
		rightMotor.backward();
		robot.sleep(duration);
		leftMotor.stop();
		rightMotor.stop();
	}	
	
	public void left(int duration) {
		
		leftMotor.backward();
		rightMotor.forward();
		robot.sleep(duration);
		leftMotor.stop();
		rightMotor.stop();
	}	
	
	public void right(int duration) {
		
		leftMotor.forward();
		rightMotor.backward();
		robot.sleep(duration);
		leftMotor.stop();
		rightMotor.stop();
	}
	
}