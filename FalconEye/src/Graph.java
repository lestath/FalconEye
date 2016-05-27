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
    	
		this.setPreferredSize(new Dimension(300,600));
		this.setVisible(true);
		this.Running = true;
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		int rgb;
		Graphics2D g2d =(Graphics2D) g;
		Color c;
		boolean[][] faceTable;

				
		faceTable = new boolean[W][H];
		
		for(int x =0;x<W;x++)
			for(int y=0;y<H;y++)
				faceTable[x][y] = false;
		
		IMG = web.getImage();
		IMG = Scalr.resize(IMG,W,H);
		g2d.drawImage(IMG,0, 0,IMG.getWidth(),IMG.getHeight(),this);
		g2d.setColor(Color.red);
		for(int x=0;x<IMG.getWidth();x++){
			for(int y =0; y<IMG.getHeight();y++){ 
					rgb = IMG.getRGB(x, y);
					c = new Color(rgb);
					if((c.getRed() <= 200) && (c.getRed()>=150))
					  if((c.getGreen() <= (c.getRed()*0.81))){
						//  g2d.drawRect(x,y,1,1);
						  faceTable[x][y] = true;
					  }
			}
	
		}
		
		for(int x =0;x<W;x++)
			for(int y=0;y<H;y++)
				if(faceTable[x][y]){
					g2d.drawRect(x,y+H,1,1);
				}else{
					g2d.clearRect(x, y+H,1,1);
				}
		
		int counter = 0;
		for(int x =1;x<(W-1);x++)
			for(int y=1;y<(H-1);y++)
				if(faceTable[x][y]){
					if(faceTable[x-1][y-1]){
						if(faceTable[x+1][y-1]){
							if(faceTable[x-1][y+1]){
								if(faceTable[x-1][y+1]){
									counter = counter +1;
									if(counter > 30){
										g2d.drawString("FACE",x,y);
									}
								}		
							}	
						}
					}
				}else{
					counter = 0;
				}

		
	}
	
	public void run(){
		while(true){
				while(Running){
					this.repaint();
				}
				try {
					Thread.sleep(10);
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
