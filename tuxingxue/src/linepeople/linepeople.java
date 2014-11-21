package linepeople;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.*;

public class linepeople extends Applet implements MouseListener 
{
	private int tableWidth=400;
	private int tableHeight=400;
	private int ballRadius=30;
	private int ballX=100;
	private int ballY=200;
	private int mouseX=400;
	private int mouseY=200;

	private int attenuator=1;
	private int maxStrength=100;

	private int dx=1;
	private int dy=1;
		int timer=0;


	public linepeople(){
	
	}

public void init(){
		this.addMouseListener(this);

}
	public void paint(Graphics gg){
		Graphics2D g = (Graphics2D)gg;
		//lineBresenham(300,300,0,0);
		//g.clearRect(0,0,800,600);
		drawTable(g);
		peopleMove_H();
}

	private void drawTable(Graphics2D g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,tableWidth,tableHeight);
	}

	private void drawPeople()
	{
		Graphics g=this.getGraphics();
		g.clearRect(ballX-ballRadius*2,ballY-ballRadius*2,ballRadius*2,ballRadius*2);
		g.clearRect(0,0,tableWidth,tableHeight);
		setBackground(Color.black);
		g.setColor(Color.red);
		//g.fillOval(100,100,20,20);
		g.drawOval(ballX-ballRadius,ballY-ballRadius,ballRadius,ballRadius);
		

		for(int i=-1;i<=1;i++)
		g.drawLine(ballX+i-ballRadius/2,ballY,ballX+i-ballRadius/2,ballY+ballRadius);
			int j=timer%20-10;
		g.drawLine(ballX-ballRadius/2,ballY+ballRadius/3,ballX-j*3-ballRadius/2,ballY+ballRadius);
		g.drawLine(ballX-ballRadius/2,ballY+ballRadius/3,ballX+j*3-ballRadius/2,ballY+ballRadius);
		
		g.drawLine(ballX-ballRadius/2,ballY+ballRadius,ballX-j-ballRadius/2,ballY+ballRadius*2);
		g.drawLine(ballX-ballRadius/2,ballY+ballRadius,ballX+j-ballRadius/2,ballY+ballRadius*2);

	}

	private void peopleMove_H()
	{
		do{
		if(ballX+ballRadius>=tableWidth )
		{
			dx=-1;
		}
		if(ballX-ballRadius<=0){
			dx=1;
		}
		ballX=ballX+2*dx;
		drawPeople();

		try{
		Thread.sleep(30);
		timer++;
		}catch(InterruptedException e){}

		}while(true );
	}


	/////////////mosue listener ///////
public void mouseClicked(MouseEvent e){
	
}
public void mousePressed(MouseEvent e){
//	mouseX=e.getX();
//	mouseY=e.getY();

//	strength++;
//	try{
//		Thread.sleep(30);
//	}catch(Exception ee){}
}
public void mouseReleased(MouseEvent e){
	mouseX=e.getX();
	mouseY=e.getY();
}
public void mouseEntered(MouseEvent e){
}
public void mouseExited(MouseEvent e){
}



}
