/*
 * 練習問題11.1 p.219
 * 練習問題2.2で始めたLinkedListクラスを見直して、ジェネリッククラスとして書き直しなさい。
 */

/*
 * 練習問題2.2 p.37
 * LinkedListクラスを作成しなさい。LinkeListクラスは、Element型のフィールドと、リストの中で次のLinkedList要素への参照を持ちます。
 */

package ch11.ex01;

public class LinkedList<E>
{
    E element;
    LinkedList<E> nextElement;

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        LinkedList<String> test1 = new LinkedList<String>();
        test1.element = "test1";
        test1.nextElement = new LinkedList<String>();
        test1.nextElement.element = "test2";
        System.out.println(test1.element);
        System.out.println(test1.nextElement.element);

    }

}
