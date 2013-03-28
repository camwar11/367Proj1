/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj1;

import android.content.Context;

import static android.opengl.GLES10.glTranslatef;
import static android.opengl.GLES11.*;
import android.opengl.Matrix;
import android.util.Log;

import javax.microedition.khronos.opengles.GL11;

import edu.gvsu.cis.proj2.SceneObj;
import edu.gvsu.cis.proj3.MeshObject;

/**
 *This is a cake, the texture is not very good though
 * @author Cam Warner, Andrew Zimny, Eric Munson
 */
public class Cake extends SceneObj{
	public static MeshObject meshObj = null;
	private static int count = 0;
    public Cake(Context context, double size){//, Triple<Float> color){
        this.size = size;
        Matrix.setIdentityM(cf, 0);
        this.color = color;
        if(meshObj==null) //only load the meshObj if this is the first call
        {
        	meshObj = new MeshObject(context, "cake_normals.obj", true);
        	Log.d("TEST", Integer.toString(count++));
        }
    }
    @Override
    public void draw(Object... objects){
        glPushMatrix();
        glTranslatef(0, 0, 0.5f);
        glScalef(0.1f, 0.1f, 0.1f);
        //glColor3f(color.R(), color.G(), color.B());
        glMultMatrixf(cf, 0);
        Cake.meshObj.draw(null);
        glPopMatrix();
    }
}
