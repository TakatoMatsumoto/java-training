/*
 * 課題1-2
 * デジタル時計に次の機能追加を行ってください。
 * ・メニューを付けて、プロパティダイアログを開ける。
 * ・プロパティダイアログでは、以下の項目を設定できる。
 * 　1. フォントの指定
 * 　2. フォントサイズの指定
 * 　3. 文字色の指定
 * 　4. 時計の背景色の指定
 * ・描画に際して、ちらつきをなくすようにダブルバッファリングする。
 * ・フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して、正しく表示されるようにする。
 */

/*
 * 課題1-1
 * AWTのFrameを使用して、時間を表示するデジタル時計（アナログ時計は不可）を作成してください。
 * ・java.awt.Frameを使用する。
 * ・Windowsの普通のアプリケーションと同様にタイトルバーの「×」ボタンをクリックすると終了する。
 * ・デジタル時計の描画は、paintメソッド内でGraphicsを使用して行う。テキストラベルによる単なる表示は、不可。
 */

package dc1_2;

import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalClock extends Frame implements Runnable, ActionListener
{
    private static final long serialVersionUID = 1L;
    private int hour;
    private int minute;
    private int second;

    private Thread th;
    private PropertyDialog dialog;
    private Menu menuMenu;
    private MenuItem menuProperty;

    private Image imageBuffer;
    private Graphics graphicBuffer;

    private String fontType = "TimesRoman";
    private Integer fontSize = 48;
    private Color fontColor = Color.black;
    private Color backgroundColor = Color.white;

    private int windowSizeX = 320;
    private int windowSizeY = 240;

    private String timeString;

    private MenuBar menuBar;

    private Font fontSetting = new Font("TimesRoman", Font.PLAIN, 48);

    public DigitalClock(String title)
    {
        // タイトルバーにタイトルを登録する
        super(title);

        // ウィンドウを閉じられるようにする
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });


        menuBar = new MenuBar();
        setMenuBar(menuBar);
        menuMenu = new Menu("Menu");
        menuMenu.addActionListener(this);
        menuBar.add(menuMenu);

        menuProperty = new MenuItem("Property");
        menuMenu.add(menuProperty);

        dialog = new PropertyDialog(this);

        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minute = Calendar.getInstance().get(Calendar.MINUTE);
        second = Calendar.getInstance().get(Calendar.SECOND);

    }

    public void paint(Graphics g)
    {
   
        timeString = hour + ":" + minute + ":" + second;

        windowSizeX = graphicBuffer.getFontMetrics().stringWidth(timeString);
        windowSizeX += getInsets().left;
        windowSizeX += getInsets().right;

        windowSizeY = graphicBuffer.getFontMetrics().getAscent();
        windowSizeY += graphicBuffer.getFontMetrics().getDescent();
        windowSizeY += graphicBuffer.getFontMetrics().getLeading();
        windowSizeY += getInsets().top;

        setSize(windowSizeX, windowSizeY);

        imageBuffer = createImage(windowSizeX, windowSizeY);
        graphicBuffer = imageBuffer.getGraphics();

        graphicBuffer.setColor(backgroundColor);
        graphicBuffer.fillRect(0, 0, windowSizeX, windowSizeY);

        fontSetting = new Font(fontType, Font.PLAIN, fontSize);
        graphicBuffer.setFont(fontSetting); // フォントの設定
        graphicBuffer.setColor(fontColor); // 文字色の設定
        graphicBuffer.drawString(timeString, 0, graphicBuffer.getFontMetrics()
                .getAscent() + getInsets().top - getInsets().bottom);


        g.drawImage(imageBuffer, 0, 0, this);
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public void run()
    {
        while (true)
        {
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            minute = Calendar.getInstance().get(Calendar.MINUTE);
            second = Calendar.getInstance().get(Calendar.SECOND);

            repaint();

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                ;
            }
        }

    }

    public String getFontType()
    {
        return fontType;
    }

    public void setFontType(String fontType)
    {
        this.fontType = fontType;
    }

    public Integer getFontSize()
    {
        return fontSize;
    }

    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
    }

    public Color getFontColor()
    {
        return fontColor;
    }

    public void setFontColor(Color fontColor)
    {
        this.fontColor = fontColor;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public Font getFontSetting()
    {
        return fontSetting;
    }

    public void setFontSetting(Font fontSetting)
    {
        this.fontSetting = fontSetting;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        DigitalClock window = new DigitalClock("Digital Clock");

        window.th = new Thread(window);

        window.setSize(220, 150);
        window.setResizable(false);
        window.setVisible(true);

        window.imageBuffer = window.createImage(220, 150);
        window.graphicBuffer = window.imageBuffer.getGraphics();

        window.th.start();

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand() == "Property")
        {
            dialog.setVisible(true);
        }
        else
        {
            ;
        }
    }

}
