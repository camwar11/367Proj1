package edu.gvsu.cis.proj1;

import java.util.ArrayList;
import java.util.Random;
import android.opengl.*;
import javax.microedition.khronos.opengles.GL11;

/**
 *
 * @author Cam Warner, Andrew Zimny, Eric Munson
 */
public class CubeSide {
    static float[][] cfCubeSides;
    static ArrayList<float[]> cfCubes;
    static int sideList, cylList;
    static ArrayList<Integer> cubeLists;
    static Random rand = new Random();
    static boolean renderRandom = true;
    
    protected static void render( GL11 gl2){
        
        //splitting these up lets us not render the random cubes
        for(int i = 0; i < 4; i++){
            gl2.glPushMatrix();
            gl2.glMultMatrixf(cfCubes.get(i),0);
            gl2.glCallList(cubeLists.get(i));
            gl2.glPopMatrix();
        }
        
        for(int i = 4; i < cfCubes.size() && renderRandom; i++){
            gl2.glPushMatrix();
            gl2.glMultMatrixf(cfCubes.get(i),0);
            gl2.glCallList(cubeLists.get(i));
            gl2.glPopMatrix();
        }
    }
    
    
    protected static void initCubeAddons( GL11 gl2, float x, float y, float z, Triple<Integer> color){
        Cake.initCylinder(gl2, 1f, .15f, new Triple(127,127,127), color, 1);
        //bottom middle front
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );

        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.25f, y+1.10f, z );
        gl2.glVertex3f( x+2.25f, y+.25f, z );
        gl2.glVertex3f( x+3.75f, y+1.10f, z );
        gl2.glVertex3f( x+3.75f, y+.25f, z );
        gl2.glEnd();
        
        //////////////////////////////////////////////////////////////////////////////
        //bottom middle left
        
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.25f, y+.35f, z );
        gl2.glVertex3f( x+2.25f, y+1.10f, z );
        gl2.glVertex3f( x+2.25f, y+.35f, z-.75f );
        gl2.glVertex3f( x+2.25f, y+1.10f, z-.75f );
        gl2.glEnd();
        
        //bottom middle right
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
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
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.25f, y+1.10f, z-.75f );
        gl2.glVertex3f( x+2.25f, y+1.10f, z );
        gl2.glVertex3f( x+3.755f, y+1.10f, z-.75f );
        gl2.glVertex3f( x+3.75f, y+1.10f, z );
        gl2.glEnd();
        
        ///////////////////////////////////////////////////////////////////////////////////
        //top middle front
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
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
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+1.10f, y+3.75f, z );
        gl2.glVertex3f( x+.25f, y+3.75f, z );
        gl2.glVertex3f( x+1.10f, y+2.25f, z );
        gl2.glVertex3f( x+.25f, y+2.25f, z );
        gl2.glEnd();

        
        //right middle
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.90f, y+3.75f, z );
        gl2.glVertex3f( x+4.90f, y+2.25f, z );
        gl2.glVertex3f( x+5.75f, y+3.75f, z );
        gl2.glVertex3f( x+5.75f, y+2.25f, z );
        gl2.glEnd();
       

        //top right
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1,1  );
        gl2.glVertex3f( x+4.00f, y+5.75f, z );//1
        gl2.glVertex3f( x+4.00f, y+4.875f, z );//2
        gl2.glVertex3f( x+4.875f, y+5.75f, z );//3
        gl2.glVertex3f( x+4.875f, y+4.875f, z );//4
        gl2.glVertex3f( x+5.75f, y+5.75f, z );//5
        gl2.glVertex3f( x+5.75f, y+4.875f, z );//6
        gl2.glEnd();

        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.00f, y+4.875f, z );//1
        gl2.glVertex3f( x+4.875f, y+4.00f, z );//3
        gl2.glVertex3f( x+4.875f, y+4.875f, z );//2
        gl2.glVertex3f( x+5.75f, y+4.00f, z );//5
        gl2.glVertex3f( x+5.75f, y+4.875f, z );//4
        gl2.glEnd();

        
        //top left
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+.25f, y+5.75f, z );//1
        gl2.glVertex3f( x+.25f, y+4.875f, z );//2
        gl2.glVertex3f( x+1.125f, y+5.75f, z );//3
        gl2.glVertex3f( x+1.125f, y+4.875f, z );//4
        gl2.glVertex3f( x+2.00f, y+5.75f, z );//5
        gl2.glVertex3f( x+2.00f, y+4.875f, z );//6
        gl2.glEnd();
         
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.00f, y+4.875f, z );//4
        gl2.glVertex3f( x+1.125f, y+4.875f, z );//2
        gl2.glVertex3f( x+1.125f, y+4.00f, z );//3
        gl2.glVertex3f( x+.25f, y+4.875f, z );//1
        gl2.glVertex3f( x+.25f, y+4.00f, z );//5
        gl2.glEnd();
        
        
        //bottom left
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+.25f, y+2.00f, z );//2
        gl2.glVertex3f( x+.25f, y+1.125f, z );//1
        gl2.glVertex3f( x+1.125f, y+2.00f, z );//3
        gl2.glVertex3f( x+1.125f, y+1.125f, z );//6
        gl2.glVertex3f( x+2.00f, y+1.125f, z );//5
        gl2.glEnd();
        
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+.25f, y+1.125f, z );//4
        gl2.glVertex3f( x+.25f, y+.25f, z );//4
        gl2.glVertex3f( x+1.125f, y+1.125f, z );//3
        gl2.glVertex3f( x+1.125f, y+.25f, z );//2
        gl2.glVertex3f( x+2.00f, y+1.125f, z );//5
        gl2.glVertex3f( x+2.00f, y+.25f, z );//1
        gl2.glEnd();
     
        
 ////////////////////////////////////////////////////////////////////////////////////       
        //bottom corner left sides
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+1.125f, y+2.00f, z );//4
        gl2.glVertex3f( x+1.125f, y+2.00f, z-.15f );//4
        gl2.glVertex3f( x+.25f, y+2.00f, z );//4
        gl2.glVertex3f( x+.25f, y+2.00f, z-.15f );//4
        gl2.glEnd();
        
        
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.00f, y+1.125f, z );//4
        gl2.glVertex3f( x+2.00f, y+1.125f, z-.15f );//4
        gl2.glVertex3f( x+1.125f, y+2.00f, z );//4
        gl2.glVertex3f( x+1.125f, y+2.00f, z-.15f );//4
        gl2.glEnd();
        
        
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+2.00f, y+.25f, z );//4
        gl2.glVertex3f( x+2.00f, y+.25f, z-.15f );//4
        gl2.glVertex3f( x+2.00f, y+1.125f, z );//4
        gl2.glVertex3f( x+2.00f, y+1.125f, z-.15f );//4
        gl2.glEnd();
        
        
