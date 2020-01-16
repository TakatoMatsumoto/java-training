/*
 * 練習問題16.4 p.363
 * 指定された型に適用されていて得られるすべてのアノテーションを表示するプログラムを書きなさい。
 * （リテンションポリシーがRUNTIMEを持つアノテーションだけが得られます。）
 */

package ch16.ex04;

import java.lang.annotation.Annotation;

public class ShowAllAnnotation
{
    public void showAllAnnotation(Class<?> c)
    {
        Annotation[] tmp;
        tmp = c.getAnnotations();
        for (int i = 0; i < tmp.length; i++)
        {
            System.out.println(tmp[i]);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ShowAllAnnotation test = new ShowAllAnnotation();
        try
        {
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            test.showAllAnnotation(c);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("unknown class: " + args[0]);
        }
    }

}
