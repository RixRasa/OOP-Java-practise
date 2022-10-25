package domaci3;

import java.awt.*;

public class Stavka extends Panel {
	
	Label labelaN= new Label();
	Label labelaT= new Label();
	
	public Stavka(String naslov,String text) {
		this.setLayout(new GridLayout(2, 1, 0, 0));
		labelaN.setText(naslov);
		labelaT.setText(text);
		labelaN.setFont(new Font(Font.DIALOG,Font.BOLD,16));
		this.add(labelaN);
		this.add(labelaT);
		setVisible(true);
	}
	

	public Label getLabelaN() {
		return labelaN;
	}


	public void setLabelaN(Label labela1) {
		this.labelaN = labela1;
	}

	public static void main(String[] args) {
		

	}

}
