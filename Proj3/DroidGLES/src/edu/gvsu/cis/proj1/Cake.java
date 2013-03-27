/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj1;

import android.content.Context;

import static android.opengl.GLES10.glMultMatrixf;
import static android.opengl.GLES10.glPopMatrix;
import static android.opengl.GLES10.glPushMatrix;
import static android.opengl.GLES11.*;
import android.opengl.Matrix;

import javax.microedition.khronos.opengles.GL11;

import edu.gvsu.cis.proj2.SceneObj;
import edu.gvsu.cis.proj2.Test;
import edu.gvsu.cis.proj3.MeshObject;

/**
 *
 * @author Cam Warner, Andrew Zimny, Eric Munson
 */
public class Cake extends SceneObj{
	private MeshObject meshObj;
    public Cake(Context context, double size){//, Triple<Float> color){
        this.size = size;
        Matrix.setIdentityM(cf, 0);
        this.color = color;
        meshObj = new MeshObject(context, "cake.off");
    }
    public void draw(){
        glPushMatrix();
        glMultMatrixf(this.getCF(), 0);
        //glColor3f(color.R(), color.G(), color.B());
        meshObj.draw(null);
        glPopMatrix();
    }
}
