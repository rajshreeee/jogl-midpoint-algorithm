/**
 * 
 */
package rrassi2;

import java.awt.Frame;
import java.util.Scanner;  

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

/**
 * @author rajshree--- to make home
 *
 */
public class ellipse implements GLEventListener{

	/**
	 * @param args
	 */
	int pntX1 = 70,  pntY1=50,  rx = 50 ,ry=50;
	
	
	private GLU glu;
	
   
    
	
   
	
       
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		GLProfile glp = GLProfile.get(GLProfile.GL2);
		GLCapabilities cap = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(cap);
		
		Frame frame = new Frame("Assignment1");
		frame.setSize(1200, 800);
		frame.add(canvas);
		frame.setVisible(true);
		
		ellipse l = new ellipse();
	    canvas.addGLEventListener(l);  
	   
	
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}});
		}
		
		
	

	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
		GL2 gl = drawable.getGL().getGL2();

		gl.glClear (GL2.GL_COLOR_BUFFER_BIT);
		//gl.glColor3f (0.0f, 0.0f, 0.0f);
		gl.glPointSize(1.0f);

		midPointCircleAlgo(gl);

	gl.glFlush ();
	}
	
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void init(GLAutoDrawable gld) {
		// TODO Auto-generated method stub
		
		 GL2 gl = gld.getGL().getGL2();
		 glu = new GLU();

		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		gl.glColor3f(0.0f, 0.0f, 0.0f);
		gl.glPointSize(4.0f);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
	glu.gluOrtho2D(0.0, 640.0, 0.0, 480.0);
		
	}
	
	void plot(GL2 gl,int x, int y)
	{
		gl.glBegin(GL2.GL_POINTS);
		gl.glVertex2i(x+pntX1, y+pntY1);
		gl.glEnd();
	}

	void midPointCircleAlgo(GL2 gl)
	{
		int x = 0;
		int y = ry;
		float decision = ry^2-rx^2*ry+((rx^2)/4);
		plot(gl, x, y);

		while(! ((2*(ry^2)*x )> (2*(rx^2)*y)))
		{
			if (decision < 0)
			{
				x++; 
				decision += (2*(ry^2)*x)+ry^2 ;
			}
			else
			{
				y--;
				x++;
				decision +=(2*(ry^2)*x)-(2*(ry^2)*y)+ry^2;
			}
			plot(gl,x, y);
			plot(gl, -x, y);
			plot (gl, x,-y);
			plot (gl, -x, -y);
		
				
			
		}
		
		region2(gl, x,y, rx, ry);
		
		
	}
	private void region2(GL2 gl, int x, int y, int rx2, int ry2) {
		// TODO Auto-generated method stub
		double decision2 = (((ry*ry)*((x+0.5)*(x+0.5)))-((rx*rx)*(ry*ry))+((rx*rx)*((y-1)*(y-1))));//don't keep r^2, do r*r else bigrinxa
		plot(gl, x, y);

		while(y> 0)
		{
			if (decision2 > 0)
			{
				
				y--;
				decision2 = decision2 -(2*(rx*rx)*y)+rx*rx ;
			}
			else
			{
				y--;
				x++;
			
				decision2 = decision2 + (2*(ry*ry)*x)-(2*(rx*rx)*y)+rx*rx;
			}
			plot(gl,x, y);
			plot(gl, -x, y);
			plot (gl, x,-y);
			plot (gl, -x, -y);
		
			
		}

	}




	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		
		
		// TODO Auto-generated method stub
		
	}

}