/////////////////////////////////////////////////////////////////////////////////////      
        //bottom right
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.00f, y+1.125f, z );//5
        gl2.glVertex3f( x+4.875f, y+1.125f, z );//6
        gl2.glVertex3f( x+4.875f, y+2.00f, z );//6
        gl2.glVertex3f( x+5.75f, y+1.125f, z );//1
        gl2.glVertex3f( x+5.75f, y+2.00f, z );//3
        gl2.glEnd();
        
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        //gl2.glColor3f( 1, 1, 1 );
        gl2.glVertex3f( x+4.00f, y+1.125f, z );//4
        gl2.glVertex3f( x+4.00f, y+.25f, z );//4
        gl2.glVertex3f( x+4.875f, y+1.125f, z );//3
        gl2.glVertex3f( x+4.875f, y+.25f, z );//2
        gl2.glVertex3f( x+5.75f, y+1.125f, z );//5
        gl2.glVertex3f( x+5.75f, y+.25f, z );//1
        gl2.glEnd();
        
        gl2.glBegin( GL11.GL_TRIANGLE_STRIP );
        gl2.glColor3f( 0.3f, 0.3f, 0.3f );
        
        gl2.glVertex3f( x+.50f, y+5.50f, z-.25f );
        gl2.glVertex3f( x+.50f, y+.50f, z-.25f );
        gl2.glVertex3f( x+5.50f, y+5.50f, z-.25f );
        gl2.glVertex3f( x+5.50f, y+.50f, z-.25f );
        gl2.glEnd();
