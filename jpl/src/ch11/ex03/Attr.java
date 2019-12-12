/*
 * 練習問題11.3 p.219
 * 練習問題11.2は、良い考えですか。Attrがジェネリックであることは、第4章で定義したAttributedインタフェースに
 * どのように影響しますか。
 * Attributedオブジェクトに対しては、どのような意味を持ちますか。
 */

/*
 * 練習問題11.2 p.219
 * 第3章のAttrクラスを、ジェネリッククラスとして書き直しなさい。
 */

package ch11.ex03;

public class Attr<E>
{
    private final String name;
    private E value = null;

    public Attr(String name)
    {
        this.name = name;
    }

    public Attr(String name, E value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public Object getValue()
    {
        return value;
    }

    public Object setValue(E newValue)
    {
        Object oldVal = value;
        value = newValue;
        return oldVal;
    }

    public String toString()
    {
        return name + "='" + value + "'";
    }
}
