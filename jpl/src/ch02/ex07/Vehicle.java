package ch02.ex07;

public class Vehicle {
	public int velocity;
	public int direction;
	public String ownerName;
	public static int nextID;
	public int ID;
	
	public Vehicle(){
		ID = nextID++;
	}
	
	public Vehicle(String name){
		this();
		ownerName = name;
	}
	
	public static void main(String[] args) {
		Vehicle benz = new Vehicle("John");
		benz.velocity = 100;
		benz.direction = 60;
		
		Vehicle bmw = new Vehicle("Tom");
		bmw.velocity = 120;
		bmw.direction = 180;

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
