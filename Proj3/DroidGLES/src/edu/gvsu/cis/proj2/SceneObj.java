/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj2;

import edu.gvsu.cis.proj3.Drawable;
import android.opengl.*;

/**
 * Base object of a scene that other objects inherit from
 * @author warnecam
 */
public abstract class SceneObj implements Drawable{
    protected float[] cf = new float[16];
    protected double size;
    protected float[] color = new float[3];
    
    @Override
    public abstract void draw(Object... objects);
    
    public void rotateX(float rot){
        Matrix.rotateM(cf, 0, rot, 1f, 0f, 0f);
    }
    public void rotateY(float rot){
    	Matrix.rotateM(cf, 0, rot, 0f, 1f, 0f);
    }
    public void rotateZ(float rot){
    	Matrix.rotateM(cf, 0, rot, 0f, 0f, 1f);
    }
    
    void globalTranslate(float x, float y, float z) {
    	throw new UnsupportedOperationException("Not supported yet.");
    }
    public void localTranslate(float x, float y, float z) {
        Matrix.translateM(cf, 0, x, y, z);
    }
    
    public float[] getCF(){
        return cf;
    }
    
    public float getColR(){
    	return color[0];
    }
    public float getColG(){
    	return color[1];
    }
    public float getColB(){
    	return color[2];
    }
    
    public void setColor(float r, float g, float b){
    	color[0] = r;
    	color[1] = g;
    	color[2] = b;
    }
    
}
