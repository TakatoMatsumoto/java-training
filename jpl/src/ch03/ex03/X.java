/*
 * 練習問題3.3 p.72
 * オブジェクトの生成の間に、拡張したクラスからの値を使用して、マスクの値を設定するのが重要な場合には、
 * どうすれば問題を回避できますか。
 */

package ch03.ex03;

public abstract class X {

    {
	testShow("pre X initialize");
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
