/*
 * 課題1-4
 * 課題1-2のデジタル時計で、属性をダイアログで指定できるようにしましたが、ダイアログを作りなおして下さい。
 * ・レイアウトマネージャは、GridBagLayoutを使用する。
 * ・プロパティダイアログは、属性名+のリストメニューが縦に並ぶようにする。
 * 　　　フォント　フォントのリスト
 * フォントサイズ　サイズのリスト
 * 　　　　文字色　色のリスト
 * 　　　　背景色　色のリスト
 * 　この場合「属性名」のラベルは右寄せして、「値の選択リスト」メニューは左寄せる。
 * ・ダイアログの下には、「OK」「キャンセル」のバタンをダイアログの右下に寄せて表示し、それぞれのボタンを実装する。
 * 　キャンセルされた場合には、正しく、元の値に戻るようにする。
 * ・java.util.prefsパッケージを使用して、プロパティダイアログの内容の保存と、時計の終了時の位置を保存する。
 * 　再度、時計を起動した場合に、それらの保存を復元して、デスクトップの元の位置に表示されるようにする。
 */

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

package dc1_4;

import java.awt.*;
import java.awt.event.*;

public class PropertyDialog extends Dialog implements ActionListener,
        ItemListener
{
    private Choice choiceFontType = new Choice();
    private Choice choiceFontSize = new Choice();
    private Choice choiceFontColor = new Choice();
    private Choice choiceBackgroundColor = new Choice();

    private Label labelFontType = new Label("Font Type: ");
    private Label labelFontSize = new Label("Font Size: ");
    private Label labelFontColor = new Label("Font Color: ");
    private Label labelBackgroundColor = new Label("Background Color: ");

    private String defaultFontColor;
    private String defaultBackgroundColor;

    private String fonts[] = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    private String newFontType = "TimesRoman";
    private int newFontSize = 10;
    private Color newFontColor = Color.black;
    private Color newBackgroundColor = Color.white;

    private Button OKButton = new Button("OK");
    private Button cancelButton = new Button("Cancel");

    private GridBagConstraints gbc = new GridBagConstraints();

    private GridBagLayout gbl = new GridBagLayout();

    private DigitalClock digitalClock;

    static public String stringColor[] =
        { "Black", "White", "Red", "Green", "Blue", "Cyan", "Magenta", "Yellow",
                "Orange" };
    static public Color colorColor[] =
        { Color.black, Color.white, Color.red, Color.green, Color.blue, Color.cyan,
                Color.magenta, Color.yellow, Color.orange };

    public PropertyDialog(Frame owner)
    {

        super(owner);

        digitalClock = (DigitalClock) owner;

        // 現在の時計の設定を取得する
        newFontType = digitalClock.getFontType();
        newFontSize = digitalClock.getFontSize();
        newFontColor = digitalClock.getFontColor();
        newBackgroundColor = digitalClock.getBackgroundColor();

        this.setLayout(gbl);
        this.setTitle("Property");
        this.setSize(300, 180);
        this.setResizable(false);

        // リスナー登録
        choiceFontType.addItemListener(this);
        choiceFontSize.addItemListener(this);
        choiceFontColor.addItemListener(this);
        choiceBackgroundColor.addItemListener(this);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // フォントタイプ
        // ラベル
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelFontType, gbc);
        // choice
        for (int i = 0; i < fonts.length; i++)
        {
            choiceFontType.add(fonts[i]);
        }
        choiceFontType.select(digitalClock.getFontType());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontType, gbc);

        // フォントサイズ
        // label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelFontSize, gbc);
        // choice
        for (Integer i = 50; i < 300; i = i + 30)
        {
            choiceFontSize.add(i.toString());
        }
        choiceFontSize.select(digitalClock.getFontSize().toString());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontSize, gbc);


        // フォントカラー
        // フォントカラーの初期選択値をStringで取得する
        defaultFontColor = changeColorToString(digitalClock.getFontColor());
        // label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelFontColor, gbc);
        // choice
        for (int i = 0; i < stringColor.length; i++)
        {
            choiceFontColor.add(stringColor[i]);
        }
        choiceFontColor.select(defaultFontColor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceFontColor, gbc);


        // 背景色
        // 背景色の初期選択値をStringで取得する
        defaultBackgroundColor = changeColorToString(digitalClock.getBackgroundColor());
        // label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbl.setConstraints(labelBackgroundColor, gbc);
        // choice
        for (int i = 0; i < stringColor.length; i++)
        {
            choiceBackgroundColor.add(stringColor[i]);
        }
        choiceBackgroundColor.select(defaultBackgroundColor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbl.setConstraints(choiceBackgroundColor, gbc);

        // OKボタン
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(OKButton, gbc);

        // Cancelボタン
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(cancelButton, gbc);

        // ダイアログボックスを閉じるとき
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                setVisible(false);
            }
        });

        this.add(labelFontType);
        this.add(labelFontSize);
        this.add(labelFontColor);
        this.add(labelBackgroundColor);
        this.add(choiceFontType);
        this.add(choiceFontSize);
        this.add(choiceFontColor);
        this.add(choiceBackgroundColor);
        this.add(OKButton);
        this.add(cancelButton);
    }

    private static final long serialVersionUID = 3853419917132576660L;

    public void resetParameter()
    {
        // 現在の時計の設定を取得する
        newFontType = digitalClock.getFontType();
        newFontSize = digitalClock.getFontSize();
        newFontColor = digitalClock.getFontColor();
        newBackgroundColor = digitalClock.getBackgroundColor();

        choiceFontType.select(digitalClock.getFontType());
        choiceFontSize.select(digitalClock.getFontSize().toString());
        // フォントカラーの初期選択値をStringで取得する
        defaultFontColor = changeColorToString(digitalClock.getFontColor());
        choiceFontColor.select(defaultFontColor);
        // 背景色の初期選択値をStringで取得する
        defaultBackgroundColor = changeColorToString(digitalClock.getBackgroundColor());
        choiceBackgroundColor.select(defaultBackgroundColor);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if ("OK" == e.getActionCommand())
        {
            digitalClock.setFontType(newFontType);
            digitalClock.setFontSize(newFontSize);
            digitalClock.setFontColor(newFontColor);
            digitalClock.setBackgroundColor(newBackgroundColor);
            setVisible(false);
        }

        if ("Cancel" == e.getActionCommand())
        {
            setVisible(false);
        }
    }

    static public Color changeStringToColor(String colorString)
    {
        for (int i = 0; i < stringColor.length; i++)
        {
            if (colorString.equals(stringColor[i]))
            {
                return colorColor[i];
            }
        }
        return Color.black;
    }

    static public String changeColorToString(Color targetColor)
    {
        for (int i = 0; i < colorColor.length; i++)
        {
            if (targetColor.equals(colorColor[i]))
            {
                return stringColor[i];
            }
        }
        return stringColor[0];
    }

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        // フォントタイプが選択された場合
        if (choiceFontType == e.getSource())
        {
            newFontType = e.getItem().toString();
        }
        else if (choiceFontSize == e.getSource())
        {
            newFontSize = Integer.parseInt(e.getItem().toString());
        }
        else if (choiceFontColor == e.getSource())
        {
            newFontColor = changeStringToColor((String)e.getItem());
        }
        else if (choiceBackgroundColor == e.getSource())
        {
            newBackgroundColor = changeStringToColor((String)e.getItem());
        }
        else
        {
            System.out.println("Error occurs at select property!");
            System.out.println(e);
        }

    }
}
