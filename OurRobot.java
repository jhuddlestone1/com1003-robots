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
		
		ColorSensor detectColor = robot.getColorSensor(Sensor.Port.S1);
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
	
	public static void whiteSolution() {
		while(detectColor.getColor() == ColorSensor.Color.WHITE) {
			right(500);
			forward(500);
			if (detectColor.getColor() == ColorSensor.Color.WHITE) {
				left(1000);
				forward(500);
			}
			else
		}
	}

    public static void findBlackLine() {
    	while(detectColor.getColor() != ColorSensor.Color.YELLOW) {
    		switch(detectColor.getColor()) {
    			case WHITE: whiteSolution(); break;

    			case BLACK: forward(500); break;

    			case RED: for(int hz=100; hz<900; hz+=100) {
    				speaker.playTone(hz,500);
    			}; break;
    		}
    	}
    }
	
}