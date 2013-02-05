/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg367proj1;

/**
 *
 * @author warnecam
 */
public class Triple {
    private int x,y,z;
    public Triple(int first, int second, int third){
        x = first;
        y = second;
        z = third;
    }
    public int X(){
        return x;
    }
    public int Y(){
        return y;
    }
    public int Z(){
        return z;
    }
    public int R(){
        return x;
    }
    public int G(){
        return y;
    }
    public int B(){
        return z;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
    public void setR(int r) {
        this.x = r;
    }

    public void setG(int g) {
        this.y = g;
    }

    public void setB(int b) {
        this.z = b;
    }
}
