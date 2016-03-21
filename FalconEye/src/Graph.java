import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;





import java.awt.Image;

import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
public class Graph extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Image IMG;
    private Webcam web;
    private int W,H;
    private boolean Running;
	
    public Graph(){
    	this.W = 300;
    	this.H = 300;
    	web = Webcam.getDefault();
    	web.open();	
		this.setPreferredSize(new Dimension(W,H));
		this.setVisible(true);
		this.Running = true;
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2d =(Graphics2D) g;
		IMG = web.getImage();
		g2d.drawImage(IMG, 0, 0,W,H,this);
	}
	
	public void run(){
		while(true){
				while(Running){
					this.repaint();
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void sto(){
		this.Running = false;
	}
	
	public void g(){
		this.Running = true;
	}
}
