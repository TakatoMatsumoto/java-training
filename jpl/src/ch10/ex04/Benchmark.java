/*
 * 練習問題10.4 p.205
 * 今までの練習問題からforループを使用した問題を数題選んで、whileループを使用して書き直しなさい。
 * do-whileループを使用しても書きなおすことができますか。そのように書きなおしたりしますか。しないとしたら、なぜですか。
 */

/*
 * 練習問題3.5 p.86
 * 他のベンチマークを行う新たな拡張したクラスを作成しなさい。たとえば、０からパラメータとして渡された値まで
 * ループするのに要する時間を計るとか。
 */

package ch10.ex04;

public abstract class Benchmark
{
    abstract void benchmark();

    public final long repeat(int count)
    {
        long start = System.nanoTime();

        // ##### オリジナル(for版) #####
        /*
         * for (int i = 0; i < count; i++) { benchmark(); }
         */

        // ##### while版 #####
        int i = 0;
        while (i < count)
        {
            benchmark();
            i++;
        }

        // countが0の場合、benchmarkが一度も実行されてはいけないので、do-whileでは書けない

        return (System.nanoTime() - start);
    }

}
