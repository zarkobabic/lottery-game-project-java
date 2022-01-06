package domaciZadatak2ZarkoBabic;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Mreza extends Panel{
	
	private ArrayList<Polje> nizIzabranih = new ArrayList<>();
	private int col;
	private int row;
	private Igra owner;
	private List<List<Polje>> matrix;
	
	
	
	
	
	

	public Mreza(Igra ig) {
		this(4,5,ig);
	}
	
	
	
	public Mreza(int roww, int coll, Igra ig) {
		col = coll;
		row = roww;
		owner = ig;
		this.setBackground(Color.BLACK);
		setLayout(new GridLayout(0, col, 3, 3));
		

		matrix = new ArrayList<List<Polje>>();
		for(int i = 0; i < row; i++) {
			matrix.add(new ArrayList<Polje>()); //pravimo redove koliko ima row, kolone ce dinamicki
		}
		
		int cntNumbers = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Polje pom = new Polje(this,cntNumbers++);
				matrix.get(row-1).add(pom);
				add(pom);
			}
		}	
	}
	
	
	
	public int getUkupanbrojPolja() {
		return col*row;
	}
	
	
	
	public ArrayList<Polje> getNizIzabranih() {
		return nizIzabranih;
	}
	

	
	public HashSet<Integer> dohvatiHashSet() {
		HashSet<Integer> skupIzabranih = new HashSet<>();
		for(Polje i:nizIzabranih) {
			skupIzabranih.add(i.getBroj());
		}
		return skupIzabranih;
	}
	
	
	
	public void azuriraj(Polje p) {
		if(p.getStatus() == Polje.IZABRANO) {
			nizIzabranih.add(p);
		}
		else {
			nizIzabranih.remove(p);
		}
		owner.obavestiIgru();
	}
}
