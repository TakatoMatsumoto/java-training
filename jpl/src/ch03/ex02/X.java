/*
 * 練習問題3.2 p.72
 * テキストのクラスXとクラスYを入力して、各マスクの値の変化を表示するための出力文を追加しなさい。
 * mainメソッドを追加し、実行して結果を見て下さい。（ヒント：16進形式で数値を表示するための書式指定子%xを用いて、
 * 第１章で示したprintfメソッドを使用してください。）
 */

package ch03.ex02;

public abstract class X
{

    {
        testShow("before X initialize");
    }

    protected int xMask = 0x00ff;
    protected int fullMask;

    {
        testShow("after X initialize");
    }

    public X()
    {
        fullMask = xMask;
        testShow("after X constructor");
    }

    public int mask(int orig)
    {
        return (orig & fullMask);
    }

    public abstract void testShow(String when);

}
