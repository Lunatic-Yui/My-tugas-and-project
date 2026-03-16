//this is just example from the class
public class example {
    public static void main(String[] args) throws Exception {
        System.out.println("Maw");
        ElectricCar myCar = new ElectricCar("Sedan", "pajero", "hitam", 0);
        System.out.println(myCar.getSpeed());
        myCar.startEngine();
    }
}
abstract class Car {
    protected String brand, type, color;
    protected int speed;
    protected CashPayment cashPayment = new CashPayment();

    public Car(String type, String brand, String color, int speed) {
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.speed = speed;
    }
    abstract void startEngine();
    public int getSpeed() {
        return this.speed;
    } 
    public void setSpeed(int speedNew) {
        this.speed = speedNew;
    }

    public void makeTollPayment(int amount) {
        cashPayment.payToll(amount);
    }
}

class ElectricCar extends Car {
    public ElectricCar(String type, String brand, String color, int speed) {
        super(type, brand, color, speed);
    }
    @Override
    void startEngine() {
        System.out.println("maw");
    }
}

interface TollPayment {
    void paytollPayment(int number);
}

class CashPayment implements tollPayment {
    @Override
    public void payToll(int number) {
        System.err.println("Pembayaran cash sejumlah: " + number);
    }
}
