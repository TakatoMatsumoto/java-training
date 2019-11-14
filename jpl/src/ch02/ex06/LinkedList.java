package ch02.ex06;

public class LinkedList {
	public Object obj;
	public LinkedList next = null;

	public void Linkedlist(Object obj, LinkedList next) {
		this.obj = obj;
		this.next = next;
	}

	public static void main(String[] args) {
		Vehicle benz = new Vehicle();
		Vehicle bmw = new Vehicle();

		LinkedList node_1 = new LinkedList();
		LinkedList node_2 = new LinkedList();

		node_1.obj = benz;
		node_1.next = node_2;
		node_2.obj = bmw;
		node_2.next = null;

		benz.velocity = 100;
		benz.direction = 60;
		benz.ownerName = "John";
		benz.ID = Vehicle.nextID++;

		bmw.velocity = 120;
		bmw.direction = 180;
		bmw.ownerName = "Tom";
		bmw.ID = Vehicle.nextID++;

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
