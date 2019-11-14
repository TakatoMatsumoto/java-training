package ch02.ex13;

public class Vehicle {
	private double velocity;
	private double direction;
	private String ownerName = "<unnamed>";
	private static int nextID;
	private int ID;
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
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
