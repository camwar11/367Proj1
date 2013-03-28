/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author warnecam
 */
public class Test {

    static Cylinder cyl;
    static boolean light0=true;
    static boolean light1=true;
    static Teapots teapots;
    static GL2 myGL;
    
    public static void main(String[] args) {
        teapots = new Teapots();
        setupTeapots();

        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        final GLCanvas glcanvas = new GLCanvas(glcapabilities);
        Helper.glcanvas = glcanvas;
        glcanvas.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                System.out.println("Typed: " + key);
                if (key == 'n') {
                    Helper.sceneNum = (Helper.sceneNum + 1) % 4;
                    System.out.println("Next Scene: " + Helper.sceneNum);
                } else if (key == 'p') {
                    if (Helper.sceneNum == 0) {
                        Helper.sceneNum = 4;
                    }
                    Helper.sceneNum = (Helper.sceneNum - 1) % 4;
                    System.out.println("Prev Scene: " + Helper.sceneNum);
                } else if (key == 'f') {
                    if(Helper.fillMode==GL2.GL_FILL)
                        Helper.fillMode = GL2.GL_LINE;
                    else
                        Helper.fillMode = GL2.GL_FILL;
                } else if(key == 'w'){
                  //cyl.localTranslate(1f);
                } else if(key == 'a'){
                  cyl.rotateX(1f);
                } else if(key == 's'){
                  cyl.localTranslate(myGL, new Triple<Float>(-1f,0f,0f));
                } else if(key == 'd'){
                  cyl.rotateX(-1f);
                } else if(key == 'q'){
                    Helper.rotating = !Helper.rotating; 
                } else if(key == 'e'){
                    cyl.moving = !cyl.moving;
                }else if(key == 'k'){
                        light0=true;
                    }else if(key == 'l'){
                       light0=false;
                    }else if(key == 'h'){
                        light1=true;
                    }else if(key == 'j'){
                       light1=false;
                    }
                
                glcanvas.display();
            }


            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        glcanvas.addGLEventListener(new GLEventListener() {
            @Override
            public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {
                Helper.setup(glautodrawable.getGL().getGL2(), width, height);
                cyl.draw(glautodrawable.getGL().getGL2());
                teapots.draw(glautodrawable.getGL().getGL2());
            }

            @Override
            public void init(GLAutoDrawable glautodrawable) {
                GL2 gl2 = glautodrawable.getGL().getGL2();
                myGL = gl2;
                Helper.init(gl2);
                //CubeSide2.init(glautodrawable.getGL().getGL2());
                cyl = new Cylinder(gl2, 50, 4, new Triple(1f, 0.4f, 0.4f));
                cyl.draw(gl2);
                initTeapots(gl2);
                Teapot.initMaterial(gl2);
                initLights(gl2);
                teapots.draw(gl2);
            }

            @Override
            public void dispose(GLAutoDrawable glautodrawable) {
            }

            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                if (light0 == false) {
                    glautodrawable.getGL().getGL2().glDisable(GL2.GL_LIGHT0);
                }
                if (light1 == false) {
                    glautodrawable.getGL().getGL2().glDisable(GL2.GL_LIGHT1);
                }
                if (light0 == true) {
                    glautodrawable.getGL().getGL2().glEnable(GL2.GL_LIGHT0);
                }
                if (light1 == true) {
                    glautodrawable.getGL().getGL2().glEnable(GL2.GL_LIGHT1);
                }
                Helper.render( glautodrawable.getGL().getGL2());
                cyl.draw(glautodrawable.getGL().getGL2());
                teapots.draw(glautodrawable.getGL().getGL2());
            }
        });

        Frame frame = new Frame("Portal Scene");
        frame.add(glcanvas);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                System.exit(0);
            }
        });

        frame.setSize(640, 480);
        frame.setVisible(true);
    }

    private static void setupTeapots() {
        Random rand = new Random();
        for(int i = 0; i < 4; i++){
            teapots.add(new Teapot(10.0, new Triple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat())));
            
        }   
        
    }
    private static void initTeapots(GL2 gl2) {
        teapots.get(0).rotateZ(0f);
        teapots.get(0).globalTranslate(gl2, new Triple<Float>(0f, 35f, 2f));
        
        teapots.get(1).rotateZ(90f);
        teapots.get(1).globalTranslate(gl2, new Triple<Float>(0f, -35f, 2f));
        
        teapots.get(2).rotateZ(180f);
        teapots.get(2).globalTranslate(gl2, new Triple<Float>(35f, 0f, 2f));
        
        teapots.get(3).rotateZ(270f);
        teapots.get(3).globalTranslate(gl2, new Triple<Float>(-35f, 0f, 2f));
        
        
    }
        
    public static void initLights(GL2 gl) {
        /* define light properties */
        float light0_diffuse[] = {1.0f, 0.0f, 0.0f, 1.0f};
        float light0_position[] = {-1.0f, 0.0f, 1.0f, 0.0f};
        float light1_diffuse[] = {0.0f, 0.0f, 1.0f, 1.0f};
        float light1_position[] = {1.0f, 0.0f, 1.0f, 0.0f};

        /* enable lights 0 and 1*/
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);

        /* load light properties */
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light0_diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light0_position, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, light1_diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, light1_position, 0);
    }

}
