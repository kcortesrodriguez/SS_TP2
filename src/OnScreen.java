import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.util.*;

public class OnScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	int width, height;

	public OnScreen(int width, int height) {
		this.width = width;
		this.height = height;
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
	}

	public void draw(Set<Particle> particles) {

		Graphics g = this.getGraphics();
		int dimen = 20;
		 g.clearRect(0, 0, getWidth(), getHeight());

		for (Particle p : particles) {
			g.setColor(p.getColor());
			double x, y, x2, y2;
			x = (p.getPosition().getX()) * dimen;
			y = (p.getPosition().getY()) * dimen;
			x2 = (p.getV().getXVelocity()) * dimen;
			y2 = (p.getV().getYVelocity()) * dimen;

			Graphics2D g2 = (Graphics2D) g;
			Line2D lin = new Line2D.Double(x, y, x + x2, y + y2);
			g2.draw(lin);
		}

	}

	public void saveImage() {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.paint(img.getGraphics());
		try {
			String nombre = Calendar.getInstance().getTimeInMillis()+".png";
			ImageIO.write(img, "png", new File(nombre));
			System.out.println("Imagen guardada como "+nombre);
		} catch (Exception e) {
			System.out.println("Imagen no guardada. " + e.getMessage());
		}
	}

}