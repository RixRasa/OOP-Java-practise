package domaci2;
import java.awt.*;

public class Generator {
	
	public Generator() {
		
	}
	
	public int GetRandom(int donja, int gornja) {
		return (int)(Math.random()*(gornja-donja)+donja);
	}

}
