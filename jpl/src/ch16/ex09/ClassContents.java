/*
 * 練習問題16.9 p.375
 * 指定されたクラスの完全な宣言を表示するプログラムをリフレクションを使用して作成しなさい。
 * ただし、インポート文、コメント、それに、初期化子、コンストラクタ、メソッドのコードは除外します。
 * メンバー宣言は、ソースコードに書かれたように表示すべきです。
 * 今まで説明してきたすべてのリフレクションクラスを使用する必要があるでしょう。
 * 多くのリフレクションオブジェクトのtoStringメソッドは、欲しい情報を正しい形で提供しませんので、
 * 個々の情報を集めてまとめる必要があります。
 */

/*
 * 練習問題16.5 p.363
 * 個々のメンバーに対して得られるアノテーション情報を含むようにClassContentsを拡張子しなさい。
 */

/*
 * 練習問題16.3 p.359
 * すべての宣言されているメンバーとすべての継承されているpublicのメンバーに関する情報を表示するように
 * ClassContesntsを修正しなさい。
 * 同じ物を2度表示しないようにしなさい。
 */

package ch16.ex09;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ClassContents
{
    private static String[] memberName = new String[100];

    enum MemberType
    {
        FIELD, CONSTRUCTOR, METHOD;
    }

    public static void main(String[] args)
    {
        try
        {
            Class<?> c = Class.forName(args[0]);
            showAllAnnotation(c);
            System.out.println(c);

            printMembers(c.getFields(), true, MemberType.FIELD);
            printMembers(c.getDeclaredFields(), false, MemberType.FIELD);
            printMembers(c.getConstructors(), true, MemberType.CONSTRUCTOR);
            printMembers(c.getDeclaredConstructors(), false, MemberType.CONSTRUCTOR);
            printMembers(c.getMethods(), true, MemberType.METHOD);
            printMembers(c.getDeclaredMethods(), false, MemberType.METHOD);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("unknown class: " + args[0]);
        }
    }

    private static void printMembers(Member[] mems, boolean isFirst, MemberType type)
    {
        Member m;
        // for (Member m : mems)
        checkMember: for (int i = 0; i < mems.length; i++)
        {
            m = mems[i];
            if (m.getDeclaringClass() == Object.class)
            {
                continue;
            }
            if (true == isFirst)
            {
                memberName[i] = m.toString();
            }
            if (false == isFirst)
            {
                for (int j = 0; j < memberName.length; j++)
                {
                    if (m.toString().equals(memberName[j]))
                    {
                        continue checkMember;
                    }
                }
            }
            String decl = m.toString();
            System.out.print(" ");
            Annotation[] tmp;
            switch(type)
            {
                case FIELD:
                    tmp = ((Field)m).getAnnotations();
                    break;
                case CONSTRUCTOR:
                    tmp = ((Constructor<?>)m).getAnnotations();
                    break;
                case METHOD:
                    tmp = ((Method)m).getAnnotations();
                    break;
                default:
                    tmp = null;
                    break;
            }
            for (int j = 0; j < tmp.length; j++)
            {
                System.out.println(tmp[j]);
                System.out.print(" ");
            }
            System.out.println(strip(decl, "java.lang."));
        }
    }

    public static String strip(String source, String removeTarget)
    {
        return source.replaceAll(removeTarget, "");
    }


    public static void showAllAnnotation(Class<?> c)
    {
        Annotation[] tmp;
        tmp = c.getAnnotations();
        for (int i = 0; i < tmp.length; i++)
        {
            System.out.println(tmp[i]);
        }
    }

}
