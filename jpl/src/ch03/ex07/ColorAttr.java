/*
 * 練習問題3.7 p.87
 * ColorAttrに対してequalsとhashCodeをオーバーライドしなさい。
 */

package ch03.ex07;

public class ColorAttr extends Attr
{
    private ScreenColor myColor; // 変換された色

    public ColorAttr(String name, Object value)
    {
        super(name, value);
        decodeColor();
    }

    public ColorAttr(String name)
    {
        this(name, "transparent");
    }

    public ColorAttr(String name, ScreenColor value)
    {
        super(name, value.toString());
        myColor = value;
    }

    public Object setValue(Object newValue)
    {
        // スーパークラスのsetValueを最初に行う
        Object retval = super.setValue(newValue);
        decodeColor();
        return retval;
    }

    /** 値を記述ではなくScreenColorに設定する */
    public ScreenColor setValue(ScreenColor newValue)
    {
        // スーパークラスのsetValueを最初に行う
        super.setValue(newValue.toString());
        ScreenColor oldValue = myColor;
        myColor = newValue;
        return oldValue;
    }

    /** 変換されたScreenColorオブジェクトを返す */
    public ScreenColor getColor()
    {
        return myColor;
    }

    /** getValue()で得られる記述からScreenColorを設定する */
    protected void decodeColor()
    {
        if (getValue() == null)
        {
            myColor = null;
        }
        else
        {
            myColor = new ScreenColor(getValue());
        }
    }

    public boolean equals(ColorAttr obj)
    {
        return (this.getName() == obj.getName()
                && this.getValue() == obj.getValue());
    }

    public int hashCode()
    {
        return getName().hashCode();
    }

    public static void main(String[] args)
    {
        ColorAttr test1 = new ColorAttr("hoge");
        ColorAttr test2 = new ColorAttr("hoge");
        ColorAttr test3 = new ColorAttr("fuga");

        System.out.println(test1.equals(test2));
        System.out.println(test1.equals(test3));

        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());
        System.out.println(test3.hashCode());
    }
}