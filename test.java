package week6;

import java.time.LocalDate;
import java.util.ArrayList;

public class test {
public static void main(String args[])
	{ArrayList<Animal> AnimalList=new ArrayList<Animal>();
	ArrayList<Customer> CustomerList=new ArrayList<Customer>(); 
    MyAnimalShop x=new MyAnimalShop(300,AnimalList,CustomerList,true,300);
    Animal dog=new Dog("Alex",1,true);
    Animal cat=new Cat("irene",2,true);
    LocalDate date=LocalDate.now();
    x.GetAnimal(dog);
    
    x.GetAnimal(cat);
   
    System.out.println(AnimalList);
    Customer c=new Customer("Lily",2,date);
    x.GetCustomer(c);
    x.closed();   

	}
}

abstract class Animal {
	String AnimalName;
	int age;
	boolean sex;
	double price;

	public Animal(String animalName, int age, boolean sex, double price) {

		AnimalName = animalName;
		this.age = age;
		this.sex = sex;
		this.price = price;
	}

	public abstract String toString();

}

class Dog extends Animal {
	boolean isVaccineInjected;

	public Dog(String animalName, int age, boolean sex) {
		super(animalName, age, sex, 100);
	}

	@Override
	public String toString() {
		return "Dog [isVaccineInjected=" + isVaccineInjected + ", AnimalName=" + AnimalName + ", age=" + age + ", sex="
				+ sex + ", price=" + price + "]";
	}

}

class Cat extends Animal {
	public Cat(String animalName, int age, boolean sex) {
		super(animalName, age, sex, 200);

	}

	@Override
	public String toString() {
		return "Cat [AnimalName=" + AnimalName + ", age=" + age + ", sex=" + sex + ", price=" + price + "]";
	}
}

class Piggy extends Animal {
	public Piggy(String animalName, int age, boolean sex, double price) {
		super(animalName, age, sex, 300);

	}

	@Override
	public String toString() {
		return "Piggy [AnimalName=" + AnimalName + ", age=" + age + ", sex=" + sex + ", price=" + price + "]";
	}
}

class Customer {
	String name;
	int frequency;
	LocalDate Date;

	public Customer() {
		
	}

	public Customer(String name, int frequency, LocalDate date) {
		super();
		this.name = name;
		this.frequency = frequency;
		Date = date;
	}

	public String toString() {
		return "Customer [name=" + name + ", frequency=" + frequency + ", LocalDate=" + Date + "]";
	}
}


interface AnimalShop{
	void GetAnimal(Animal animal);
	void GetCustomer(Customer customer);
	void closed();
}


class MyAnimalShop implements AnimalShop
{
	
	double balance;
	ArrayList<Animal> AnimalList;
	ArrayList<Customer> CustomerList;
	boolean open;
	double OriginalBalance;

	public MyAnimalShop(double balance, ArrayList<Animal> animalList, ArrayList<Customer> customerList, boolean open,
			double originalBalance) {
		super();
		this.balance = balance;
		AnimalList = animalList;
		CustomerList = customerList;
		this.open = open;
		OriginalBalance = originalBalance;
	}

	@Override
	public void GetAnimal(Animal animal) {
		if(balance-animal.price<0)
		{
			throw new InsufficientBalanceException("余额不足，无法购买小动物");
		}
		else
		{
		AnimalList.add(animal);
		balance=balance-animal.price;
		}

		
	
		
	}

	@Override
	public void GetCustomer(Customer customer) {
    CustomerList.add(customer);
		if(AnimalList.isEmpty())
		{
			throw new  AnimalNotFountException("我们没有动物可以卖啦");
		}
		else
		{
			Animal a=AnimalList.get(AnimalList.size()-1);
			System.out.print("这个小动物的信息是"+a.toString());
			AnimalList.remove(AnimalList.size()-1);
			balance=balance+a.price;
		}
	}

	@Override
	public void closed() {
		Customer c=CustomerList.get(CustomerList.size()-1);
		System.out.println(c.Date.getYear()+" "+c.Date.getMonthValue()+" "+c.Date.getDayOfMonth());
		System.out.println(CustomerList);
		System.out.println("今天的利润为"+(balance-OriginalBalance));
	}
	
}

class AnimalNotFountException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnimalNotFountException() {

	}

	public AnimalNotFountException(String msg) {
		super(msg);
	}
}

class InsufficientBalanceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {

	}

	public InsufficientBalanceException(String msg) {
		super(msg);
	}
}
