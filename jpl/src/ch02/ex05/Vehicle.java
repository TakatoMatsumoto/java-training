package ch02.ex05;

public class Vehicle {
	public int velocity;
	public int direction;
	public String ownerName;
	public static int nextID;
	public int ID;
	public static void main(String[] args) {
		Vehicle benz = new Vehicle();
		benz.velocity = 100;
		benz.direction = 60;
		benz.ownerName = "John";
		benz.ID = nextID++;
		
		Vehicle bmw = new Vehicle();
		bmw.velocity = 120;
		bmw.direction = 180;
		bmw.ownerName = "Tom";
		bmw.ID = nextID++;
		
		System.out.println("OwnerName: " + benz.ownerName);
		System.out.println("ID: " + benz.ID);
		System.out.println("Velocity: " + benz.velocity);
		System.out.println("Direction: " + benz.direction);
		
		System.out.println("OwnerName: " + bmw.ownerName);
		System.out.println("ID: " + bmw.ID);
		System.out.println("Velocity: " + bmw.velocity);
		System.out.println("Direction: " + bmw.direction);
	}
}
