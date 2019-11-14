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

public class GasTank implements EnergySource
{
    public void empty()
    {
        System.out.println("GasTank's empty method");
    }
}
