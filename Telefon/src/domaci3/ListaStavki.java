package domaci3;

import java.awt.*;
import java.util.ArrayList;

public class ListaStavki extends Panel {
	//ArrayList<Stavka> Stavke = new ArrayList<>();
	public ListaStavki() {
		this.setLayout(new GridLayout(0, 1, 0, 0));
	}
	
	public void Add(Component b) {
		this.add(b);
	}
	
}
