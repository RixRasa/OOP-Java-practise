package domaci3;

import java.awt.*;

	
public class Tastatura extends Panel implements Runnable {
	Panel southPanel;
	Panel centarPanel;
	
	Label labelaImena;
	Label labelaBroja;
	
	CardLayout cardLayout = new CardLayout();
	Thread thread;
	
	private int STEP=0;
	private String s="";
	private String s1="";

	public Tastatura() {
		this.setLayout(new BorderLayout());
		
		//Setovanje panela gde se nalaze ime i broj koji unosimo
		labelaImena= new Label("");
		labelaBroja= new Label("");
		centarPanel = new Panel(new GridLayout(0,1,0,0));
		centarPanel.add(labelaBroja);
		centarPanel.add(labelaImena);
		this.add(centarPanel,BorderLayout.CENTER);
		
		//Setovanje card panela gde ce biti svi dumici
		southPanel= new Panel( cardLayout);
		//Prvih 12
		Panel Stanje1=new Panel(new GridLayout(4, 3, 2, 2));
		Button dugme1= new Button("1"); Stanje1.add(dugme1);
		Button dugme2= new Button("2"); Stanje1.add(dugme2);
		Button dugme3= new Button("3"); Stanje1.add(dugme3);
		Button dugme4= new Button("4"); Stanje1.add(dugme4);
		Button dugme5= new Button("5"); Stanje1.add(dugme5);
		Button dugme6= new Button("6"); Stanje1.add(dugme6);
		Button dugme7= new Button("7"); Stanje1.add(dugme7);
		Button dugme8= new Button("8"); Stanje1.add(dugme8);
		Button dugme9= new Button("9"); Stanje1.add(dugme9);
		Button dugme10= new Button("*"); Stanje1.add(dugme10);
		Button dugme11= new Button("0"); Stanje1.add(dugme11);
		Button dugme12= new Button("+"); Stanje1.add(dugme12);
		//Drugih 12
		Panel Stanje2=new Panel(new GridLayout(4, 3, 2, 2));
		Button dugmE1= new Button(" "); Stanje2.add(dugmE1);
		Button dugmE2= new Button("ABC"); Stanje2.add(dugmE2);
		Button dugmE3= new Button("DEF"); Stanje2.add(dugmE3);
		Button dugmE4= new Button("GHI"); Stanje2.add(dugmE4);
		Button dugmE5= new Button("JKL"); Stanje2.add(dugmE5);
		Button dugmE6= new Button("MNO"); Stanje2.add(dugmE6);
		Button dugmE7= new Button("PQRS"); Stanje2.add(dugmE7);
		Button dugmE8= new Button("TUV"); Stanje2.add(dugmE8);
		Button dugmE9= new Button("WXYZ"); Stanje2.add(dugmE9);
		Button dugmE10= new Button(" "); Stanje2.add(dugmE10);
		Button dugmE11= new Button("_"); Stanje2.add(dugmE11);
		Button dugmE12= new Button(""); Stanje2.add(dugmE12);
		
		southPanel.add(Stanje1);
		southPanel.add(Stanje2);
		this.add(southPanel,BorderLayout.SOUTH);
		
		dugmE2.addActionListener((ae) -> {MenjajLabelu(dugmE2);});
		dugmE3.addActionListener((ae) -> {MenjajLabelu(dugmE3);});
		dugmE4.addActionListener((ae) -> {MenjajLabelu(dugmE4);});
		dugmE5.addActionListener((ae) -> {MenjajLabelu(dugmE5);});
		dugmE6.addActionListener((ae) -> {MenjajLabelu(dugmE6);});
		dugmE7.addActionListener((ae) -> {MenjajLabelu2(dugmE7);});
		dugmE8.addActionListener((ae) -> {MenjajLabelu(dugmE8);});
		dugmE9.addActionListener((ae) -> {MenjajLabelu2(dugmE9);});
		dugmE11.addActionListener((ae) -> {
			String s1=labelaImena.getText();
			labelaImena.setText(s1+" ");
			s=labelaImena.getText();
		});
		
		dugme1.addActionListener((ae) -> {MenjajLabelu1(dugme1);});
		dugme2.addActionListener((ae) -> {MenjajLabelu1(dugme2);});
		dugme3.addActionListener((ae) -> {MenjajLabelu1(dugme3);});
		dugme4.addActionListener((ae) -> {MenjajLabelu1(dugme4);});
		dugme5.addActionListener((ae) -> {MenjajLabelu1(dugme5);});
		dugme6.addActionListener((ae) -> {MenjajLabelu1(dugme6);});
		dugme7.addActionListener((ae) -> {MenjajLabelu1(dugme7);});
		dugme8.addActionListener((ae) -> {MenjajLabelu1(dugme8);});
		dugme9.addActionListener((ae) -> {MenjajLabelu1(dugme9);});
		dugme10.addActionListener((ae) -> {MenjajLabelu1(dugme10);});
		dugme11.addActionListener((ae) -> {MenjajLabelu1(dugme11);});
		dugme12.addActionListener((ae) -> {MenjajLabelu1(dugme12);});
		
	}
	
