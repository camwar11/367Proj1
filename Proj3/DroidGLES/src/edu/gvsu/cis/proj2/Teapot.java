/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj2;

import javax.microedition.khronos.opengles.GL11;
import static android.opengl.GLES11.*;
import edu.gvsu.cis.proj3.MeshObject;

import android.content.Context;
import android.opengl.*;

/**
 *
 * @author warnecam
 */
public class Teapot extends SceneObj{
	private MeshObject meshObj;
    public Teapot(Context context, double size){//, Triple<Float> color){
        this.size = size;
        Matrix.setIdentityM(cf, 0);
        this.color = color;
        meshObj = new MeshObject(context, "chalice.off");
    }
    
    @Override
    public void draw(){
        glPushMatrix();
        glMultMatrixf(Test.cyl.getCF(), 0);
        glMultMatrixf(this.getCF(), 0);
        //glColor3f(color.R(), color.G(), color.B());
        meshObj.draw(null);
        glPopMatrix();
    }
    
    
    public static void initMaterial(GL11 gl){
        /* define material properties */
        float[] material_ambient = new float[]{0.25f, 0.25f, 0.25f};
        float[] material_diffuse = new float[]{0.90f, 0.90f, 0.90f, 0};
        float[] material_specular = new float[]{0.90f, 0.90f, 0.90f, 0};
        float material_shininess = 25.0f;

        /* load material properties */
        gl.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, material_ambient, 0);
        gl.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, material_diffuse, 0);
        gl.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, material_specular, 0);
        gl.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, material_shininess);
    }
    
}
