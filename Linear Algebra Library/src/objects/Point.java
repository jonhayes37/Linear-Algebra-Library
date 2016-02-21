package objects;

import java.util.ArrayList;

import exceptions.DimensionException;

public class Point {
	private ArrayList<Double> coords;
	public static final Point ORIGIN_1D = new Point(new double[]{0.0});
	public static final Point ORIGIN_2D = new Point(new double[]{0.0,0.0});
	public static final Point ORIGIN_3D = new Point(new double[]{0.0,0.0,0.0});
	
	// Default constructor returns the 3D origin
	public Point(){
		this.coords = new ArrayList<Double>();
		coords.add(0.0);
		coords.add(0.0);
		coords.add(0.0);
	}
	
	// Standard constructors
	public Point(int[] vals){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < vals.length; i++){
			this.coords.add((double)vals[i]);
		}
	}
	
	public Point(float[] vals){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < vals.length; i++){
			this.coords.add((double)vals[i]);
		}
	}
	
	public Point(double[] vals){
		this.coords = new ArrayList<Double>();
		for (int i = 0; i < vals.length; i++){
			this.coords.add(vals[i]);
		}
	}
	
	// ----- Operations on a Point ----- //
	
	// Distance from Origin
	public double DistanceFromOrigin(){
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
	
	// Distance From Arbitrary Point
	public double DistanceFromPoint(Point p2) throws DimensionException{
		if (p2.coords.size() != this.coords.size()){
			throw new DimensionException();
		}else{
			double[] newComps = new double[coords.size()];
			for (int i = 0; i < newComps.length; i++){
				newComps[i] = p2.coords.get(i) - this.coords.get(i);
			}
			Point diffPoint = new Point(newComps);
			return diffPoint.DistanceFromOrigin();
		}
	}
	
	// Translate a point an arbitrary distance in each direction
	public void Translate(double[] distances) throws DimensionException{
		if (distances.length != this.coords.size()){
			throw new DimensionException();
		}else{
			for (int i = 0; i < distances.length; i++){
				this.coords.set(i, this.coords.get(i) + distances[i]);
			}
		}
	}
	
	// Converts a 2D point from Cartesian Coordinates to Polar Coordinates
	public void CartesianToPolar() throws DimensionException{
		if (this.coords.size() != 2){
			throw new DimensionException();
		}else{
			double[] newCoords = new double[this.coords.size()];
			newCoords[0] = Math.sqrt(Math.pow(this.coords.get(0), 2) + Math.pow(this.coords.get(1), 2));
			newCoords[1] = Math.atan(this.coords.get(1) / this.coords.get(0));
			this.coords.set(0, newCoords[0]);
			this.coords.set(1, newCoords[1]);
		}
	}
	
	// Converts a 2D point from Polar Coordinates to Cartesian Coordinates
	public void PolarToCartesian() throws DimensionException{
		if (this.coords.size() != 2){
			throw new DimensionException();
		}else{
			double[] newCoords = new double[this.coords.size()];
			newCoords[0] = this.coords.get(0) * Math.cos(this.coords.get(1));
			newCoords[1] = this.coords.get(0) * Math.sin(this.coords.get(1));
			this.coords.set(0, newCoords[0]);
			this.coords.set(1, newCoords[1]);
		}
	}
	
	// Converts a 3D point from Cartesian Coordinates to Spherical Coordinates
	public void CartesianToSpherical() throws DimensionException{
		if (this.coords.size() != 3){
			throw new DimensionException();
		}else{
			double[] newCoords = new double[this.coords.size()];
			newCoords[0] = Math.sqrt(Math.pow(this.coords.get(0), 2) + Math.pow(this.coords.get(1), 2) + Math.pow(this.coords.get(2), 2));
			newCoords[1] = Math.atan(this.coords.get(1) / this.coords.get(0));
			newCoords[2] = Math.atan(Math.sqrt(Math.pow(this.coords.get(0), 2) + Math.pow(this.coords.get(1), 2)) / this.coords.get(2));
			this.coords.set(0, newCoords[0]);
			this.coords.set(1, newCoords[1]);
			this.coords.set(2, newCoords[2]);
		}
	}
	
	// Converts a 3D point from Spherical Coordinates (x,y,z) to Cartesian Coordinates (r,phi,theta)
	public void SphericalToCartesian() throws DimensionException{
		if (this.coords.size() != 3){
			throw new DimensionException();
		}else{
			double[] newCoords = new double[this.coords.size()];
			newCoords[0] = this.coords.get(0) * Math.sin(this.coords.get(2)) * Math.cos(this.coords.get(1));
			newCoords[1] = this.coords.get(0) * Math.sin(this.coords.get(2)) * Math.sin(this.coords.get(1));
			newCoords[2] = this.coords.get(0) * Math.cos(this.coords.get(2));
			this.coords.set(0, newCoords[0]);
			this.coords.set(1, newCoords[1]);
			this.coords.set(2, newCoords[2]);
		}
	}
}
