package objects;

import java.util.ArrayList;

import exceptions.DimensionException;

public class Vector {
	private ArrayList<Double> coords;
	public static final Vector ZERO_VECTOR_1D = new Vector(new double[]{0.0});
	public static final Vector ZERO_VECTOR_2D = new Vector(new double[]{0.0,0.0});
	public static final Vector ZERO_VECTOR_3D = new Vector(new double[]{0.0,0.0,0.0});
	
	// Default constructor returns the 3D zero vector
	public Vector(){
		this.coords = new ArrayList<Double>();
		coords.add(0.0);
		coords.add(0.0);
		coords.add(0.0);
	}
	
	// Initialize a dim-component vector
	public Vector(int dim){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < dim; i++){
			this.coords.add(0.0);
		}
	}
	
	// Standard constructors
	public Vector(int[] vals){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < vals.length; i++){
			this.coords.add((double)vals[i]);
		}
	}
	
	public Vector(float[] vals){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < vals.length; i++){
			this.coords.add((double)vals[i]);
		}
	}
	
	public Vector(double[] vals){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < vals.length; i++){
			this.coords.add(vals[i]);
		}
	}
	
	// ----- Operations on a Vector ----- //
	
	// Change Component
	public void SetComponent(int index, double val) throws DimensionException{
		if (index > this.coords.size() - 1){
			throw new DimensionException();
		}
		this.coords.set(index, val);
	}
	
	// Scaling
	public Vector Scale(double factor){
		Vector resultant = new Vector(this.coords.size());
		for (int i = 0; i < this.coords.size(); i++){
			resultant.coords.set(i, this.coords.get(i) * factor);
		}
		return resultant;
	}
	
	// Adding
	public Vector Add(Vector v2) throws DimensionException{
		if (v2.coords.size() != this.coords.size()){
			throw new DimensionException();
		}
		Vector resultant = new Vector(this.coords.size());
		for (int i = 0; i < this.coords.size(); i++){
			resultant.coords.set(i, this.coords.get(i) + v2.coords.get(i));
		}
		return resultant;
	}
	
	// Subtracting
	public Vector Subtract(Vector v2) throws DimensionException{
		if (v2.coords.size() != this.coords.size()){
			throw new DimensionException();
		}
		return this.Add(v2.Scale(-1));
	}
	
	// Norm (Magnitude) of the Vector
	public double Norm(){
		if (coords.size() == 1){
			return coords.get(0);
		}else{
			double sqSum = 0;
			for (int i = 0; i < coords.size(); i++){
				sqSum += Math.pow(coords.get(i), 2);
			}
			return Math.sqrt(sqSum);
		}
	}
	
	// Inner Product
	public double InnerProduct(Vector v2) throws DimensionException{
		if (this.coords.size() != v2.coords.size()){
			throw new DimensionException();
		}
		double sum = 0;
		for (int i = 0; i < this.coords.size(); i++){
			sum += (this.coords.get(i) * v2.coords.get(i));
		}
		return sum;
	}
	
	// Outer Product
	public Matrix OuterProduct(Vector v2){
		Matrix result = new Matrix(this.coords.size(), v2.coords.size());
		for (int i = 0; i < this.coords.size(); i++){
			for (int j = 0; j < v2.coords.size(); j++){
				try {
					result.SetComponent(i, j, this.coords.get(i) * v2.coords.get(j));
				} catch (DimensionException e) { e.printStackTrace(); }
			}
		}
		return result;
	}
	
	// Cross Product
	public Vector CrossProduct(Vector v2) throws DimensionException{
		if (this.coords.size() != 3 || v2.coords.size() != 3){
			throw new DimensionException();
		}
		Vector result = new Vector(this.coords.size());
		try {
			result.SetComponent(0, this.coords.get(1) * v2.coords.get(2) - this.coords.get(2) * v2.coords.get(1));
			result.SetComponent(1, this.coords.get(2) * v2.coords.get(0) - this.coords.get(0) * v2.coords.get(2));
			result.SetComponent(2, this.coords.get(0) * v2.coords.get(1) - this.coords.get(1) * v2.coords.get(0));
		} catch (DimensionException e) { e.printStackTrace(); }
		return result;
	}
}
