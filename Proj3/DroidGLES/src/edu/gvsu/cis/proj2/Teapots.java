/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gvsu.cis.proj2;

import java.util.ArrayList;

/**
 *
 * @author warnecam
 */
public class Teapots extends SceneObj{
    ArrayList<Chalice> teapots;
    
    public Teapots(){
        teapots = new ArrayList<Chalice>();
    }
    
    public void add(Chalice pot){
        teapots.add(pot);
    }
    
    public void remove(Chalice pot){
        teapots.remove(pot);
    }
    
    @Override
    public void draw(Object... objects){
        for(Chalice teapot : teapots){
            teapot.draw();
        }
    }
    public Chalice get(int pos){
        return teapots.get(pos);
    }

    @Override
    public void rotateX(float rot) {
        for(Chalice teapot : teapots){
            teapot.rotateX(rot);
        }
    }

    @Override
    public void rotateY(float rot) {
        for(Chalice teapot : teapots){
            teapot.rotateY(rot);
        }
    }

    @Override
    public void rotateZ(float rot) {
        for(Chalice teapot : teapots){
            teapot.rotateZ(rot);
        }
    }
    
    
}
