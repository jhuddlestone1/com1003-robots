/** Adds methods for detecting the ping-pong ball to an instance of OurRobot.
@author		Jamie Huddlestone
*/
public class Grab extends MyColorSensor {
	
	// This is temporary... should update .goForward() to accept params in metres.
	int nudge = 2;	// 0.02 m ?
	
	UltrasonicSensor distanceSensor = robot.getUltrasonicSensor(Sensor.Port.B);
	Motor grabMotor = robot.getLargeMotor(Motor.Port.A);
	
	/** Tests proximity to object and lowers grab arm if close.
	@return boolean proximity triggered; grab arm lowered
	*/
	public boolean grabIfClose() {
		
		float distance = distanceSensor.getDistance();
		
		if (distance < 0.05) {
			goForward(nudge);
			grabMotor.forward();
			while(!grabMotor.isStalled());
			grabMotor.stop();
			return true;
		}
		
		return false;
	}
	
}