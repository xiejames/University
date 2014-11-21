package tableball;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class  ratateRect extends Applet implements MouseListener,MouseMotionListener,ActionListener
{

	private int type=2;

	private int[] centerX={500,200};
	private int[] centerY={150,300};
	private int   RotateCenterX=150;
	private int   RotateCenterY=300;

	private int mouseX;
	private int mouseY;
	private double scrX;
	private double scrY;

	private double degree=0;
	private double rad;
	private int w=100;
	private int h=100;

	private int n=4;
	private int[][] x0;		//绝对坐标系1-2原值
	private int[][] y0;
	private int[][] x01;		//相对坐标系1-2原值
	private int[][] y01;
	
	private int[][] ex1;
	private int[][] ex2;
	private int[][] ex3;
	private int[][] ex4;
	private int[][] ey1;
	private int[][] ey2;
	private int[][] ey3;
	private int[][] ey4;


	private int[][] ex11;
	private int[][] ex21;
	private int[][] ex31;
	private int[][] ex41;
	private int[][] ey11;
	private int[][] ey21;
	private int[][] ey31;
	private int[][] ey41;


	private int[][] cx;		//绝对坐标系1-2轴
	private int[][] cy;
	private int[][] cx1;		//相对坐标系1-2轴
	private int[][] cy1;

	private int[][] x1;		//变换后的绝对坐标系1-2的值
	private int[][] y1;


	private int Rx=100;
	private int Ry=100;


	Button r;
	Button t;
	Button s1;
	Button s2;
	Button s11;
	Button s111;
	Button s22;	
	Button s222;
	Button ls;

	public void init(){



x0=new int[2][n];		//绝对坐标系1-2原值
y0=new int[2][n];
x01=new int[2][n];		//相对坐标系1-2原值
y01=new int[2][n];		
cx=new int[2][n];		//绝对坐标系1-2轴
cy=new int[2][n];
cx1=new int[2][n];		//相对坐标系1-2轴
cy1=new int[2][n];

x1=new int[2][n];		//变换后的绝对坐标系1-2的值
y1=new int[2][n];



			r=new Button("旋转");
			t=new Button("拉伸");

			s1=new Button("放大");
			s2=new Button("缩小");
			s11=new Button("Y向放大");
			s22=new Button("X向放大");
			s111=new Button("Y向缩小");
			s222=new Button("X向缩小");
			
			ls=new Button("平移");
			//r1=new Button("变形");

			//p=new Button("");
		t.addActionListener(this);
		r.addActionListener(this);
		add(t);
		add(r);
		s1.addActionListener(this);
		s2.addActionListener(this);
		add(s1);
		add(s2);
		s11.addActionListener(this);
		s22.addActionListener(this);
		add(s11);
		add(s22);
		s111.addActionListener(this);
		s222.addActionListener(this);
		add(s111);
		add(s222);
		ls.addActionListener(this);
		add(ls);

parmInit(2);

		rad=degree*3.14159265/180;

		scrX=this.getSize().getWidth();
		scrY=this.getSize().getHeight();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}

	public void parmInit(int type){
ex1=new int[2][h+5];		
ex2=new int[2][w+5];
ex3=new int[2][h+5];
ex4=new int[2][w+5];

ey1=new int[2][h+5];		
ey2=new int[2][w+5];
ey3=new int[2][h+5];
ey4=new int[2][w+5];

ex11=new int[2][h+5];		
ex21=new int[2][w+5];
ex31=new int[2][h+5];
ex41=new int[2][w+5];


ey11=new int[2][h+5];		
ey21=new int[2][w+5];
ey31=new int[2][h+5];
ey41=new int[2][w+5];

for(int i=0;i<2;i++){
		x0[i][0]=x0[i][3]=centerX[i]-w/2;//100
		x0[i][1]=x0[i][2]=centerY[i]+w/2;//300
		y0[i][0]=y0[i][1]=centerX[i]-h/2;//100
		y0[i][2]=y0[i][3]=centerY[i]+h/2;//300

		x01[i][0]=x01[i][3]=-w/2;//100
		x01[i][1]=x01[i][2]=+w/2;//300
		y01[i][0]=y01[i][1]=-h/2;//100
		y01[i][2]=y01[i][3]=+h/2;//300
		for(int j=0;j<h;j++) {
			ex1[i][j]=-w/2;
			ex3[i][j]=w/2;
			ey1[i][j]=-h/2+j;
			ey3[i][j]=-h/2+j;

		}
		for(int j=0;j<w;j++) {
			ey2[i][j]=-h/2;
			ey4[i][j]=h/2;
			ex2[i][j]=-w/2+j;
			ex4[i][j]=-w/2+j;

		}

		cx[i][0]=cx[i][1]=0;
		cy[i][0]=-h*2;
		cy[i][1]=h*2;
		cx[i][2]=-w*2;
		cx[i][3]=w*2;
		cy[i][2]=cy[i][3]=0;
	}	
}

	public void paint(Graphics g){

		matrixRotate(x01[0],y01[0],x1[0],y1[0],n,cx[0],cy[0],cx1[0],cy1[0],centerX[0],centerY[0],rad);
		matrixRotate(x01[1],y01[1],x1[1],y1[1],n,cx[1],cy[1],cx1[1],cy1[1],centerX[1],centerY[1],rad);
		
		matrixRotate(ex1[0],ey1[0],ex11[0],ey11[0],h,centerX[0],centerY[0],rad);
		matrixRotate(ex2[0],ey2[0],ex21[0],ey21[0],w,centerX[0],centerY[0],rad);
		matrixRotate(ex3[0],ey3[0],ex31[0],ey31[0],h,centerX[0],centerY[0],rad);
		matrixRotate(ex4[0],ey4[0],ex41[0],ey41[0],w,centerX[0],centerY[0],rad);
		
		matrixRotate(ex1[1],ey1[1],ex11[1],ey11[1],h,centerX[1],centerY[1],rad);
		matrixRotate(ex2[1],ey2[1],ex21[1],ey21[1],w,centerX[1],centerY[1],rad);
		matrixRotate(ex3[1],ey3[1],ex31[1],ey31[1],h,centerX[1],centerY[1],rad);
		matrixRotate(ex4[1],ey4[1],ex41[1],ey41[1],w,centerX[1],centerY[1],rad);
			
		//int[] ccx={centerX[0]-RotateCenterX,centerX[1]-RotateCenterX};
		//int[] ccy={centerY[0]-RotateCenterY,centerY[1]-RotateCenterY};

//		matrixRotate(ccx,ccy,centerX,centerY,2,cx[1],cy[1],cx1[1],cy1[1],RotateCenterX,RotateCenterY,rad/100);
		
		setBackground(Color.black);
		drawRect(g);
		//drawRound(g);


	}
	public void matrixRotate(int[] x01,int[] y01,int[] x1,int y1[],int n,int[] cx,int[] cy,int[] cx1,int[] cy1,int centerX,int centerY,double rad){

		//边框
		for(int i=0;i<n;i++)
		x1[i]=(int)( x01[i]*Math.cos(rad)-y01[i]*Math.sin(rad)  )+centerX;
		
		for(int i=0;i<n;i++)
		y1[i]=(int)( x01[i]*Math.sin(rad)+y01[i]*Math.cos(rad)  )+centerY;	
		
		//对称轴
		for(int i=0;i<n;i++)
		cx1[i]=(int)( cx[i]*Math.cos(rad)-cy[i]*Math.sin(rad)  )+centerX;

		for(int i=0;i<n;i++)
		cy1[i]=(int)( cx[i]*Math.sin(rad)+cy[i]*Math.cos(rad)  )+centerY;	
		
	}
	public void matrixRotate(int[] x01,int[] y01,int[] x1,int y1[],int n,int centerX,int centerY,double rad){

		//边框
		for(int i=0;i<n;i++)
		x1[i]=(int)( x01[i]*Math.cos(rad)-y01[i]*Math.sin(rad)  )+centerX;
		
		for(int i=0;i<n;i++)
		y1[i]=(int)( x01[i]*Math.sin(rad)+y01[i]*Math.cos(rad)  )+centerY;	
	}

	public void pull(){
		
	}
	public void drawRect(Graphics g)
	{

		g.setColor(Color.green);
		g.drawLine(20,(int)scrY-10,20,(int)scrY-70);
		g.drawString("Y ^",8,(int)scrY-65);
		g.drawLine(20,(int)scrY-10,70,(int)scrY-10);
		g.drawString(">  X",70,(int)scrY-5);

for(int i=0;i<2;i++){
		
		g.setColor(Color.white);
		g.drawLine(x1[i][0],y1[i][0],x1[i][1],y1[i][1]);
		g.drawLine(x1[i][1],y1[i][1],x1[i][2],y1[i][2]);
		g.drawLine(x1[i][2],y1[i][2],x1[i][3],y1[i][3]);
		g.drawLine(x1[i][0],y1[i][0],x1[i][3],y1[i][3]);
		
		g.setColor(Color.red);
		g.drawLine(cx1[i][0],cy1[i][0],cx1[i][1],cy1[i][1]);
		g.drawLine(cx1[i][2],cy1[i][2],cx1[i][3],cy1[i][3]);
}

//最后面
		g.setColor(Color.blue);
	for(int i=0;i<w;i++){
		g.drawLine(ex21[0][i],ey21[0][i],ex41[0][i],ey41[0][i]);
		//g.drawLine(ex11[0][i],ey11[0][i],ex31[0][i],ey31[0][i]);
	}



//z向连线

		g.setColor(Color.white);

	for(int i=0;i<n;i++)
		g.drawLine(x1[0][i],y1[0][i],x1[1][i],y1[1][i]);

//Color cc=new Color(255,1,1);
		g.setColor(Color.red );
	
	for(int i=0;i<h;i++){
		//cc=new Color(1,255,i%255);
			//	g.setColor(cc );
					g.setColor(Color.red );

		g.drawLine(ex11[0][i],ey11[0][i],ex11[1][i],ey11[1][i]);
				g.setColor(Color.yellow );

		g.drawLine(ex31[0][i],ey31[0][i],ex31[1][i],ey31[1][i]);

	}
			g.setColor(Color.green);
	for(int i=0;i<w;i++){
		//cc=new Color(1,255,i%255);
			//	g.setColor(cc );

		g.drawLine(ex21[0][i],ey21[0][i],ex21[1][i],ey21[1][i]);
		g.drawLine(ex41[0][i],ey41[0][i],ex41[1][i],ey41[1][i]);

	}

//最前面		
		g.setColor(Color.blue);

	for(int i=0;i<h;i++){
		g.drawLine(ex11[1][i],ey11[1][i],ex31[1][i],ey31[1][i]);
		//g.drawLine(ex21[1][i],ey21[1][i],ex41[1][i],ey41[1][i]);
	}

		//fill(g,x1[1],y1[1]);
		//fill(g,y1[1],x1[1]);
		
	}



	public void drawRound(Graphics g){
		g.setColor(Color.green);
		g.drawLine(20,(int)scrY-10,20,(int)scrY-70);
		g.drawString("Y ^",8,(int)scrY-65);
		g.drawLine(20,(int)scrY-10,70,(int)scrY-10);
		g.drawString(">  X",70,(int)scrY-5);

		g.setColor(Color.white);
		g.drawOval(centerX[0]-Rx,centerY[0]-Ry,Rx+Rx,Ry+Ry);	
		
		g.setColor(Color.red);
		g.drawLine(cx1[0][0],cy1[0][0],cx1[0][1],cy1[0][1]);
		g.drawString("Y向",cx1[0][0],cy1[0][0]);
		g.drawLine(cx1[0][2],cy1[0][2],cx1[0][3],cy1[0][3]);
		g.drawString("X向",cx1[0][3],cy1[0][3]);
		
	}


public void doAction(int type)
{
	switch(type){
	case 1 :doTransform();break;
	case 2 :doRotate();break;
	default: doRotate();

	}
}

public void doRotate(){
	if(mouseX==centerX[0]){
		return;
	}
	double k=(mouseY-centerY[0])/(mouseX-centerX[0]);
	double dd=Math.atan(k);
	int i=0;
		rad+=dd/30;
		repaint();

}
                             


public void doTransform()
{

	centerX[0]=mouseX;
	centerY[0]=mouseY;
	repaint();
	
}
public void mouseClicked(MouseEvent e){
//	mouseX=e.getX();
//	mouseY=e.getY();
//	doAction(type);
}
public void mousePressed(MouseEvent e){
	mouseX=e.getX();
	mouseY=e.getY();
	int i=0;
	//while(e.getModifiers()!=MouseEvent. MOUSE_RELEASED ){
		doAction(type);
		//try{Thread.sleep(100);}catch(Exception ee){}
	//}

}
public void mouseReleased(MouseEvent e){
	mouseX=e.getX();
	mouseY=e.getY();	
	doAction(type);

}
public void mouseEntered(MouseEvent e){
}
public void mouseExited(MouseEvent e){
}
public void mouseDragged(MouseEvent e){
	mouseX=e.getX();
	mouseY=e.getY();	
	doAction(type);

}

public void mouseMoved(MouseEvent e){
	
	if(type==1&&e.getModifiers()==MouseEvent.CTRL_DOWN_MASK){
	mouseX=e.getX();
	mouseY=e.getY();	
	
	doAction(type);
	}
}

public void actionPerformed(ActionEvent e){
	if(e.getSource()==t)
		type=1;
	if(e.getSource()==r)
		type=2;
	if(e.getSource()==s1){
		//type=3;
		w*=2;
		h*=2;
		Rx*=2;
		Ry*=2;
		parmInit(0);
		repaint();
	}
	if(e.getSource()==s2){
		//type=4;
		if(w/2==0||h/2==0|Rx/2==0|Ry/2==0)return;
		w/=2;
		h/=2;
		Rx/=2;
		Ry/=2;

		parmInit(0);
		repaint();
	}
	if(e.getSource()==s11){
		//type=5;
		//w*=2;
		h*=2;
		Ry*=2;
		parmInit(0);
		repaint();
	}
	if(e.getSource()==s22){
		//type=5;
		w*=2;
		//h*=2;
		Rx*=2;
		parmInit(0);
		repaint();
	}
	if(e.getSource()==s111){
		//type=4;
		if(w/2==0||h/2==0|Rx/2==0|Ry/2==0)return;

		//w/=2;
		h/=2;
		Ry/=2;
		parmInit(0);
		repaint();
	}
	if(e.getSource()==s222){
		type=4;
		if(w/2==0||h/2==0|Rx/2==0|Ry/2==0)return;

		w/=2;
		//h/=2;
		Rx/=2;
		parmInit(0);
		repaint();
	}

	if(e.getSource()==ls){
//		type=4;
//		if(w/2==0||h/2==0|Rx/2==0|Ry/2==0)return;

//		w/=2;
		//h/=2;
//		Rx/=2;
//		parmInit();
//		repaint();
	}



}

}