//        gl2.glLineWidth(.2f); 
//        gl2.glBegin(GL.GL_LINES); 
//        gl2.glVertex3f( x+2.5f, y+2.5f, z); 
//        gl2.glVertex3f( x+2.5f, y-2.5f, z); 
//        gl2.glEnd(); 
        

        }
    
    
      private static void setUpCoordFrames(GL11 gl2){
        //initialize all coord frames
        gl2.glPushMatrix();
        cfCubeSides = new float[6][16];
        cfCubes = new ArrayList<float[]>();
        gl2.glLoadIdentity();
        for(int i=0; i<cfCubeSides.length;i++){
            gl2.glGetFloatv(GL11.GL_MODELVIEW_MATRIX, cfCubeSides[i],0);//TODO: don't know if this works
        }
        for(int i=0; i<40;i++){
            cfCubes.add(new float[16]);
            gl2.glGetFloatv(GL11.GL_MODELVIEW_MATRIX, cfCubes.get(i),0);
        }
        gl2.glPopMatrix();
        
        //set up the sides' coord frames
        Cake.localRotate(gl2, 90, cfCubeSides[1], new Triple(0f,1f,0f));
        Cake.localTranslate(gl2,cfCubeSides[1], new Triple(2.5f,0f,2.5f));
        Cake.localRotate(gl2, -90, cfCubeSides[2], new Triple(0f,1f,0f));
        Cake.localTranslate(gl2,cfCubeSides[2], new Triple(-2.5f,0f,2.5f));
        Cake.localRotate(gl2, 180, cfCubeSides[3], new Triple(0f,1f,0f));
        Cake.localTranslate(gl2,cfCubeSides[3], new Triple(0f,0f,5f));
        Cake.localRotate(gl2, 90, cfCubeSides[4], new Triple(1f,0f,0f));
        Cake.localTranslate(gl2,cfCubeSides[4], new Triple(0f,-2.5f,2.5f));
        Cake.localRotate(gl2, -90, cfCubeSides[5], new Triple(1f,0f,0f));
        Cake.localTranslate(gl2,cfCubeSides[5], new Triple(0f,2.5f,2.5f));

        //set up the cubes from the picture
        Cake.globalTranslate(gl2, cfCubes.get(0), new Triple<Float>(12f,8f,3f));
        Cake.globalTranslate(gl2, cfCubes.get(1), new Triple<Float>(20f,11f,3f));
        Cake.globalTranslate(gl2, cfCubes.get(2), new Triple<Float>(15f,10f,9.1f));
        Cake.globalTranslate(gl2, cfCubes.get(3), new Triple<Float>(4f,14f,4f));
        Cake.localRotate(gl2, 45, cfCubes.get(3), new Triple<Float>(1f,1f,0f));
        
        //set up some random different color cubes
        for(int i = 4; i < cfCubes.size(); i++){
            double x = rand.nextInt(60)*3*rand.nextGaussian();
            double y = rand.nextInt(60)*3*rand.nextGaussian();
            double z = rand.nextInt(60)*3*rand.nextGaussian();
            float deg = rand.nextInt(361);
            
            Cake.globalTranslate(gl2, cfCubes.get(i), new Triple<Float>((float)x,(float)y,(float)z));
            Cake.localRotate(gl2, deg, cfCubes.get(i), new Triple<Float>(1f,1f,1f));
        }
        
    }
      
      
      public static void init(GL11 gl2){
        setUpCoordFrames(gl2);
   
        sideList = gl2.glGenLists(1);
        gl2.glNewList(sideList, GL2.GL_COMPILE);
        
        float x = -3.0f;
        float y = -3.0f;
        float z = 0;
        
        gl2.glEndList();
        
        cubeLists = new ArrayList<Integer>();
        for(int i = 0; i < 40; i++){
            cubeLists.add(gl2.glGenLists(1));
            gl2.glNewList(cubeLists.get(i), GL2.GL_COMPILE);
            for(int j=0; j<cfCubeSides.length;j++){
                gl2.glPushMatrix();
                gl2.glMultMatrixd(cfCubeSides[j], 0);
                if(i<4)
                    initCubeAddons(gl2,x,y, z+.25f,new Triple<Integer>(182-20*j,192-20*j,184-20*j));//create the 4 normal cubes
                else{
                    int r = rand.nextInt(256);
                    int g = rand.nextInt(256);
                    int b = rand.nextInt(256);
                    initCubeAddons(gl2,x,y, z+.25f,new Triple<Integer>(r,g,b)); //create a cube with random colors
                }
                gl2.glPopMatrix();
            }
            gl2.glEndList();
        }
        /*
        ////////////////////////////////////////////////////////////////////////////
        //attempt at lines = fail
        gl2.glLineWidth(.2f); 
        gl2.glBegin(GL.GL_LINES);
        gl2.glColor3f( 1, 0, 0 );
        gl2.glVertex3f( x+5.5f, y, z-2.5f); 
        gl2.glVertex3f( x+.5f, y, z-2.5f); 
        gl2.glEnd(); 
        ////////////////////////////////////////////////////////////////////////////////
        gl2.glPopMatrix();*/
      }
}