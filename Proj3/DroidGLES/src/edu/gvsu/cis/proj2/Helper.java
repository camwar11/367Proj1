/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package pkg367proj1;

import javax.media.opengl.awt.GLCanvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.swing.Timer;

/**
*
* @author Cam Warner, Andrew Zimny, Eric Munson
*/
public class Helper {
    static double[][] lookAts;
    static int sceneNum = 0;
    static int prevSceneNum = 0;
    static int movePos = 0;
    static int fillMode = GL2.GL_FILL;
    static double eyeX;
    static double eyeY;
    static double eyeZ;
    static double focX;
    static double focY;
    static double focZ;
    static double camX;
    static double camY;
    static double camZ;
    static GLCanvas glcanvas;
    static int time;
    static float timeFactor;
    static GL2 myGL;
    static boolean increasingSpeed = true;
    public static boolean rotating = true;
    
    protected static void setup( GL2 gl2, int width, int height ) {
        myGL = gl2;
        time = 0;
        timeFactor = .002f;
        
        gl2.glViewport( 0, 0, width, height );
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glEnable(GL.GL_DEPTH_TEST);
        gl2.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(90f, width/(float)height, 4, 1000);
        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();
        glu.gluLookAt( lookAts[sceneNum][0], lookAts[sceneNum][1], lookAts[sceneNum][2], //eye position x,y,z
                        lookAts[sceneNum][3], lookAts[sceneNum][4], lookAts[sceneNum][5], //focus x,y,z
                        lookAts[sceneNum][6], lookAts[sceneNum][7], lookAts[sceneNum][8]);//camera up x,y,z
        
        //gl2.glEnable (GL2.GL_LIGHTING);
        gl2.glEnable (GL2.GL_NORMALIZE);
          
    }
     
