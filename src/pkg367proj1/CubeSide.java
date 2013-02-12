package pkg367proj1;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class CubeSide {
    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        // coordinate system origin at lower left with width and height same as the window
        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( -2, -2, width, height );
    }

    protected static void render( GL2 gl2, int width, int height ) {
        gl2.glClear( GL.GL_COLOR_BUFFER_BIT );

 
       
       //width = 624
        //height = 442
       
        gl2.glLoadIdentity();
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 0.3f, 0.3f, 0.3f );
        
         gl2.glVertex2f( 50, 550 );
        gl2.glVertex2f( 50, 50 );
        gl2.glVertex2f( 550, 550 );
        gl2.glVertex2f( 550, 50 );
        
        gl2.glEnd();

        
//        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
//        gl2.glColor3f( 1, 1, 1 );
//        gl2.glVertex2f( 0, 300 );//1
//        gl2.glVertex2f( 0, 200 );//2
//        gl2.glVertex2f( 100, 300 );//3
//        gl2.glVertex2f( 100, 200 );//4
//        gl2.glVertex2f( 200, 300 );//5
//        gl2.glVertex2f( 200, 200 );//6
//        gl2.glEnd();
//        
//        
//        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
//        gl2.glColor3f( 1, 1, 1 );
//        
//        gl2.glVertex2f( 0, 200 );//1
//        gl2.glVertex2f( 100, 200 );//2
//        gl2.glVertex2f( 100, 100 );//3
//        gl2.glVertex2f( 200, 200 );//4
//        gl2.glVertex2f( 200, 100 );//5
        
        
       //150 each wide, 25 gaps
        
        //bottom middle
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex2f( 375, 110 );
        gl2.glVertex2f( 375, 35 );
        gl2.glVertex2f( 225, 110 );
        gl2.glVertex2f( 225, 35 );
        gl2.glEnd();
        
        //top middle
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex2f( 375, 490 );
        gl2.glVertex2f( 375, 565 );
        gl2.glVertex2f( 225, 490 );
        gl2.glVertex2f( 225, 565 );
        gl2.glEnd();

        //left middle
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex2f( 110, 375 );
        gl2.glVertex2f( 35, 375 );
        gl2.glVertex2f( 110, 225 );
        gl2.glVertex2f( 35, 225 );
        gl2.glEnd();

        //right middle
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex2f( 490, 375 );
        gl2.glVertex2f( 565, 375 );
        gl2.glVertex2f( 490, 225 );
        gl2.glVertex2f( 565, 225 );
        gl2.glEnd();
       
        
        //top right
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex2f( 400, 575 );//1
        gl2.glVertex2f( 400, 487.5f );//2
        gl2.glVertex2f( 487.5f, 575 );//3
        gl2.glVertex2f( 487.5f, 487.5f );//4
        gl2.glVertex2f( 575, 575 );//5
        gl2.glVertex2f( 575, 487.5f );//6
        gl2.glEnd();
        
        
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        
        gl2.glVertex2f( 400, 487.5f );//1
        gl2.glVertex2f( 487.5f, 487.5f );//2
        gl2.glVertex2f( 487.5f, 400 );//3
        gl2.glVertex2f( 575, 487.5f );//4
        gl2.glVertex2f( 575, 400 );//5
        gl2.glEnd();

        //top left
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );



        gl2.glVertex2f( 200, 575 );//5
        gl2.glVertex2f( 200, 487.5f );//6
        gl2.glVertex2f( 112.5f, 575 );//3
        gl2.glVertex2f( 112.5f, 487.5f );//4
        gl2.glVertex2f( 25, 575 );//1
        gl2.glVertex2f( 25, 487.5f );//2

        //gl2.glVertex2f( 200, 487.5f );//6
        //gl2.glVertex2f( 112.5f, 575 );//3
        gl2.glEnd();
        
        
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        
        
        gl2.glVertex2f( 200, 487.5f );//4
        gl2.glVertex2f( 112.5f, 487.5f );//2
        gl2.glVertex2f( 112.5f, 400 );//3
        gl2.glVertex2f( 25, 487.5f );//1
        gl2.glVertex2f( 25, 400 );//5


        gl2.glEnd();
        
        
        
        
        
        //bottom left
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );

        gl2.glVertex2f( 200, 112.5f );//5
        gl2.glVertex2f( 112.5f, 112.5f );//6
        gl2.glVertex2f( 112.5f, 200 );//3
        gl2.glVertex2f( 25, 112.5f );//1
        gl2.glVertex2f( 25, 200 );//2

        //gl2.glVertex2f( 200, 487.5f );//6
        //gl2.glVertex2f( 112.5f, 575 );//3
        gl2.glEnd();
        
        
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        
        gl2.glVertex2f( 25, 25 );//4
        gl2.glVertex2f( 25, 112.5f );//4
        gl2.glVertex2f( 112.5f, 25 );//2
        gl2.glVertex2f( 112.5f, 112.5f );//3
        gl2.glVertex2f( 200, 25 );//1
        gl2.glVertex2f( 200, 112.5f );//5


        gl2.glEnd();
        
        
        //bottom right
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );

        gl2.glVertex2f( 400, 112.5f );//5
        gl2.glVertex2f( 487.5f, 200 );//6
        gl2.glVertex2f( 487.5f, 112.5f );//6
        gl2.glVertex2f( 575, 200 );//3
        gl2.glVertex2f( 575, 112.5f );//1
        //gl2.glVertex2f( 25, 200 );//2

        //gl2.glVertex2f( 200, 487.5f );//6
        //gl2.glVertex2f( 112.5f, 575 );//3
        gl2.glEnd();
        
        
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        
        gl2.glVertex2f( 400, 25 );//4
        gl2.glVertex2f( 400, 112.5f );//4
        gl2.glVertex2f( 487.5f, 25 );//2
        gl2.glVertex2f( 487.5f, 112.5f );//3
        gl2.glVertex2f( 575, 25 );//1
        gl2.glVertex2f( 575, 112.5f );//5


        gl2.glEnd();
        
        
        
       
       
       
//        gl2.glLoadIdentity();
//        gl2.glBegin( GL.GL_TRIANGLES );
//        gl2.glColor3f( 1, 0, 0 );
//        gl2.glVertex2f( 0, 0 );
//        gl2.glColor3f( 0, 1, 0 );
//        gl2.glVertex2f( width, 0 );
//        gl2.glColor3f( 0, 0, 1 );
//        gl2.glVertex2f( width / 2, height );
//        gl2.glEnd();
    }
}