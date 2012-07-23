package rbacTextualEditor.editor.graphicComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Field;

import javax.swing.JFrame;

import cartesiancoordinatesystem.AxeX;
import cartesiancoordinatesystem.AxeY;
import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemFactory;
import cartesiancoordinatesystem.Point;
import cartesiancoordinatesystem.Segment;

public class CartesianCoordinateSystemView extends JFrame {

	private CartesianCoordinateSystem cartesianCoordinateSystem;
	private CartesiancoordinatesystemFactory factory;
	private Graphics buffer;
	private Image image;

	private int axeXLength;
	private int axeYLength;
	private int imageWidth;
	private int imageHeight;

	// fixed decoration
	private final int margin = 100;

	private final int coeffX;
	private final int coeffY;

	public CartesianCoordinateSystemView(CartesianCoordinateSystem sys) {
		cartesianCoordinateSystem = sys;
		factory = CartesiancoordinatesystemFactory.eINSTANCE;
		coeffX = cartesianCoordinateSystem.getCoeffX();
		coeffY = cartesianCoordinateSystem.getCoeffY();
		axeXLength = cartesianCoordinateSystem.getAxeX().getLength();
		axeYLength = cartesianCoordinateSystem.getAxeY().getLength();
		imageWidth = cartesianCoordinateSystem.getAxeX().getLength() + 200;
		imageHeight = cartesianCoordinateSystem.getAxeY().getLength() + 200;

		setSize(new Dimension(imageWidth, imageHeight));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public CartesianCoordinateSystemView() {
		factory = CartesiancoordinatesystemFactory.eINSTANCE;
		cartesianCoordinateSystem = factory.createCartesianCoordinateSystem();
		cartesianCoordinateSystem.setName("test");
		coeffX = 1;
		coeffY = 1;
		axeXLength = 500;
		axeYLength = 500;
		imageWidth = axeXLength + 200;
		imageHeight = axeYLength + 200;
		AxeX axeX = factory.createAxeX();
		axeX.setName("time ms");
		axeX.setColor("black");
		AxeY axeY = factory.createAxeY();
		axeY.setName("number of rules u:op:ob");
		axeY.setColor("black");
		cartesianCoordinateSystem.setAxeX(axeX);
		cartesianCoordinateSystem.setAxeY(axeY);
		
		// before convertion
		// Point p00 = factory.createPoint();
		// p00.setX(margin);
		// p00.setY(margin);
		//
		// Point p01 = factory.createPoint();
		// p01.setX(margin);
		// p01.setY(margin+axeYLength);
		//
		// Point p10 = factory.createPoint();
		// p10.setX(margin+axeXLength);
		// p10.setY(margin);
		//
		// Point p11 = factory.createPoint();
		// p11.setX(margin+axeXLength);
		// p11.setY(margin+axeYLength);

		Point p00 = factory.createPoint();
		p00.setX(0);
		p00.setY(0);
		p00.setColor("black");

		Point p01 = factory.createPoint();
		p01.setX(0);
		p01.setY(axeYLength);
		p01.setColor("black");

		Point p10 = factory.createPoint();
		p10.setX(axeXLength);
		p10.setY(0);
		p10.setColor("black");

		Point p11 = factory.createPoint();
		p11.setX(axeXLength);
		p11.setY(axeYLength);
		p11.setColor("black");

		Segment s0 = factory.createSegment();
		s0.setA(p00);
		s0.setB(p01);
		s0.setColor("green");

		Segment s1 = factory.createSegment();
		s1.setA(p00);
		s1.setB(p10);
		s1.setColor("green");

		Segment s2 = factory.createSegment();
		s2.setA(p10);
		s2.setB(p11);
		s2.setColor("green");

		Segment s3 = factory.createSegment();
		s3.setA(p01);
		s3.setB(p11);

		cartesianCoordinateSystem.getPoints().add(p00);
		cartesianCoordinateSystem.getPoints().add(p01);
		cartesianCoordinateSystem.getPoints().add(p10);
		cartesianCoordinateSystem.getPoints().add(p11);

		// cartesianCoordinateSystem.getSegments().add(s0);
		// cartesianCoordinateSystem.getSegments().add(s1);
		cartesianCoordinateSystem.getSegments().add(s2);
		cartesianCoordinateSystem.getSegments().add(s3);

		for (int i = 0; i < 200; i = i + 10) {
			Point p = factory.createPoint();
			p.setX(i);
			p.setY(i);
			p.setColor("red");
			cartesianCoordinateSystem.getPoints().add(p);
		}
	
		setSize(new Dimension(imageWidth, imageHeight));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if (buffer == null) {
			image = createImage(imageWidth, imageHeight);
			buffer = image.getGraphics();
		}
		buffer.setColor(Color.white);
		buffer.fillRect(0, 0, imageWidth, imageHeight);
		buffer.setColor(Color.black);

		drawAxes();
		drawAxesGraduation();
		drawPoints();
		drawSegments();

		g.drawImage(image, 0, 0, this);
	}

	public void drawAxeX() {
		buffer.setColor(Color.getColor(cartesianCoordinateSystem.getAxeX()
				.getColor()));

		Point p00 = factory.createPoint();
		p00.setX(margin);
		p00.setY(margin + axeYLength);

		Point p0X = factory.createPoint();
		p0X.setX(margin + axeXLength);
		p0X.setY(margin + axeYLength);

		buffer.drawLine(p00.getX(), p00.getY(), p0X.getX(), p0X.getY());
		buffer.drawLine(p0X.getX(), p0X.getY(), p0X.getX() - 3, p0X.getY() - 3);
		buffer.drawLine(p0X.getX(), p0X.getY(), p0X.getX() - 3, p0X.getY() + 3);

		buffer.drawString(cartesianCoordinateSystem.getAxeX().getName(),
				margin - 30, margin - 10);
	}

	public void drawAxeY() {
		buffer.setColor(colorHelper(cartesianCoordinateSystem.getAxeY()
				.getColor()));

		Point p00 = factory.createPoint();
		p00.setX(margin);
		p00.setY(margin + axeYLength);

		Point p0Y = factory.createPoint();
		p0Y.setX(margin);
		p0Y.setY(margin);

		buffer.drawLine(p00.getX(), p00.getY(), p0Y.getX(), p0Y.getY());
		buffer.drawLine(p0Y.getX(), p0Y.getY(), p0Y.getX() - 3, p0Y.getY() + 3);
		buffer.drawLine(p0Y.getX(), p0Y.getY(), p0Y.getX() + 3, p0Y.getY() + 3);

		buffer.drawString(cartesianCoordinateSystem.getAxeY().getName(),
				(margin + axeXLength), (margin + 20 + axeYLength));
	}

	public void drawAxes() {
		drawAxeX();
		drawAxeY();
		buffer.drawString(cartesianCoordinateSystem.getName(),
				(margin + axeXLength) / 2, (margin + 70 + axeYLength));
	}

	public Color colorHelper(String color) {
		if (color == null) {
			return Color.black;
		}
		if (color.equals("black")) {
			return Color.black;
		}
		if (color.equals("yellow")) {
			return Color.yellow;
		}
		if (color.equals("red")) {
			return Color.red;
		}
		if (color.equals("green")) {
			return Color.green;
		}
		if (color.equals("blue")) {
			return Color.blue;
		}
		return Color.black;
	}

	public void drawSegment(Segment s) {
		buffer.setColor(colorHelper(s.getColor()));
		buffer.drawLine((margin) + (s.getA().getX()), (margin + axeYLength)
				- (s.getA().getY()), (margin) + (s.getB().getX()),
				(margin + axeYLength) - (s.getB().getY()));
	}

	public void drawSegments() {
		for (Segment s : cartesianCoordinateSystem.getSegments()) {
			drawSegment(s);
		}
	}

	public void drawAxesGraduation() {
		int gradX = 5;
		int gradY = 5;

		// axeX
		for (int i = 0; i < gradX; i++) {
			Point px = factory.createPoint();
			px.setX((axeXLength / gradX) * i);
			px.setY(0);
			buffer.drawString(
					Integer.toString(((axeXLength / gradX) * i) / coeffX),
					margin - 3 + ((axeXLength / gradX) * i), axeYLength
							+ margin + 15);
			cartesianCoordinateSystem.getPoints().add(px);
		}

		// axeY
		for (int i = 0; i < gradY; i++) {
			Point py = factory.createPoint();
			py.setX(0);
			py.setY((axeYLength / gradY) * i);
			buffer.drawString(
					Integer.toString(((axeYLength / gradY) * i) / coeffY),
					margin - 32, margin
							+ (axeYLength - (axeYLength / gradY) * i) + 3);
			cartesianCoordinateSystem.getPoints().add(py);
		}
	}

	public void drawPoint(Point p) {
		buffer.setColor(colorHelper(p.getColor()));
		if ((p.getX() < axeXLength) && (p.getY() < axeYLength)) {
			buffer.drawLine((margin) + ((p.getX()) + 2), (margin + axeYLength)
					- (p.getY()), (margin) + ((p.getX()) - 2),
					(margin + axeYLength) - (p.getY()));
			buffer.drawLine((margin) + ((p.getX())),
					(margin + axeYLength) - (p.getY() + 2),
					(margin) + ((p.getX())), (margin + axeYLength)
							- (p.getY() - 2));
		}
	}

	public void drawPoints() {
		for (Point p : cartesianCoordinateSystem.getPoints()) {
			drawPoint(p);
		}
	}

	public void repaint(){
		paint(getGraphics());
	}
	
	
		public void run() {
			int cpt = 0;
			while (true) {
				try {
					repaint();
					
					Thread.sleep(1000);
					int val=cpt;
					for (int i = cpt; i < val+10; i = i + 1) {
						Point p = factory.createPoint();
						p.setX(i);
						p.setY(i);
						p.setColor("red");
						getCartesianCoordinateSystem().getPoints().add(p);
						cpt=cpt+1;
					}
				} catch (Exception e) {
				}
			}
		}
	

	/**
	 * @return the cartesianCoordinateSystem
	 */
	public CartesianCoordinateSystem getCartesianCoordinateSystem() {
		return cartesianCoordinateSystem;
	}


	public static void main(String[] args) {
		CartesianCoordinateSystemView v = new CartesianCoordinateSystemView();
		v.setVisible(true);
		v.run();
		
	}
}