    protected static void init(GL2 gl2){
        //setup the 4 look at positions
        lookAts = new double[4][9];
        lookAts[0] = new double[]{0,80,80,0,0,0,0,0,1};
        lookAts[1] = new double[]{50,35,15,0,0,0,0,0,1};
        lookAts[2] = new double[]{0,0,90,0,0,0,0,1,0};
        lookAts[3] = new double[]{100,-200,20,0,0,0,0,0,1};
        
        //A Timer is used to calculate the movement between points that we are at for smooth animation
        int delay = 100; //milliseconds
        ActionListener calcMovement = new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
                 Helper.calcMove();
            }
        };
        new Timer(delay, calcMovement).start();
        gl2.glPolygonMode (GL.GL_FRONT, GL2.GL_FILL);
        gl2.glPolygonMode (GL.GL_BACK, GL2.GL_LINE);
    }
    
   
    public static void globalRotate(GL2 gl2, float degrees, double[] matrix, Triple<Float> axis){
        gl2.glPushMatrix();
        gl2.glLoadIdentity();
        gl2.glRotatef(degrees, axis.X(), axis.Y(), axis.Z());
        gl2.glMultMatrixd(matrix, 0);
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    public static void globalTranslate(GL2 gl2,double[] matrix, Triple<Float> translation){
        gl2.glPushMatrix();
        gl2.glLoadIdentity();
        gl2.glTranslatef(translation.X(), translation.Y(), translation.Z());
        gl2.glMultMatrixd(matrix, 0);
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    public static void localRotate(GL2 gl2, float degrees, double[] matrix, Triple<Float> axis){
        gl2.glPushMatrix();
        gl2.glLoadMatrixd(matrix, 0);
        gl2.glRotatef(degrees, axis.X(), axis.Y(), axis.Z());
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    public static void localTranslate(GL2 gl2,double[] matrix, Triple<Float> translation){
        gl2.glPushMatrix();
        gl2.glLoadMatrixd(matrix,0);
        gl2.glTranslatef(translation.X(), translation.Y(), translation.Z());
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
        public static void globalRotate(GL2 gl2, float degrees, float[] matrix, Triple<Float> axis){
        gl2.glPushMatrix();
        gl2.glLoadIdentity();
        gl2.glRotatef(degrees, axis.X(), axis.Y(), axis.Z());
        gl2.glMultMatrixf(matrix, 0);
        gl2.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    public static void globalTranslate(GL2 gl2,float[] matrix, Triple<Float> translation){
        gl2.glPushMatrix();
        gl2.glLoadIdentity();
        gl2.glTranslatef(translation.X(), translation.Y(), translation.Z());
        gl2.glMultMatrixf(matrix, 0);
         gl2.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    public static void localRotate(GL2 gl2, float degrees, float[] matrix, Triple<Float> axis){
        gl2.glPushMatrix();
        gl2.glLoadMatrixf(matrix, 0);
        gl2.glRotatef(degrees, axis.X(), axis.Y(), axis.Z());
         gl2.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    public static void localTranslate(GL2 gl2,float[] matrix, Triple<Float> translation){
        gl2.glPushMatrix();
        gl2.glLoadMatrixf(matrix,0);
        gl2.glTranslatef(translation.X(), translation.Y(), translation.Z());
        gl2.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, matrix, 0);
        gl2.glPopMatrix();
    }
    
    protected static void render( GL2 gl ) {
        gl.glPolygonMode (GL.GL_FRONT, fillMode);
        //have to do this here so that the lookat updates when the view is changed with a keypress
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
        GLU glu = new GLU();
        
        glu.gluLookAt( eyeX, eyeY, eyeZ, //eye position x,y,z
                        focX, focY, focZ, //focus x,y,z
                        camX, camY, camZ);//camera up x,y,z
        
        
        gl.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        
        gl.glFlush();
    }
    
    protected static void calcMove(){
        eyeX = lookAts[sceneNum][0];
        eyeY = lookAts[sceneNum][1];
        eyeZ = lookAts[sceneNum][2];
        focX = lookAts[sceneNum][3];
        focY = lookAts[sceneNum][4];
        focZ = lookAts[sceneNum][5];
        camX = lookAts[sceneNum][6];
        camY = lookAts[sceneNum][7];
        camZ = lookAts[sceneNum][8];
        
        if(sceneNum != prevSceneNum){
            eyeX = ((eyeX-lookAts[prevSceneNum][0])/20 *movePos) + lookAts[prevSceneNum][0];
            eyeY = ((eyeY-lookAts[prevSceneNum][1])/20*movePos) + lookAts[prevSceneNum][1];
            eyeZ = ((eyeZ-lookAts[prevSceneNum][2])/20*movePos) + lookAts[prevSceneNum][2];
            focX = ((focX-lookAts[prevSceneNum][3])/20*movePos) + lookAts[prevSceneNum][3];
            focY = ((focY-lookAts[prevSceneNum][4])/20*movePos) + lookAts[prevSceneNum][4];
            focZ = ((focZ-lookAts[prevSceneNum][5])/20*movePos) + lookAts[prevSceneNum][5];
            camX = ((camX-lookAts[prevSceneNum][6])/20*movePos) + lookAts[prevSceneNum][6];
            camY = ((camY-lookAts[prevSceneNum][7])/20*movePos) + lookAts[prevSceneNum][7];
            camZ = ((camZ-lookAts[prevSceneNum][8])/20*movePos) + lookAts[prevSceneNum][8];
            movePos++;
            
            if(movePos == 21){
                prevSceneNum = sceneNum;
                movePos = 0;
            }
                
            
        }
        if(rotating) {
            rotateScene(time * timeFactor);
            if (increasingSpeed) {
                time++;
            } else {
                time--;
            }

            if (time % 100 == 0) {
                increasingSpeed = !increasingSpeed;
            }
        }
        //update the graphics as we move
        glcanvas.display();
        
    }

    private static void rotateScene(float f) {
        Test.cyl.rotateZ(f);
        Test.teapots.rotateZ(f);
    }
}