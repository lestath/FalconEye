import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainApp extends JFrame implements ActionListener{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graph GRAPH;
	private JButton STOP_BTN;
	private JButton GO_BTN;
	private JPanel BTN_PANEL;
	

public MainApp(){
	  super("Falcon Eye");
	  
	  this.GRAPH = new Graph();
	  this.setLayout(new FlowLayout(FlowLayout.LEADING));
	  this.setSize(new Dimension(410,350));
	  this.setLocationRelativeTo(null);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	  

	  
	  this.STOP_BTN = new JButton("STOP");
	  this.STOP_BTN.setPreferredSize(new Dimension(90,24));
	  this.STOP_BTN.addActionListener(this);
	  this.STOP_BTN.setFocusPainted(false);
	  
	  this.GO_BTN = new JButton("GO");
	  this.GO_BTN.setPreferredSize(new Dimension(90,24));
	  this.GO_BTN.addActionListener(this);
	  this.GO_BTN.setFocusable(false);
	  
	  this.BTN_PANEL = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  this.BTN_PANEL.setPreferredSize(new Dimension(95,300));
	  this.BTN_PANEL.add(this.STOP_BTN);
	  this.BTN_PANEL.add(this.GO_BTN);
	  
	  
	  
	  this.add(this.GRAPH);
	  this.add(this.BTN_PANEL);
	  this.setVisible(true);
	  new Thread(this.GRAPH).start();
  }

@Override
public void actionPerformed(ActionEvent arg0) {
	Object src = arg0.getSource();
	if(src == this.STOP_BTN){
		this.GRAPH.sto();
	}else if(src == this.GO_BTN){
		this.GRAPH.g();
	}
}
  

}
