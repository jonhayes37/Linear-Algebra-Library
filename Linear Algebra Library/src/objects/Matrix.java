package objects;

import java.util.ArrayList;

import exceptions.DimensionException;

public class Matrix {
	private ArrayList<ArrayList<Double>> components;
	public static final Matrix IDENTITY_2X2 = new Matrix(new double[][]{{1.0,0.0},{0.0,1.0}});
	public static final Matrix IDENTITY_3X3 = new Matrix(new double[][]{{1.0,0.0,0.0},{0.0,1.0,0.0},{0.0,0.0,1.0}});
	
	// Default constructor - 3x3 identity
	public Matrix(){
		this.components = IDENTITY_3X3.components;
	}
	
	// Initialize a Matrix with # rows, cols
	public Matrix(int rows, int cols){
		this.components = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < rows; i++){
			ArrayList<Double> row = new ArrayList<Double>();
			for (int j = 0; j < cols; j++){
				row.add(0.0);
			}
			this.components.add(row);
		}
	}
	
	// Standard constructors
	public Matrix(int[][] vals){
		this.components = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < vals.length; i++){
			ArrayList<Double> row = new ArrayList<Double>();
			for (int j = 0; j < vals[i].length; j++){
				row.add((double)vals[i][j]);
			}
			this.components.add(row);
		}
	}
	
	public Matrix(float[][] vals){
		this.components = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < vals.length; i++){
			ArrayList<Double> row = new ArrayList<Double>();
			for (int j = 0; j < vals[i].length; j++){
				row.add((double)vals[i][j]);
			}
			this.components.add(row);
		}
	}
	
	public Matrix(double[][] vals){
		this.components = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < vals.length; i++){
			ArrayList<Double> row = new ArrayList<Double>();
			for (int j = 0; j < vals[i].length; j++){
				row.add(vals[i][j]);
			}
			this.components.add(row);
		}
	}
	
	// ----- Operations on Matrices ----- //
	public void SetComponent(int row, int col, double val) throws DimensionException{
		if (row > this.components.size() - 1 || col > this.components.get(0).size()){
			throw new DimensionException();
		}
		this.components.get(row).set(col, val);
	}
	
	// Add
	public Matrix Add(Matrix m2) throws DimensionException{
		if (m2.components.size() != this.components.size() || this.components.get(0).size() != m2.components.get(0).size()){
			throw new DimensionException();
		}
		Matrix resultant = new Matrix(this.components.size(), this.components.get(0).size());
		for (int i = 0; i < this.components.size(); i++){
			for (int j = 0; j < this.components.get(0).size(); j++){
				resultant.SetComponent(i, j, this.components.get(i).get(j) + m2.components.get(i).get(j));
			}
		}
		return resultant;
	}
	
	// Scale
	public Matrix Scale(double factor){
		Matrix resultant = new Matrix(this.components.size(), this.components.get(0).size());
		for (int i = 0; i < this.components.size(); i++){
			for (int j = 0; j < this.components.get(0).size(); j++){
				try {
					resultant.SetComponent(i, j, this.components.get(i).get(j) * factor);
				} catch (DimensionException e) { e.printStackTrace(); }
			}
		}
		return resultant;
	}
	
	// Multiply
	
	
	// Trace
	public double Trace(){
		double sum = 0;
		for (int i = 0; i < Math.min(this.components.size(), this.components.get(0).size()); i++){
			sum += this.components.get(i).get(i);
		}
		return sum;
	}
	
	// Determinant
	public double Determinant() throws DimensionException{
		if (this.components.size() != this.components.get(0).size()){
			throw new DimensionException();
		}
		double sum = 0;
		if (this.components.size() == 2){
			sum = this.components.get(0).get(0) * this.components.get(1).get(1) - this.components.get(1).get(0) * this.components.get(0).get(1);
		}else if (this.components.size() == 3){
			sum = this.components.get(0).get(0) * this.components.get(1).get(1) * this.components.get(2).get(2) + this.components.get(0).get(1) * this.components.get(1).get(2) * this.components.get(2).get(0) +
					this.components.get(0).get(2) * this.components.get(1).get(0) * this.components.get(2).get(1) - this.components.get(0).get(2) * this.components.get(1).get(1) * this.components.get(2).get(0) - 
					this.components.get(0).get(1) * this.components.get(1).get(0) * this.components.get(2).get(2) - this.components.get(0).get(0) * this.components.get(1).get(2) * this.components.get(2).get(1);
		}else{
			sum = -1;
		}
		return sum;
	}
}
