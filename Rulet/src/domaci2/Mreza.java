package domaci2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;

import domaci2.Polje.Status;

import java.awt.*;


public class Mreza extends Panel {
	
	//Polja
	private int red=4;
	private int kolona=5;
	private ArrayList<Polje> polja = new ArrayList<>();
	private HashSet<Integer> set = new HashSet<>();
	private Igra vlasnik;
	
	//Geteri Svih polja
	public int getRed() {
		return red;
	}

	public int getKolona() {
		return kolona;
	}

	public Igra getVlasnik() {
		return vlasnik;
	}

	public HashSet<Integer> getSet() {
		return set;
	}

	public ArrayList<Polje> getPolja() {
		return polja;
	}
	

	public Mreza(int r,int k,Igra owner) {
		this.vlasnik=owner;
		this.red=r;
		this.kolona=k;
		this.setBackground(Color.BLACK);
		this.setLayout(new GridLayout(r,k, 3, 3));	
		this.polja = new ArrayList<>();
		
		//Dodavanje Polja u Panel Mreza
		int pocetak=0;
		for(int i=0;i<red;i++) {
			for(int j=0;j<kolona;j++) {
				Polje p=new Polje(this,pocetak);
				this.add(p);
				pocetak++;
			}
		}
		
		//setVisible(true);
	}
	
	public static void main(String args[]) {
		
	}
	
	
}
