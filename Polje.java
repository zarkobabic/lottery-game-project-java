package domaciZadatak2ZarkoBabic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {
	public static final int SLOBODNO = 0;
	public static final int IZABRANO = 1;
	private Mreza owner;
	private int broj;
	private int status = SLOBODNO;

	
	
	
	
	
	
	public Polje(Mreza owner, int im) {
		this.owner = owner;
		this.broj = im;
		this.setPreferredSize(new Dimension(75,75));
		this.setBackground(Color.ORANGE);
		this.addMouseListener(new MouseAdapter(){
																							//MOUSE CLICKED LISTENER
			@Override
			public void mouseClicked(MouseEvent e) {
				if(status == IZABRANO) {status = SLOBODNO;}
				else status = IZABRANO;
				owner.azuriraj(Polje.this);
				repaint();
			}
		});
		
		
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		if(status == IZABRANO) {
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.WHITE);
		}else {
			g.setColor(Color.BLACK);
		}
		
		
		if(getHeight() > getWidth()) {
			g.setFont(new Font("Lucida",Font.BOLD, getWidth()/3));
			FontMetrics fm = g.getFontMetrics();
			g.drawString(((Integer)broj).toString(), (getWidth()- fm.stringWidth(((Integer)broj).toString()))/2, (getHeight()-fm.getHeight())/2 + fm.getAscent());
		}
		else {
			g.setFont(new Font("Lucida",Font.BOLD, getHeight()/3));
			FontMetrics fm = g.getFontMetrics();
			g.drawString(((Integer)broj).toString(), (getWidth()- fm.stringWidth(((Integer)broj).toString()))/2, (getHeight()-fm.getHeight())/2 + fm.getAscent());
		}
	}
	
	
	
	public int getBroj() {
		return broj;
	}
	

	public int getStatus() {
		return status;
	}

	
	
	public void setStatus(int status) {
		this.status = status;
	}
}
