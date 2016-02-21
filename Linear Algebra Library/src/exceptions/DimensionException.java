package exceptions;

public class DimensionException extends Exception {
	
	private static final long serialVersionUID = -6748869184634842519L;

	public DimensionException(){
		super("Error: The dimensions of the object involved do not coincide.");
	}
}
