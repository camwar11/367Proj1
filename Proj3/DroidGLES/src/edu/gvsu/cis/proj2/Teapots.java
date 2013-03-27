/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg367proj1;

import java.util.ArrayList;
import javax.media.opengl.GL2;

/**
 *
 * @author warnecam
 */
public class Teapots extends SceneObj{
    ArrayList<Teapot> teapots;
    
    public Teapots(){
        teapots = new ArrayList<Teapot>();
    }
    
    public void add(Teapot pot){
        teapots.add(pot);
    }
    
    public void remove(Teapot pot){
        teapots.remove(pot);
    }
    
    @Override
    public void draw(GL2 gl){
        for(Teapot teapot : teapots){
            teapot.draw(gl);
        }
    }
    public Teapot get(int pos){
        return teapots.get(pos);
    }

    @Override
    public void rotateX(float rot) {
        for(Teapot teapot : teapots){
            teapot.rotateX(rot);
        }
    }

    @Override
    public void rotateY(float rot) {
        for(Teapot teapot : teapots){
            teapot.rotateY(rot);
        }
    }

    @Override
    public void rotateZ(float rot) {
        for(Teapot teapot : teapots){
            teapot.rotateZ(rot);
        }
    }
    
    
}
