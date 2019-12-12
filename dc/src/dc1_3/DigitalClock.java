/*
 課題 1-3
 課題 1-2のデジタル時計を、次のように修正してください。
 FrameではなくWindow クラスを使用して、フレーム枠のないデジタル時計にする。
 課題 1-2のダイアログで指定できた属性は、マウスの右クリックでポップアップメニューを表示して、カスケード形式で選択出来るようにする（ダイアログは開かない）。
 時計内をマウスの左ボタンでクリックしたまま、デスクトップ上でウィンドウを移動させることができるようにする。
*/

package dc1_3;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class DigitalClock extends Window implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int MARGIN = 30;

	// Buffer
	private Image imageBuffer;
	private Graphics graphicsBuffer;

	// Mouse
	private int x, y;

	// Menu
	PopupMenu popup;

	// Settings
	public String fontFamily = null;
	public int fontSize = 100;
	public Color fontColor = Color.BLACK;
	public Color backGroundColor = Color.WHITE;

	public DigitalClock() {
		super(null, null);

		// pop up menu
		popup = new PopupMenu();
		add(popup);
		MenuItem menu;

		// font menu
		Menu menuFont = new Menu("Font");
		popup.add(menuFont);
		ActionListener menuFontListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DigitalClock.this.fontFamily = ((MenuItem) e.getSource()).getLabel();
			}
		};
		// Get font list
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fontFamilies = ge.getAvailableFontFamilyNames();
		// Set label
		for (String label : fontFamilies) {
			menu = new MenuItem(label);
			menu.addActionListener(menuFontListener);
			menuFont.add(menu);
		}

		// fontsize menu
		Menu menuSize = new Menu("Size");
		popup.add(menuSize);
		ActionListener menuSizeListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DigitalClock.this.fontSize = Integer.parseInt(((MenuItem) e
						.getSource()).getLabel());
			}
		};
		for (String label : new String[] { "100", "200", "300" }) {
			menu = new MenuItem(label);
			menu.addActionListener(menuSizeListener);
			menuSize.add(menu);
		}

		// text color menu
		Menu menuColor = new Menu("Font Color");
		popup.add(menuColor);
		ActionListener menuColorListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DigitalClock.this.fontColor = colorNameToColor(((MenuItem) e.getSource()).getLabel());
			}
		};
		for (String label : new String[] { "Black", "White", "Red", "Green",
				"Blue" }) {
			menu = new MenuItem(label);
			menu.addActionListener(menuColorListener);
			menuColor.add(menu);
		}

		// background color menu
		Menu menuBgColor = new Menu("Background Color");
		popup.add(menuBgColor);
		ActionListener menuBgColorListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DigitalClock.this.backGroundColor = colorNameToColor(((MenuItem) e.getSource()).getLabel());
			}
		};
		for (String label : new String[] { "Black", "White", "Red", "Green",
				"Blue" }) {
			menu = new MenuItem(label);
			menu.addActionListener(menuBgColorListener);
			menuBgColor.add(menu);
		}

		// exit menu
		MenuItem menuExit = new MenuItem("Exit");
		popup.add(menuExit);
		ActionListener menuExitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		menuExit.addActionListener(menuExitListener);

		// mouse
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				if (e.isPopupTrigger()) {
					DigitalClock.this.popup.show(DigitalClock.this, e.getX(), e.getY());
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isPopupTrigger()) {
					DigitalClock.this.popup.show(DigitalClock.this, e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					DigitalClock.this.popup.show(DigitalClock.this, e.getX(), e.getY());
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = getLocation();
				setLocation(p.x + e.getX() - x, p.y + e.getY() - y);
			}
		});

		setVisible(true);
	}

	public void run() {
		for (;;) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void paint(Graphics g) {
		// time
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		String time = String.format("%02d:%02d:%02d", h, m, s);

		// get fontsize
		if (fontFamily != null)
			g.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			g.setFont(new Font("Serif", Font.PLAIN, fontSize));

		FontMetrics ft = g.getFontMetrics();
		int stringHeight = ft.getAscent();
		int stringWidth = ft.stringWidth(time);
		int standardWidth = ft.stringWidth("00:00:00");

		// reset frame size
		int height = stringHeight + MARGIN * 2;
		int width = standardWidth + MARGIN * 2;
		setSize(width, height);

		// buffer image
		if (imageBuffer == null || imageBuffer.getWidth(this) != width
				|| imageBuffer.getHeight(this) != height) {
			imageBuffer = createImage(width, height);
			graphicsBuffer = null;
		}

		// buffer graphics
		if (graphicsBuffer == null) {
			graphicsBuffer = imageBuffer.getGraphics();
		}

		// fill graphics
		graphicsBuffer.setColor(backGroundColor);
		graphicsBuffer.fillRect(0, 0, width, height);

		// draw time
		if (fontFamily != null)
			graphicsBuffer.setFont(new Font(fontFamily, Font.PLAIN, fontSize));
		else
			graphicsBuffer.setFont(new Font("Serif", Font.PLAIN, fontSize));
		graphicsBuffer.setColor(fontColor);
		graphicsBuffer.drawString(time, MARGIN + (standardWidth - stringWidth)
				/ 2, MARGIN + stringHeight);

		// copy buffer image
		g.drawImage(imageBuffer, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private static Color colorNameToColor(String colorName) {
		String intern = colorName.intern();
		if (intern == "Black")
			return Color.BLACK;
		else if (intern == "White")
			return Color.WHITE;
		else if (intern == "Red")
			return Color.RED;
		else if (intern == "Green")
			return Color.GREEN;
		else if (intern == "Blue")
			return Color.BLUE;
		else
			return null;
	}

	public static void main(String args[]) {
		DigitalClock dc = new DigitalClock();
		Thread thread = new Thread(dc);
		thread.start();
	}
}