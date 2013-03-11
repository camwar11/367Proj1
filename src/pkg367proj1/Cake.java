/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg367proj1;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

   





public class Cake {
    static int liBerry,liCake, liCandle, liFrosting, liTopping, liBottom, liBottomPts;
    static double[] cfCake, cfCandle, cfBottom;
    static double[][] cfToppings, cfBottomPts;
    static int[][] lookAts;
    static int sceneNum = 0;
    static int fillMode = GL2.GL_FILL;
    
    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glViewport( 0, 0, width, height );
        gl2.glMatrixMode( GL2.GL_PROJECTION );
        gl2.glEnable(GL.GL_DEPTH_TEST);
        gl2.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(90f, width/(float)height, 5, 100);
        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();
        glu.gluLookAt(  lookAts[sceneNum][0], lookAts[sceneNum][1], lookAts[sceneNum][2],  //eye position x,y,z
                        lookAts[sceneNum][3],  lookAts[sceneNum][4], lookAts[sceneNum][5],   //focus x,y,z
                        lookAts[sceneNum][6],  lookAts[sceneNum][7], lookAts[sceneNum][8]);//camera up x,y,z
          
    }
     
    protected static void init(GL2 gl2){
        gl2.glPolygonMode (GL.GL_FRONT, GL2.GL_FILL);
        gl2.glPolygonMode (GL.GL_BACK, GL2.GL_LINE);        
        
        //setup the 4 look at positions 
        lookAts = new int[4][9];
        lookAts[0] = new int[]{10,0,10,0,0,0,0,0,1};
        lookAts[1] = new int[]{10,20,4,0,0,0,0,0,1};
        lookAts[2] = new int[]{0,0,30,0,0,0,0,1,0};
        lookAts[3] = new int[]{0,20,10,0,0,0,0,0,1};
        
        
        //Generate lists
        liBerry = gl2.glGenLists(1);
        gl2.glNewList(liBerry, GL2.GL_COMPILE);
        initSphere(gl2, 2f);
        gl2.glEndList();
        
        liCake = gl2.glGenLists(1);
        gl2.glNewList(liCake, GL2.GL_COMPILE);
        initCylinder(gl2, 6f, 4f,new Triple(38,18,7),new Triple(92,66,31),4);
        gl2.glEndList();
        
        liBottom = gl2.glGenLists(1); 
        gl2.glNewList(liBottom, GL2.GL_COMPILE);
        initCylinder(gl2, 6.8f,.201f,new Triple(39,29,14), new Triple(97,71,34),1); 
        gl2.glEndList(); 
        
        liBottomPts = gl2.glGenLists(1); 
        gl2.glNewList(liBottomPts, GL2.GL_COMPILE);
        gl2.glScalef(.20f , .20f , .20f); 
        initBottomPts(gl2); 
        gl2.glEndList(); 
        
               
        
        liCandle = gl2.glGenLists(1);
        gl2.glNewList(liCandle, GL2.GL_COMPILE);
        initCylinder(gl2, .25f, 3f,new Triple(183,178,96),new Triple(220,215,145), 0);
        gl2.glPushMatrix();
        gl2.glBegin(GL.GL_LINES);
        gl2.glColor3f(1f,1f,1f);
        gl2.glLineWidth(4f);
        gl2.glVertex3f(0f, 0f, 3f);
        gl2.glVertex3f(0f, 0f, 3.25f);
        gl2.glEnd();
        gl2.glPopMatrix();
        gl2.glEndList();
        
        liFrosting= gl2.glGenLists(1) ;
        gl2.glNewList(liFrosting, GL2.GL_COMPILE);
        initCylinder(gl2, 1.0f, .3f, new Triple(100, 100, 100), new Triple(210,210,210),0);
        gl2.glEndList();
        
        liTopping = gl2.glGenLists(1);
        gl2.glNewList(liTopping, GL2.GL_COMPILE);
        gl2.glPushMatrix();
        gl2.glScalef(.35f, .35f, .35f);
        gl2.glCallList(liBerry);
        gl2.glPopMatrix();
        gl2.glCallList(liFrosting);
        gl2.glEndList();
        
        setUpCoordFrames(gl2);
    }
    
    private static void setUpCoordFrames(GL2 gl2){
        //initialize all coord frames
        gl2.glPushMatrix();
        cfToppings = new double[8][16];
        cfCake = new double[16];
        cfCandle = new double[16];
        cfBottomPts = new double[14][16]; 
        cfBottom= new double[16];
        gl2.glLoadIdentity();
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, cfBottom,0);
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, cfCake,0);
        for(int i =0; i < cfToppings.length; i++){
            gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, cfToppings[i],0);
        }
        for(int i =0; i < cfBottomPts.length; i++){
            gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, cfBottomPts[i],0);
        }        
        gl2.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, cfCandle,0);
        gl2.glPopMatrix();
       
        localTranslate(gl2, cfCake, new Triple(0f,0f,4f));
        printCoords(cfCake);
        localRotate(gl2, -60f, cfCake, new Triple(1f,0f,0f));
        printCoords(cfCake);
        localTranslate(gl2, cfCake, new Triple(0f,8f,0f));
        printCoords(cfCake);
        
        //globalTranslate(gl2, cfCandle, new Triple(0f,0f,4f));//candle goes in the the middle and on top of the cake
        //globalTranslate(gl2, cfBottom, new Triple(0f,0f,-.2f));
        
        for(int i = 0; i < cfToppings.length; i++){
            globalTranslate(gl2,cfToppings[i],new Triple(3f,3f,4f));
            float degrees = (360f/cfToppings.length)*i;
            System.out.println(degrees);
            globalRotate(gl2, degrees, cfToppings[i], new Triple(0f,0f,1f));//rotate to populate the cake with berries!
            localRotate(gl2, -degrees, cfToppings[i], new Triple(0f,0f,1f));//rotate the berries so that the shading is fixed
        }
        
         for(int i = 0; i < cfBottomPts.length; i++){
            globalTranslate(gl2,cfBottomPts[i],new Triple(6.6f,0f,0f));
            localRotate(gl2, 170f, cfBottomPts[i], new Triple(0f,0f,1f));//rotate 
            //localRotate(gl2, 180f, cfBottomPts[i], new Triple(0f,1f,0f));//rotate 
            float degrees = (360f/cfBottomPts.length)*i;
            System.out.println(degrees);
            globalRotate(gl2, degrees, cfBottomPts[i], new Triple(0f,0f,1f));//rotate to populate the bottom with tabs 
            
        }       
               
        
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
    private static float findRadius(float z, float R){
        //find the radii by using the pythagorean theorem
        float zDist = Math.abs(R - z);
        return (float)Math.pow(Math.pow(R, 2.0)-Math.pow(zDist,2.0),0.5);
    }
    
    
  
    protected static void initBottomPts(GL2 gl){
        //bottom 
        gl.glBegin( GL2.GL_QUADS);  
            gl.glColor3b( (byte)21,(byte)15,(byte)8);
            gl.glVertex3f( 0, 3, -1f );
            gl.glVertex3f( -3, 3,-1f );
            gl.glVertex3f( -3, 6, -1f );
            gl.glVertex3f( 0, 6, -1f);
        gl.glEnd(); 
        //bottom     
        gl.glBegin( GL.GL_TRIANGLES);       
            gl.glColor3b( (byte)21,(byte)15,(byte)8);
            gl.glVertex3f(0, 0, -1f);
            gl.glVertex3f( -3, 3, -1f);
            gl.glVertex3f( 0, 3, -1f);
            gl.glVertex3f( 0, 6, -1f);
            gl.glVertex3f( -3, 6, -1f);
            gl.glVertex3f( 0, 9, -1f);
        gl.glEnd();
        //top 
        gl.glBegin( GL2.GL_QUADS);  
            gl.glColor3b( (byte)52,(byte)38,(byte)19);
            gl.glVertex3f( 0, 6, 0);
            gl.glVertex3f( -3, 6, 0);    
            gl.glVertex3f( -3, 3, 0);
            gl.glVertex3f( 0, 3, 0);           
        gl.glEnd(); 
        //top     
        gl.glBegin( GL.GL_TRIANGLES);       
            gl.glColor3b( (byte)52,(byte)38,(byte)19);
            
            gl.glVertex3f( 0, 9, 0);
            gl.glVertex3f( -3, 6, 0);
            gl.glVertex3f( 0, 6, 0);
            gl.glVertex3f( 0, 3, 0);
            gl.glVertex3f( -3, 3, 0);
            gl.glVertex3f(0, 0, 0); 
        gl.glEnd();
        
        //left side 
        gl.glBegin( GL2.GL_QUADS);  
           gl.glColor3b( (byte)39,(byte)29,(byte)14);
            gl.glVertex3f( -3, 3, 0);
            gl.glVertex3f( -3, 3, -1f);
            gl.glVertex3f( 0, 0, -1f );
            gl.glVertex3f( 0, 0, 0);
                                     
        gl.glEnd(); 
        
        //center 
        gl.glBegin( GL2.GL_QUADS);  
            gl.glColor3b( (byte)39,(byte)29,(byte)14);            
            gl.glVertex3f( -3, 6, 0);
            gl.glVertex3f( -3, 6, -1f);
            gl.glVertex3f( -3, 3, -1f); 
            gl.glVertex3f( -3, 3, 0);
         
        gl.glEnd(); 
                
        //right side 
        gl.glBegin( GL2.GL_QUADS);  
            gl.glColor3b( (byte)39,(byte)29,(byte)14);
           
            gl.glVertex3f( 0, 9, 0);
            gl.glVertex3f( 0, 9, -1f);
            gl.glVertex3f( -3, 6, -1f); 
            gl.glVertex3f( -3, 6, 0f);
              
        gl.glEnd(); 
    }
    /**
     * Creates a sphere with a top color of (145, 25,27)
     * and a bottom color of (103,21,27)
     */
    protected static void initSphere(GL2 gl, float r){
        gl.glBegin(GL2.GL_QUAD_STRIP);
        float zScale = r / 10f;
        double rScale = 42.0 / 10.0;
        double gScale = 4.0/10.0;
        double bScale = 0;
        int oldR = -1;
        int oldG = -1;
        int oldB = -1;
        for(int k = 0; k < 20; k++){
            float z1 = (k+1)*zScale;
            float z2 = k*zScale;
            float r1 = findRadius(z1,r);
            float r2 = findRadius(z2,r);
            int red = (int)(rScale * k + 103);
            int green = (int)(gScale * k + 21);
            int blue = 27;
            if(oldR == -1){
                oldR = red;
                oldG = green;
                oldB = blue;
            }
            for(int i=0; i <= 30; i++){
                double theta = i/30.0*2*Math.PI;
                float cosine = (float)Math.cos(theta); 
                float sine = (float)Math.sin(theta);
                float x = r1 * cosine;
                float y = r1 * sine;
                
                gl.glColor3ub((byte)(red+i%15*4), (byte)(green+i%15*4),(byte)(blue+i%15*4));
                gl.glVertex3f(x,y,z1);
                x = (r2) * cosine;
                y = (r2) * sine;
                gl.glColor3ub((byte)(oldR+i%15*4), (byte)(oldG+i%15*4),(byte)(oldB+i%15*4));
                gl.glVertex3f(x,y,z2);
            }
            oldR = red;
            oldG = green;
            oldB = blue;
        }
        gl.glEnd();
    }
    /**
     * Creates a cylinder, listID must be a valid list ie. listID = gl.glGenLists(1) must be called before using it
     * @param gl
     * @param r
     * @param h 
     */
    protected static void initCylinder(GL2 gl, float r, float h, Triple<Integer> startColor, Triple<Integer> endColor, int colorFactor){
        gl.glBegin(GL2.GL_QUAD_STRIP);
        float zScale = h / 20f;
        double rScale = (endColor.R()-startColor.R())/20.0;
        double gScale = (endColor.G()-startColor.G())/20.0;
        double bScale = (endColor.B()-startColor.B())/20.0;
        int oldR = -1;
        int oldG = -1;
        int oldB = -1;
        for(int k = 0; k < 20; k++){
            float z1 = (k+1)*zScale;
            float z2 = k*zScale;
            int red = (int)(rScale * k + startColor.R());
            int green = (int)(gScale * k + startColor.G());
            int blue = (int)(bScale * k + startColor.B());
            if(oldR==-1){
                oldR = red;
                oldG = green;
                oldB = blue;
            }
            for(int i=0; i <= 30; i++){
                double theta = i/30.0*2*Math.PI;
                float cosine = (float)Math.cos(theta); 
                float sine = (float)Math.sin(theta);
                float x = r * cosine;
                float y = r * sine;
              
                gl.glColor3ub((byte)(red+i%15*colorFactor), (byte)(green+i%15*colorFactor),(byte)(blue+i%15*colorFactor));
                gl.glVertex3f(x,y,z1);
                gl.glColor3ub((byte)(oldR+i%15*colorFactor), (byte)(oldG+i%15*colorFactor),(byte)(oldB+i%15*colorFactor));
                gl.glVertex3f(x,y,z2);
            }
            if(k==0 || k ==19){//use triangle fan for the top and bottom
                float z = 0f;
                if(k==19){
                    z= z1;
                }
                gl.glEnd();
                gl.glBegin(GL2.GL_TRIANGLE_FAN);
                gl.glVertex3f(0,0,z);
                for(int i=0; i <= 30; i++){
                    int angleFactor = 2;
                    if(k==0)
                        angleFactor = -angleFactor;//need this to draw the bottom in a CC rotation so that it is facing out
                    double theta = i/30.0*angleFactor*Math.PI;
                    float cosine = (float)Math.cos(theta); 
                    float sine = (float)Math.sin(theta);
                    float x = r * cosine;
                    float y = r * sine;
              
                    gl.glColor3ub((byte)(red+i%15*(colorFactor*.3)), (byte)(green+i%15*(colorFactor*.3)),(byte)(blue+i%15*(colorFactor*.3)));
                    gl.glVertex3f(x,y,z);
                }
                gl.glEnd();
                gl.glBegin(GL2.GL_QUAD_STRIP);//return to quad strips
            }
            oldR = red;
            oldG = green;
            oldB = blue;
        }
        gl.glEnd();        
    }
    protected static void render( GL2 gl ) {
        gl.glPolygonMode (GL.GL_FRONT, fillMode);
        //have to do this here so that the lookat updates when the view is changed with a keypress
        gl.glMatrixMode( GL2.GL_MODELVIEW );
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluLookAt(  lookAts[sceneNum][0], lookAts[sceneNum][1], lookAts[sceneNum][2],  //eye position x,y,z
                        lookAts[sceneNum][3],  lookAts[sceneNum][4], lookAts[sceneNum][5],   //focus x,y,z
                        lookAts[sceneNum][6],  lookAts[sceneNum][7], lookAts[sceneNum][8]);//camera up x,y,z
        
        
        gl.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        
      
        //bottom points-------------------------------
        for(int i = 0; i < cfBottomPts.length; i++){
            gl.glPushMatrix();
            gl.glMultMatrixd(cfBottomPts[i], 0);
            gl.glCallList(liBottomPts);
            gl.glPopMatrix();
        }
        
        
        //CakeBottom-------------------------------
        gl.glPushMatrix(); 
        gl.glMultMatrixd(cfBottom, 0); 
        gl.glCallList(liBottom); 
        gl.glPopMatrix(); 
        
        //Cake-------------------------------------
        gl.glPushMatrix();
        gl.glMultMatrixd(cfCake, 0);
        gl.glCallList(liCake);
        gl.glPopMatrix();
        
        /**
         * Toppings-------------------------------
         */
        for(int i = 0; i < cfToppings.length; i++){
            gl.glPushMatrix();
            gl.glMultMatrixd(cfToppings[i], 0);
            gl.glCallList(liTopping);
            gl.glPopMatrix();
        }
        
        /**
         * Candle---------------------------------
         */
        gl.glPushMatrix();
        gl.glMultMatrixd(cfCandle, 0);
        //gl.glTranslatef(0f, 0f, 4f);
        gl.glCallList(liCandle);
        gl.glPopMatrix();
        
        gl.glFlush();
    }

    private static void printCoords(double[] cfCake) {
        System.out.println("{");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(cfCake[(4*i)+j]+", ");
            }
                System.out.println();
        }
        System.out.println("}");
    }
}
