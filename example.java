
boolean dirIsLeft = false;

while (true) {

	switch (sensor.getColor()) {
		
		case YELLOW:
			yellowDot();
			break;
			
		case RED:
			redDot();
			break;
		
		case WHITE:
			dirIsLeft ? turnLeft() : turnRight();
			dirIsLeft = !dirIsLeft;
		
		case BLACK:
		default:
			goForward();
		
	}

}