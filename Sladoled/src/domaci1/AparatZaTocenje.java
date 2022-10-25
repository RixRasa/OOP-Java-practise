package domaci1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AparatZaTocenje extends Panel {
	
	ArrayList<Ukus> Ukusi= new ArrayList<>();
	
	GridLayout lajout=new GridLayout(1,1,0,0);
	private Panel ukusi= new Panel(lajout);
	
	private MestoZaTocenje Tocilica= new MestoZaTocenje(this);
	Button prodaj=new Button("Prodaj");
	Label sladoled= new Label("*******");
	
	private int x=1; private int y=1;
	
	public AparatZaTocenje(){
		setLayout(new BorderLayout());
		
		//JUZNI PANEL
		Panel juzni=new Panel(new FlowLayout(FlowLayout.LEFT));//Panel za ispis sladoleda
		juzni.setBackground(Color.GRAY);
		
		Label boja=new Label("Sladoled");
		boja.setFont(new Font("Plain",Font.BOLD,20));
		
		juzni.add(boja);
		juzni.add(sladoled);
	
		add(juzni,BorderLayout.SOUTH);//Dodavanje juznog na juzni deo bordera
		
		
		//SREDNJI PANELI
		Panel sredina = new Panel(new BorderLayout());//Panel za dugmad ukusa prodaj i canvas
		
		//Dodavanje Panela sa dugmadima ukusa
		ukusi.setBackground(Color.LIGHT_GRAY);
		ukusi.setPreferredSize(new Dimension(350,350));
		sredina.add(ukusi,BorderLayout.WEST);
		
		//Dodavanje panela sa dugmetom "Prodaj" i "canvasom"
		Panel dugmeImesto=new Panel(new GridLayout(2,1,0,0));
		dugmeImesto.setBackground(Color.WHITE);
		
		dugmeImesto.add(prodaj);
		dugmeImesto.add(Tocilica);
		prodaj.setEnabled(false);
		
		//Dodavanje drugmeImesto na sredinu
		sredina.add(dugmeImesto,BorderLayout.CENTER);
		
		//Dodavanje sredine na glavni panel
		add(sredina,BorderLayout.CENTER);
		
		//ACTIONLISTENER
		prodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graphics g=Tocilica.getGraphics();//Pri prodaji clearujemo canvas
				g.clearRect(0, 0, Tocilica.getWidth(), Tocilica.getHeight());//clearovanje canvasa
				prodaj.setEnabled(false);
				System.out.println((Tocilica.getSladoled().toString()));//ispisujemo labelu sladoled
				Tocilica.setSladoled(new Sladoled(200));//Novi sladoled stavljamo u tocilicu
				
				
			}
		});
	}
	public void DodajUkus(Ukus u) throws GVecPostoji {
		/*DODAJ DEO ZA GRESKUU*/
		for(Ukus ukus:Ukusi) {
			if(ukus.equals(u)) {
				throw new GVecPostoji();
			}
		}
		
		
		//Revalidiranje layout
		if((ukusi.getComponentCount()!=0)&& ukusi.getComponentCount()>=x*y) {
			if(x==y) {x++; lajout.setRows(x);}
			else if(x>y) {y++; lajout.setColumns(y);}
		}
		//Dodavanje dugmeta i Action listener
		Button ukus=new Button(u.getNaziv());
		ukus.setBackground(u.getBoja());
		
		
		//ACTION LISTENERI ZA MISA
		ukus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Object o=e.getSource();
				Button b;
				if(o instanceof Button) {
					b=(Button)o;
					String text=(b.getLabel());
					for(Ukus ukus:Ukusi) {
						if(ukus.getNaziv().equals(text)) {
							Tocilica.Startuj(ukus);
						}
					}
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Tocilica.Prekini();
			}
		});
		
		Ukusi.add(u);//Dodajemo i arraylist ukus
		ukusi.add(ukus);//Dadajemo na panel ukus
		ukusi.revalidate();
	}
}
