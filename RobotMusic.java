/** Music for OurRobot.
@author		Jamie Huddlestone
*/

import ShefRobot.*;

public class RobotMusic {
	
	public static void playMusic(String music, double tempo, Speaker speaker) {
		String[] score = music.split(";");
		int scoreLength = score.length;
		for (int n=0; n < scoreLength; n++) {
			String[] note = score[n].split(",");
			int frequency = 0;
			switch (note[0]) {
				case "G,":	frequency = 196;	break;
				case "#G,":	frequency = 208;	break;
				case "A,":	frequency = 220;	break;
				case "#A,":	frequency = 233;	break;
				case "B,":	frequency = 247;	break;
				case "C":	frequency = 262;	break;
				case "#C":	frequency = 277;	break;
				case "D":	frequency = 294;	break;
				case "#D":	frequency = 311;	break;
				case "E":	frequency = 330;	break;
				case "F":	frequency = 349;	break;
				case "#F":	frequency = 370;	break;
				case "G":	frequency = 392;	break;
				case "#G":	frequency = 415;	break;
				case "A":	frequency = 440;	break;
				case "#A":	frequency = 466;	break;
				case "B":	frequency = 494;	break;
				case "c":	frequency = 523;	break;
				case "#c":	frequency = 554;	break;
				case "d":	frequency = 587;	break;
				case "#d":	frequency = 622;	break;
				case "e":	frequency = 659;	break;
				case "f":	frequency = 698;	break;
				case "#f":	frequency = 740;	break;
				case "g":	frequency = 784;	break;
				case "#g":	frequency = 831;	break;
				case "a":	frequency = 880;	break;
				case "#a":	frequency = 932;	break;
				case "b":	frequency = 988;	break;
				case "c'":	frequency = 1047;	break;
				case "#c'":	frequency = 1109;	break;
				case "d'":	frequency = 1175;	break;
				case "#d'":	frequency = 1245;	break;
				case "e'":	frequency = 1319;	break;
			}
			if (note.length > 0) {
				double duration = Double.valueOf(note[1]);
				speaker.playTone(frequency, (int) Math.round(tempo*duration));
			}
			else
			{
				speaker.playTone(frequency, (int) Math.round(tempo));
			}
		}
	}
	
	public static void main(String[] args) {
	
		// Robot setup
		Robot robot = new Robot("dia-lego-e2");
		Speaker speaker = robot.getSpeaker();
		
		playMusic("G;A;B;c;d;c;B;A;G", 200, speaker);
		
		robot.close();
		
	}
}