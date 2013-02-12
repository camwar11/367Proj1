package pkg367proj1;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class CubeSide2 {
    static double[][] cfCube;
    static int listId, cylList, midList, cornerList, allMidsList, allCornList;


    protected static void render( GL2 gl2){
        for(int i=0; i<cfCube.length;i++){
        gl2.glPushMatrix();
        gl2.glMultMatrixd(cfCube[i], 0);
        gl2.glCallList(listId);
        gl2.glPopMatrix();
        }        
    }
    
    
    protected static void renderCubeAddons( GL2 gl2, float x, float y, float z){
        
        //bottom middle front
        
        midList = gl2.glGenLists(1);
        gl2.glNewList(midList, GL2.GL_COMPILE);
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.25f, y+1.10f, z );
        gl2.glVertex3f( x+2.25f, y+.25f, z );
        gl2.glVertex3f( x+3.75f, y+1.10f, z );
        gl2.glVertex3f( x+3.75f, y+.25f, z );
        gl2.glEnd();
        gl2.glEndList();
        
        gl2.glGenLists(1);
        gl2.glNewList(allMidsList, GL2.GL_COMPILE);
        for(int i = 0; i < 4; i++){
            gl2.glPushMatrix();
            gl2.glRotatef(90f*i, 0, 0, 1);
            gl2.glCallList(midList);
            gl2.glPopMatrix();
        }
        gl2.glEndList();
        
        //////////////////////////////////////////////////////////////////////////////
        //bottom middle left
        /*
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.25f, y+.35f, z );
        gl2.glVertex3f( x+2.25f, y+1.10f, z );
        gl2.glVertex3f( x+2.25f, y+.35f, z-.75f );
        gl2.glVertex3f( x+2.25f, y+1.10f, z-.75f );
        gl2.glEnd();
        
        //bottom middle right
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+3.75f, y+1.10f, z );
        gl2.glVertex3f( x+3.75f, y+.35f, z );
        gl2.glVertex3f( x+3.75f, y+1.10f, z-.75f );
        gl2.glVertex3f( x+3.75f, y+.35f, z-.75f );
        gl2.glEnd();
        
//        //bottom middle bottom
//        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
//        gl2.glColor3f( 1, 1, 1 );
//        gl2.glVertex3f( x+2.25f, y+.35f, z );
//        gl2.glVertex3f( x+2.25f, y+.35f, z-.75f );
//        gl2.glVertex3f( x+3.75f, y+.35f, z );
//        gl2.glVertex3f( x+3.755f, y+.35f, z-.75f );
//        gl2.glEnd();
        
        
        //bottom middle top
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.25f, y+1.10f, z-.75f );
        gl2.glVertex3f( x+2.25f, y+1.10f, z );
        gl2.glVertex3f( x+3.755f, y+1.10f, z-.75f );
        gl2.glVertex3f( x+3.75f, y+1.10f, z );
        gl2.glEnd();
        ///////////////////////////////////////////////////////////////////////////////////
        //top middle front
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+3.75f, y+4.9f, z );
        gl2.glVertex3f( x+3.75f, y+5.75f, z );
        gl2.glVertex3f( x+2.25f, y+4.9f, z );
        gl2.glVertex3f( x+2.25f, y+5.75f, z );
        gl2.glEnd();
        
        

//        //top middle left
//        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
//        gl2.glColor3f( 1, 1, 1 );
//        gl2.glVertex3f( x+3.75f, y+5.65f, z );
//        gl2.glVertex3f( x+3.75f, y+4.90f, z );
//        gl2.glVertex3f( x+3.75f, y+5.65f, z-.75f );
//        gl2.glVertex3f( x+3.75f, y+4.90f, z-.75f );
//        gl2.glEnd();
//        
//        //top middle right
//        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
//        gl2.glColor3f( 1, 1, 1 );
//        gl2.glVertex3f( x+2.25f, y+4.90f, z );
//        gl2.glVertex3f( x+2.25f, y+5.65f, z );
//        gl2.glVertex3f( x+2.25f, y+4.90f, z-.75f );
//        gl2.glVertex3f( x+2.25f, y+5.65f, z-.75f );
//        gl2.glEnd();
//        
//        //top middle top
//        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
//        gl2.glColor3f( 1, 1, 1 );
//        gl2.glVertex3f( x+2.25f, y+4.90f, z );
//        gl2.glVertex3f( x+2.25f, y+4.90f, z-.75f );
//        gl2.glVertex3f( x+3.75f, y+4.90f, z );
//        gl2.glVertex3f( x+3.755f, y+4.90f, z-.75f );
//        gl2.glEnd();
        

        
        //left middle
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+1.10f, y+3.75f, z );
        gl2.glVertex3f( x+.25f, y+3.75f, z );
        gl2.glVertex3f( x+1.10f, y+2.25f, z );
        gl2.glVertex3f( x+.25f, y+2.25f, z );
        gl2.glEnd();

        
        //right middle
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.90f, y+3.75f, z );
        gl2.glVertex3f( x+4.90f, y+2.25f, z );
        gl2.glVertex3f( x+5.75f, y+3.75f, z );
        gl2.glVertex3f( x+5.75f, y+2.25f, z );
        gl2.glEnd();        */

       
       // x=x-.1f;
                //y=y-.1f;

        cornerList = gl2.glGenLists(1);
        gl2.glNewList(cornerList, GL2.GL_COMPILE);
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1,1  );
        gl2.glVertex3f( x+4.00f, y+5.75f, z );//1
        gl2.glVertex3f( x+4.00f, y+4.875f, z );//2
        gl2.glVertex3f( x+4.875f, y+5.75f, z );//3
        gl2.glVertex3f( x+4.875f, y+4.875f, z );//4
        gl2.glVertex3f( x+5.75f, y+5.75f, z );//5
        gl2.glVertex3f( x+5.75f, y+4.875f, z );//6
        gl2.glEnd();

        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.00f, y+4.875f, z );//1
        gl2.glVertex3f( x+4.875f, y+4.00f, z );//3
        gl2.glVertex3f( x+4.875f, y+4.875f, z );//2
        gl2.glVertex3f( x+5.75f, y+4.00f, z );//5
        gl2.glVertex3f( x+5.75f, y+4.875f, z );//4
        gl2.glEnd();
        gl2.glEndList();
        
        gl2.glGenLists(1);
        gl2.glNewList(allCornList, GL2.GL_COMPILE);
        for(int i = 0; i < 4; i++){
            gl2.glPushMatrix();
            gl2.glRotatef(90f*i, 0, 0, 1);
            gl2.glCallList(midList);
            gl2.glPopMatrix();
        }
        gl2.glEndList();
        
        gl2.glCallList(cylList);
        gl2.glCallList(allCornList);
        gl2.glCallList(allMidsList);
        //top right
        /*

        
        //top left
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+.25f, y+5.75f, z );//1
        gl2.glVertex3f( x+.25f, y+4.875f, z );//2
        gl2.glVertex3f( x+1.125f, y+5.75f, z );//3
        gl2.glVertex3f( x+1.125f, y+4.875f, z );//4
        gl2.glVertex3f( x+2.00f, y+5.75f, z );//5
        gl2.glVertex3f( x+2.00f, y+4.875f, z );//6
        gl2.glEnd();
         
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.00f, y+4.875f, z );//4
        gl2.glVertex3f( x+1.125f, y+4.875f, z );//2
        gl2.glVertex3f( x+1.125f, y+4.00f, z );//3
        gl2.glVertex3f( x+.25f, y+4.875f, z );//1
        gl2.glVertex3f( x+.25f, y+4.00f, z );//5
        gl2.glEnd();
        
        
        //bottom left
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+.25f, y+2.00f, z );//2
        gl2.glVertex3f( x+.25f, y+1.125f, z );//1
        gl2.glVertex3f( x+1.125f, y+2.00f, z );//3
        gl2.glVertex3f( x+1.125f, y+1.125f, z );//6
        gl2.glVertex3f( x+2.00f, y+1.125f, z );//5
        gl2.glEnd();
        
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+.25f, y+1.125f, z );//4
        gl2.glVertex3f( x+.25f, y+.25f, z );//4
        gl2.glVertex3f( x+1.125f, y+1.125f, z );//3
        gl2.glVertex3f( x+1.125f, y+.25f, z );//2
        gl2.glVertex3f( x+2.00f, y+1.125f, z );//5
        gl2.glVertex3f( x+2.00f, y+.25f, z );//1
        gl2.glEnd();
        
        
        //bottom right
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.00f, y+1.125f, z );//5
        gl2.glVertex3f( x+4.875f, y+1.125f, z );//6
        gl2.glVertex3f( x+4.875f, y+2.00f, z );//6
        gl2.glVertex3f( x+5.75f, y+1.125f, z );//1
        gl2.glVertex3f( x+5.75f, y+2.00f, z );//3
        gl2.glEnd();
        
        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.00f, y+1.125f, z );//4
        gl2.glVertex3f( x+4.00f, y+.25f, z );//4
        gl2.glVertex3f( x+4.875f, y+1.125f, z );//3
        gl2.glVertex3f( x+4.875f, y+.25f, z );//2
        gl2.glVertex3f( x+5.75f, y+1.125f, z );//5
        gl2.glVertex3f( x+5.75f, y+.25f, z );//1
        gl2.glEnd();*/
        }
    
    
      private static void setUpCoordFrames(GL2 gl2){
        //initialize all coord frames
        gl2.glPushMatrix();
        cfCube = new double[6][16];
        
        gl2.glLoadIdentity();
        for(int i=0; i<cfCube.length;i++){
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, cfCube[i],0);
        }
        gl2.glPopMatrix();
        
        //for(int j=0; j<cfCube.length;j++){
        //Cake.globalRotate(gl2, 90, cfCube[0], new Triple(0f,0f,1f));//rotate to populate he cake with berries!
        Cake.localRotate(gl2, 90, cfCube[1], new Triple(0f,1f,0f));//rotate to populate the cake with berries!
        Cake.localTranslate(gl2,cfCube[1], new Triple(2.5f,0f,2.5f));
        Cake.localRotate(gl2, -90, cfCube[2], new Triple(0f,1f,0f));//rotate to populate the cake with berries!
        Cake.localTranslate(gl2,cfCube[2], new Triple(-2.5f,0f,2.5f));
        Cake.localRotate(gl2, 180, cfCube[3], new Triple(0f,1f,0f));//rotate to populate the cake with berries!
        Cake.localTranslate(gl2,cfCube[3], new Triple(0f,0f,5f));
        Cake.localRotate(gl2, 90, cfCube[4], new Triple(1f,0f,0f));//rotate to populate the cake with berries!
        Cake.localTranslate(gl2,cfCube[4], new Triple(0f,-2.5f,-2.5f));
        Cake.localRotate(gl2, -90, cfCube[5], new Triple(1f,0f,0f));//rotate to populate the cake with berries!
        Cake.localTranslate(gl2,cfCube[5], new Triple(0f,2.5f,2.5f));
        //Cake.localTranslate(gl2, cfCube[1], new Triple(0f,-3.5f,0f));
        
        //Cake.localTranslate(gl2, cfCube[2], new Triple(0f,0f,-1f));
        //Cake.localRotate(gl2, 180, cfCube[2], new Triple(0f,1f,0f));//rotate to populate the cake with berries!
        
        //Cake.localRotate(gl2, 90, cfCube[3], new Triple(0f,0f,1f));//rotate to populate the cake with berries!
        //Cake.localRotate(gl2, 90, cfCube[4], new Triple(0f,0f,1f));//rotate to populate the cake with berries!
        //Cake.localRotate(gl2, 90, cfCube[5], new Triple(0f,0f,1f));//rotate to populate the cake with berries!
        //}
    }
      
      
      public static void init(GL2 gl2){
        setUpCoordFrames(gl2);
        cylList = gl2.glGenLists(1);
        gl2.glNewList(cylList, GL2.GL_COMPILE);
        Cake.initCylinder(gl2, 1f, .15f, new Triple(127,127,127), new Triple(255,255,255), 1);
        gl2.glEndList();
        
        listId = gl2.glGenLists(1);
        gl2.glNewList(listId, GL2.GL_COMPILE);
        
        float x = -3.0f;
        float y = -3.0f;
        float z = 0;
        
        renderCubeAddons(gl2,x,y, z+.25f);

        gl2.glBegin( GL.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 0.3f, 0.3f, 0.3f );
        
        gl2.glVertex3f( x+.50f, y+5.50f, z );
        gl2.glVertex3f( x+.50f, y+.50f, z );
        gl2.glVertex3f( x+5.50f, y+5.50f, z );
        gl2.glVertex3f( x+5.50f, y+.50f, z );
        
        gl2.glEnd();
        
        gl2.glPopMatrix();

        gl2.glEndList();
      }
}