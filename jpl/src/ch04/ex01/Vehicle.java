/*
 * 練習問題4.1 p.113
 * 86頁の練習問題3.6の回答を、抽象クラスではなく、EnergySourceのためのインタフェースを使用して書き直しなさい。
 */

/*
 * 練習問題3.6 p.86
 * Vehicleを変更して、コンストラクタでVehicleと関連付けされるEnergySourceオブジェクトの参照を持つようにしなさい。
 * EnergySourceクラスはabstractクラスでなければなりません。
 * なぜならば、GasTankオブジェクトの満タンの測定の方法は、Batteryオブジェクトとは異なるでしょう。
 * EnergySourceにabstractのemptyメソッドを入れて、GasTankとBatteryクラスでそのメソッドを実装しなさい。
 * 動力源が空でないことを保証するstartメソッドをVehicleに追加しなさい。
 */

package ch04.ex01;

public class Vehicle
{
    private EnergySource power;
    private double currentSpeed;
    private double currentDirection;
    private String owner;

    static final int TURN_RIGHT = 1;
    static final int TURN_LEFT = 2;

    private static int nextID = 1;
    final int id = nextID++;

    public void turn(int direction)
    {
        if (direction == TURN_RIGHT)
        {
            currentDirection = currentDirection + 1.0;
        }
        else if (direction == TURN_LEFT)
        {
            currentDirection = currentDirection - 1.0;
        }
        else
        {
            ; // 何もしない
        }
    }

    public void turn(double degree)
    {
        currentDirection = currentDirection + degree;
    }

    public String getOwner()
    {
        return owner;
    }

    public double getCurrentSpeed()
    {
        return currentSpeed;
    }

    public void changeSpeed(double speed)
    {
        currentSpeed = speed;
    }

    public void stop()
    {
        currentSpeed = 0.0;
    }

    public double getCurrentDirection()
    {
        return currentDirection;
    }

    public void setCurrentDirection(double direction)
    {
        currentDirection = direction;
    }

    public Vehicle(EnergySource power)
    {
        this.power = power;
    }

    public Vehicle(EnergySource power, String ownerName)
    {
        this(power);
        owner = ownerName;
    }

    public String toString()
    {
        String desc = "owner: " + owner;

        return desc;
    }

    public static int showCurrentID()
    {
        // まだ識別番号が一度も使われていない場合は0を返す
        return nextID - 1;
    }

    // 動力源が空の場合はGasTankを割り当てる
    public void start()
    {
        if (power == null)
        {
            power = new GasTank();
        }
        System.out.println("This Vehicle power: " + power);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        EnergySource testEnergy = new Battery();

        Vehicle testVehicle1 = new Vehicle(testEnergy);

        testVehicle1.start();
        testVehicle1.power.empty();

        testVehicle1.power = null;

        testVehicle1.start();
        testVehicle1.power.empty();

    }

}
