/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg367proj1;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.*;
//import javax.media.opengl.glu.GLU;
import javax.vecmath.Matrix4f;

/**
 *
 * @author warnecam
 */
public class Teapot extends SceneObj{

    public Teapot(double size, Triple<Float> color){
        this.size = size;
        cf = new Matrix4f();
        cf.setIdentity();
        this.color = color;
        
    }
    
    @Override
    public void draw(GL2 gl){
        gl.glPushMatrix();
        gl.glMultMatrixf(Test.cyl.getCFf(), 0);
        gl.glMultMatrixf(this.getCFf(), 0);
        GLUT glut = new GLUT();
        gl.glColor3f(color.R(), color.G(), color.B());
        glut.glutSolidTeapot(size,false);
        gl.glPopMatrix();
    }
    
    
    public static void initMaterial(GL2 gl){
        /* define material properties */
        float[] material_ambient = new float[]{0.25f, 0.25f, 0.25f};
        float[] material_diffuse = new float[]{0.90f, 0.90f, 0.90f, 0};
        float[] material_specular = new float[]{0.90f, 0.90f, 0.90f, 0};
        float material_shininess = 25.0f;

        /* load material properties */
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, material_ambient, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, material_diffuse, 0);
        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, material_specular, 0);
        gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, material_shininess);
    }
    
}
