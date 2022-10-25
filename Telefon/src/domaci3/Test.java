package domaci3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test extends Frame {

	Telefon t1;
	Telefon t2;
	public Test(Telefon t1,Telefon t2){
		this.t1=t1;
		this.t2=t2;
		setBounds(700, 200, 500, 500);
		
		setTitle("Telefon");
		
		add(t1,BorderLayout.WEST);
		add(t2,BorderLayout.EAST);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
			}
		});
		//pack();
		setVisible(true);
	}
	public static void main(String args[]) {
		Broj b1= new Broj("+3816928181");
		Telefon t1= new Telefon(b1,Color.GREEN);
		Broj b2= new Broj("+3816928282");
		Telefon t2= new Telefon(b2,Color.ORANGE);
		new Test(t1,t2);

		

	}

}
