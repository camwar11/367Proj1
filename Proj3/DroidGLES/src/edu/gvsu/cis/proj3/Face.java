package edu.gvsu.cis.proj3;

import java.util.ArrayList;

public class Face {
	ArrayList<Vector3D> vertices, uvws, normals;
	public Face(){
		vertices = new ArrayList<Vector3D>();
		uvws = new ArrayList<Vector3D>();
		normals = new ArrayList<Vector3D>();
	}
	
	public ArrayList<Vector3D> getVertices() {
		return vertices;
	}

	public ArrayList<Vector3D> getUvws() {
		return uvws;
	}

	public ArrayList<Vector3D> getNormals() {
		return normals;
	}

}
