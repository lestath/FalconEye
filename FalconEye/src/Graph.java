import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;





import java.awt.Image;
import java.awt.image.BufferedImage;
import java.lang.reflect.Method;

import javax.swing.JPanel;

import org.imgscalr.Scalr;

import com.github.sarxos.webcam.Webcam;
public class Graph extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private BufferedImage IMG;
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
		int rgb;
		Graphics2D g2d =(Graphics2D) g;
		IMG = web.getImage();
		IMG = Scalr.resize(IMG,W,H,null);
		g2d.drawImage(IMG, 0, 0,W,H,this);
		for(int x=0;x<IMG.getWidth();x++){
			for(int y =0; y<IMG.getHeight();y++){
					rgb = IMG.getRGB(x, y);
				//	System.out.println(rgb);
					if(rgb >-16776432 && rgb <-16777186){
						g2d.setColor(Color.red);
						g2d.drawRect(x+10, y+10,10,10);
						
					}
				}
			}
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
