/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg367proj1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLCanvas;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
/**
 *
 * @author warnecam
 */
public class Test {
        public static void main( String [] args ) {
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );
        glcanvas.addKeyListener(new KeyListener(){

                @Override
                public void keyTyped(KeyEvent e) {
                    char key = e.getKeyChar();
                    System.out.println("Typed: "+key);
                    if(key == 'n'){
                        Cake.sceneNum = (Cake.sceneNum + 1) % 4;
                        System.out.println("Next Scene: "+Cake.sceneNum);
                    }else if(key == 'p'){
                        if(Cake.sceneNum == 0)
                            Cake.sceneNum = 4;
                        Cake.sceneNum = (Cake.sceneNum - 1) % 4;
                        System.out.println("Prev Scene: "+Cake.sceneNum);
                    }else if(key == 'w'){
                        Cake.fillMode = GL2.GL_LINE;
                    }else if(key == 'f'){
                        Cake.fillMode = GL2.GL_FILL;
                    }
                    glcanvas.display();
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
        
        
        
        });
        glcanvas.addGLEventListener( new GLEventListener() {
            
            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
                Cake.setup( glautodrawable.getGL().getGL2(), width, height );
            }
            
            @Override
            public void init( GLAutoDrawable glautodrawable ) {
                Cake.init(glautodrawable.getGL().getGL2());
                CubeSide2.init(glautodrawable.getGL().getGL2());
            }
            
            
            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }
            
            @Override
            public void display( GLAutoDrawable glautodrawable ) {
                Cake.render( glautodrawable.getGL().getGL2());
                CubeSide2.render( glautodrawable.getGL().getGL2());
            }
        });

        Frame frame = new Frame( "Portal Scene" );
        frame.add( glcanvas );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                System.exit( 0 );
            }
        });

        frame.setSize( 640, 480 );
        frame.setVisible( true );
    }
}
