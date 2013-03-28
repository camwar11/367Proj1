package edu.gvsu.cis.proj3;

public interface ModelScanner {
	public void read(String name);

	public float[] getVertexArray();
	public short[] getIndexArray();
	public float[] getNormalArray();
	
}
