package edu.gvsu.cis.proj3;

import static android.opengl.GLES10.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.content.Context;
/**
 * Mesh Object converter taken from Dr. Dulimarta's example
 * @author dulimarh
 *
 */
public class MeshObject implements Drawable {
    private ModelScanner obj;
    private FloatBuffer mVertex, mNormal, mTex;
    private ShortBuffer mIndex;
    private boolean hasNorms = false;
    
    public MeshObject(Context c, String filename, boolean isObjFile)
    {
    	if(isObjFile)
    		obj = new ObjScanner(c);
    	else
    		obj = new OffScanner(c);
    	
        obj.read(filename);
        
        ByteBuffer buff;
        
        buff = ByteBuffer.allocateDirect(4 * obj.getVertexArray().length);
        buff.order(ByteOrder.nativeOrder());
        mVertex = buff.asFloatBuffer();
        mVertex.put(obj.getVertexArray());
        
        if(obj.getNormalArray() != null){
        	hasNorms = true;
        buff = ByteBuffer.allocateDirect(4 * obj.getNormalArray().length);
        buff.order(ByteOrder.nativeOrder());
        mNormal = buff.asFloatBuffer();
        mNormal.put(obj.getNormalArray());
        }
        
        buff = ByteBuffer.allocateDirect(4 * obj.getVertexArray().length * 2 / 3);
        buff.order (ByteOrder.nativeOrder());
        mTex = buff.asFloatBuffer();
        float[] src = obj.getVertexArray();
        for (int k = 0; k < src.length; k++) {
        	if (k % 3 != 2)  /* ignore the Z-value */
        		mTex.put(src[k]);
        }
        
        buff = ByteBuffer.allocateDirect(2 * obj.getIndexArray().length);
        buff.order(ByteOrder.nativeOrder());
        mIndex = buff.asShortBuffer();
        mIndex.put(obj.getIndexArray());
        
        mVertex.position(0);
        if(hasNorms)
        	mNormal.position(0);
        mTex.position(0);
        mIndex.position(0);
    }
    
    @Override
    public void draw(Object ... data)
    {
        glVertexPointer(
                3           /* number of coordinates per vertex */, 
                GL_FLOAT    /* type of each vertex */, 
                0           /* stride */,
                mVertex     /* memory location */);
        if(hasNorms)
        glNormalPointer(
        		GL_FLOAT, 
        		0 /* stride */, 
        		mNormal);
        glTexCoordPointer(2, GL_FLOAT, 0, mTex);
        glDrawElements(
                GL_TRIANGLES, /* mode */ 
                mIndex.capacity()       /* number of elements */, 
                GL_UNSIGNED_SHORT  /* type of each index */, 
                mIndex);
        
    }
}