	public void MenjajLabelu1(Button b) {
		String s=b.getActionCommand().substring(0, 1);
		String s1=labelaBroja.getText();
		labelaBroja.setText(s1+s);
		
	}
	
	public void MenjajLabelu(Button b) {
		
		if(!(s1.equals(b.getActionCommand().substring(0, 1)) || s1.equals(b.getActionCommand().substring(1, 2)) || s1.equals(b.getActionCommand().substring(2, 3)))) {
			s=(labelaImena.getText()); STEP=0;
			}
		//if(thread==null) {s=(labelaImena.getText());}
		if(STEP==0) {
			if(thread!=null) {
				thread.interrupt();
				try {thread.join();} catch (InterruptedException e) {}
			}
			s1=b.getActionCommand().substring(0, 1);
			labelaImena.setText(s+b.getActionCommand().substring(0, 1)); 
			STEP++; 
			thread = new Thread(this);
			thread.start();
		}
		else if(STEP==1) {
			s1=b.getActionCommand().substring(1, 2);
			labelaImena.setText(s+b.getActionCommand().substring(1, 2)); 
			STEP++;
			thread.interrupt();
			try {thread.join();} catch (InterruptedException e) {}
			thread = new Thread(this);
			thread.start();
			}
		else if(STEP==2) {
			s1=b.getActionCommand().substring(2, 3);
			labelaImena.setText(s+b.getActionCommand().substring(2, 3)); 
			STEP=0;
			thread.interrupt();
			try {thread.join();} catch (InterruptedException e) {}
			thread = new Thread(this);
			thread.start();
		}
		
	}
	
public void MenjajLabelu2(Button b) {
		
		if(!(s1.equals(b.getActionCommand().substring(0, 1)) || s1.equals(b.getActionCommand().substring(1, 2)) || s1.equals(b.getActionCommand().substring(2, 3)))) {
			s=(labelaImena.getText()); STEP=0;
			}
		//if(thread==null) {s=(labelaImena.getText());}
		if(STEP==0) {
			if(thread!=null) {
				thread.interrupt();
				try {thread.join();} catch (InterruptedException e) {}
			}
			s1=b.getActionCommand().substring(0, 1);
			labelaImena.setText(s+b.getActionCommand().substring(0, 1)); 
			STEP++; 
			thread = new Thread(this);
			thread.start();
		}
		else if(STEP==1) {
			s1=b.getActionCommand().substring(1, 2);
			labelaImena.setText(s+b.getActionCommand().substring(1, 2)); 
			STEP++;
			thread.interrupt();
			try {thread.join();} catch (InterruptedException e) {}
			thread = new Thread(this);
			thread.start();
			}
		else if(STEP==2) {
			s1=b.getActionCommand().substring(2, 3);
			labelaImena.setText(s+b.getActionCommand().substring(2, 3)); 
			STEP++;
			thread.interrupt();
			try {thread.join();} catch (InterruptedException e) {}
			thread = new Thread(this);
			thread.start();
		}
		else if(STEP==3) {
			s1=b.getActionCommand().substring(2, 3);
			labelaImena.setText(s+b.getActionCommand().substring(3, 4)); 
			STEP=0;
			thread.interrupt();
			try {thread.join();} catch (InterruptedException e) {}
			thread = new Thread(this);
			thread.start();
		}
		
	}
	
	
	

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(1000);
				s=(labelaImena.getText());
				STEP=0;
			} 
		}catch (InterruptedException e) {
		}
	}
}
