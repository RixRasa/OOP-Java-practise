package domaci2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {
	
	enum Status{SLOBODAN, IZABRAN}
	
	private int sirina;
	private int duzina;
	private Mreza owner;
	int broj;
	Status status=Status.SLOBODAN;
	Label labela;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Label getLabela() {
		return labela;
	}

	public void paint(Graphics g) {
		super.paint(g);
		getDim();
		if(status==Status.SLOBODAN) {
			Integer i=broj;
			String text=i.toString();
			Font font=new Font("Arial", Font.BOLD, getFonticile()/3);
			g.setColor(Color.BLACK);
			drawCenteredString(g,text,font);
		}
		else if (status==Status.IZABRAN) {
			Integer i=broj;
			String text=i.toString();
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, sirina, duzina);
			g.setColor(Color.WHITE);
			Font font=new Font("Arial", Font.BOLD, getFonticile()/3);
			drawCenteredString(g,text,font); 

		}
	}
	
	public void drawCenteredString(Graphics g, String text, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
	    
	    int x = 0 + (sirina - metrics.stringWidth(text)) / 2;
	    
	    int y = 0 + ((duzina - metrics.getHeight()) / 2) + metrics.getAscent();
	    
	    g.setFont(font);
	    
	    g.drawString(text, x, y);

	}
	private void getDim() {
		int w = owner.getWidth();
		int h = owner.getHeight();
		sirina = w / owner.getKolona();
		duzina = h / owner.getRed();
	}
	
	private int getFonticile() {
		return Math.max(duzina, sirina);
	}
	
	
	
	public Polje(Mreza owner,int broj) {
		this.owner=owner;
		this.broj=broj;
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(75, 75));
		labela= new Label(((Integer) broj).toString());
		
		
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//Ponovo crtanje svih kruzica
				Polje.this.setStatus(Status.IZABRAN);
				Polje.this.owner.getPolja().add(Polje.this);//U array Slobodnih cela polja
				Polje.this.owner.getSet().add(Polje.this.broj);//U hashset Slobodni int
				repaint();
				
				//Updatovanje onih stvari
				int red=owner.getRed();
				int vrste=owner.getKolona();
				int plavih=owner.getPolja().size();//trenutnih izabranih
				
				double a= red*vrste*1.0/plavih;//dobijanje kvote
				double kvotica = (double) Math.round(a * 100) / 100;//zaokruzivanje kvota
				
				Polje.this.owner.getVlasnik().getKvota().setText(Double. toString(kvotica));//Postavljenje nove kvote
				Polje.this.owner.getVlasnik().getKvota().revalidate();
				
				int ulog=Integer.parseInt(Polje.this.owner.getVlasnik().getUlog().getText());//Uzimamo ulog
				double dobitak=kvotica*ulog;//Racunamo dobitak
				
				Polje.this.owner.getVlasnik().getDobitak().setText(Double. toString(dobitak));//Postavi dobitak
				Polje.this.owner.getVlasnik().getDobitak().revalidate();
			}
		});
	}
}

