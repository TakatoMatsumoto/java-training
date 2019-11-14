package ch02.ex15;

public class Vehicle {
	private double velocity;
	private double direction;
	private String ownerName = "<unnamed>";
	private static int nextID;
	private int ID;
	public double getVelocity() {
		return velocity;
	}
	public void changeSpeed(double velocity) {
		this.velocity = velocity;
	}
	
	public void stop() {
		this.velocity = 0;
	}
	
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	public String getName() {
		return ownerName;
	}
	public void setName(String name) {
		this.ownerName = name;
	}
	public static int getNextID() {
		return nextID;
	}
	public int getID() {
		return ID;
	}
}
