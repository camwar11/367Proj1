/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg367proj1;

/**
 *
 * @author warnecam
 */
public class Triple<T> {
    private T x,y,z;
    public Triple(T first, T second, T third){
        x = first;
        y = second;
        z = third;
    }
    public T X(){
        return x;
    }
    public T Y(){
        return y;
    }
    public T Z(){
        return z;
    }
    public T R(){
        return x;
    }
    public T G(){
        return y;
    }
    public T B(){
        return z;
    }
    public void setX(T x) {
        this.x = x;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void setZ(T z) {
        this.z = z;
    }
    public void setR(T r) {
        this.x = r;
    }

    public void setG(T g) {
        this.y = g;
    }

    public void setB(T b) {
        this.z = b;
    }
}
