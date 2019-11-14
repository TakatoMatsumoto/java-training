/*
 * 練習問題3.5 p.86
 * 他のベンチマークを行う新たな拡張したクラスを作成しなさい。たとえば、０からパラメータとして渡された値まで
 * ループするのに要する時間を計るとか。
 */

package ch03.ex05;

public class ExtendBenchmark extends Benchmark
{
    void benchmark()
    {
        System.out.println("test");
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ExtendBenchmark test = new ExtendBenchmark();
        System.out.println("total time: " + test.repeat(10));

    }

}
