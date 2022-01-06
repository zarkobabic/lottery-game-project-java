package domaciZadatak2ZarkoBabic;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Igra extends Frame{
	private Mreza mreza = new Mreza(this);
	private Button dugme = new Button("Igraj");
	private Label balans = new Label("0");
	private TextField ulog = new TextField("100",4);
	private Label kvota = new Label("");
	private Label dobitak = new Label("");
	private Label izvuceniBr = new Label("");
	private Panel statusnaTraka = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	private double bal = 0.0, dob = 0.0, kv = 0.0, ulg = 100;
	
	
	
	
	public Igra() {
		setBounds(400, 400, 525, 325);
		setTitle("Igra");
		setVisible(true);
		setResizable(true);
		populateWindow();
		pack();
		
																								//WINDOW CLOSING LISTENER
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				super.windowClosing(e);
			}
		});
		
																								//TEXT LISTENER ZA ULOG
		
		ulog.addTextListener(e->{
			if(ulog.getText().length() != 0) {
				ulg = Double.parseDouble(ulog.getText());
				dob = kv*ulg;
			}
			else {
				dob = 0.0;
			}
			
			if(mreza.getNizIzabranih().size() != 0) {
				dobitak.setText(String.format("%.2f", dob));
			}
			else {
				kvota.setText("");
				dobitak.setText("");
			}
			revalidate();
		});
		
																								//LISTENER ZA MOUSE CLICKED NA IGRAJ DUGME
		
		
		dugme.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				Integer slucbroj = (new Generator()).generisiSlucajniBr(0, mreza.getUkupanbrojPolja()-1);
				izvuceniBr.setText((slucbroj).toString());
				if(mreza.dohvatiHashSet().contains(slucbroj)) {
					statusnaTraka.setBackground(Color.GREEN);
					izvuceniBr.setBackground(Color.GREEN);
					bal += dob;
					//bal -= ulg; receno da ce biti promenjeno u postavci da se oduzima ulog uvek
				}
				else{
					statusnaTraka.setBackground(Color.RED);
					izvuceniBr.setBackground(Color.RED);
					bal -= ulg;	
				}
				balans.setText(String.format("%.2f", bal));
				izvuceniBr.revalidate();
			}
		});
	}
	
	
	
	private void populateWindow() {
		Panel zaUpravljanje = new Panel(new GridLayout(0,1,0,0));
		Panel panelbalans = new Panel(new FlowLayout(FlowLayout.LEFT, 5,15));
		Panel panelulog = new Panel(new FlowLayout(FlowLayout.LEFT, 5,15));
		Panel panelkvota = new Panel(new FlowLayout(FlowLayout.LEFT, 5,15));
		Panel paneldobitak = new Panel(new FlowLayout(FlowLayout.LEFT, 5,15));
		Panel paneldugme = new Panel(new FlowLayout(FlowLayout.RIGHT, 5,5));
		
		panelbalans.add(new Label("Balans:"));
		panelbalans.add(balans);
		
		panelulog.add(new Label("Ulog:"));
		panelulog.add(ulog);
		
		panelkvota.add(	new Label("Kvota:"));
		panelkvota.add(kvota);
		
		paneldobitak.add(new Label("Dobitak:"));
		paneldobitak.add(dobitak);
		
		paneldugme.add(dugme);
		dugme.setEnabled(false);
		
		statusnaTraka.setPreferredSize(new Dimension(525,25));
		statusnaTraka.add(izvuceniBr);
		
		statusnaTraka.setBackground(Color.GRAY);
		zaUpravljanje.setBackground(Color.LIGHT_GRAY);
		
		zaUpravljanje.add(panelbalans);
		zaUpravljanje.add(panelulog);
		zaUpravljanje.add(panelkvota);
		zaUpravljanje.add(paneldobitak);
		zaUpravljanje.add(paneldugme);
		
		add(mreza, BorderLayout.CENTER);
		add(zaUpravljanje, BorderLayout.EAST);
		add(statusnaTraka, BorderLayout.SOUTH);
	}
	
	
	
	
	public void obavestiIgru() {
		if(mreza.getNizIzabranih().size() > 0) {
			dugme.setEnabled(true);
		}else {
			dugme.setEnabled(false);
		}
		
																								//AZURIRANJE KVOTE I DOBITKA
		
		if(mreza.getNizIzabranih().size() != 0) {
			kv = Math.round((double) mreza.getUkupanbrojPolja()/mreza.getNizIzabranih().size()*100.00)/100.00;
			dob = Math.round (kv*ulg*100.00)/100.0;
			kvota.setText(String.format("%.2f", kv));
			dobitak.setText(String.format("%.2f", dob));
		}
		else {
			kvota.setText("");
			dobitak.setText("");
		}
		kvota.revalidate();
		dobitak.revalidate();
	}
	
	
	
	public Mreza getMreza() {
		return mreza;
	}
	
	
	
	public static void main(String[] args) {
		new Igra();
	}
	
}