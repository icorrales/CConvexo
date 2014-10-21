/*
 * Copyright (c) 2007 Alexander Hristov.
 * http://www.ahristov.com
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License (http://www.gnu.org/licenses/gpl.txt) 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */

/*

package dia.upm.cconvexo.prueba;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;


public class RGraphics extends Graphics2D{
	private Graphics2D g;
		
	public RGraphics(Graphics2D g) {
		this.g = g;
	}
	
	public void drawArrow(double x1, double y1, double x2, double y2, double arrowAngle, double length) {
		drawLine(x1,y1,x2,y2);

		/*double angle = Math.atan2(y2-y1,x2-x1);
		double x3 = x2-length*Math.cos(angle+arrowAngle);
		double y3 = y2-length*Math.sin(angle+arrowAngle);
		drawLine(x2,y2,x3,y3);
		double x4 = x2-length*Math.cos(angle-arrowAngle);
		double y4 = y2-length*Math.sin(angle-arrowAngle);
		drawLine(x2,y2,x4,y4);*/
/*	}



	public void drawArrow(Point a, Point b, double arrowAngle, double length) {
		drawArrow(a.x,a.y,b.x,b.y,arrowAngle,length);
	}
	
	public void drawLine(double x1, double y1, double x2, double y2) {
		g.drawLine((int)Math.round(x1),(int)Math.round(y1),(int)Math.round(x2),(int)Math.round(y2));
	}
	public void drawPoint(double x, double y) {
		drawLine(x,y,x,y);
	}
	public void drawFatPoint(double x, double y) {
		g.fillOval((int)(x-3),(int)(y-3),6,6);
	}
	public void drawFatPoint(Point p) {
		g.fillOval((int)(p.x-3),(int)(p.y-3),6,6);
	}
	public void drawOval(double x, double y, double rx, double ry) {
		g.drawOval((int)Math.round(x-rx),(int)Math.round(y-ry),(int)Math.round(2*rx),(int)Math.round(2*ry));
	}
	public void fillOval(double x, double y, double rx, double ry) {
		g.fillOval((int)Math.round(x-rx),(int)Math.round(y-ry),(int)Math.round(2*rx),(int)Math.round(2*ry));
	}
	public void drawLine(Point a, Point b) {
		g.drawLine(a.x,a.y,b.x,b.y);
	}
	

	public void addRenderingHints(Map<?, ?> hints) {
		g.addRenderingHints(hints);
	}

	public void clearRect(int x, int y, int width, int height) {
		g.clearRect(x, y, width, height);
	}

	public void clip(Shape s) {
		g.clip(s);
	}

	public void clipRect(int x, int y, int width, int height) {
		g.clipRect(x, y, width, height);
	}

	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		g.copyArea(x, y, width, height, dx, dy);
	}

	public Graphics create() {
		return g.create();
	}

	public Graphics create(int x, int y, int width, int height) {
		return g.create(x, y, width, height);
	}

	public void dispose() {
		g.dispose();
	}

	public void draw(Shape s) {
		g.draw(s);
	}

	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		g.draw3DRect(x, y, width, height, raised);
	}

	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	public void drawBytes(byte[] data, int offset, int length, int x, int y) {
		g.drawBytes(data, offset, length, x, y);
	}

	public void drawChars(char[] data, int offset, int length, int x, int y) {
		g.drawChars(data, offset, length, x, y);
	}

	public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
		g.drawImage(img, op, x, y);
	}

	public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
		return g.drawImage(img, xform, obs);
	}

	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return g.drawImage(img, x, y, bgcolor, observer);
	}

	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		return g.drawImage(img, x, y, observer);
	}

	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		return g.drawImage(img, x, y, width, height, bgcolor, observer);
	}

	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		return g.drawImage(img, x, y, width, height, observer);
	}

	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
		return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		return g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

	public void drawOval(int x, int y, int width, int height) {
		g.drawOval(x, y, width, height);
	}

	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		g.drawPolygon(xPoints, yPoints, nPoints);
	}

	public void drawPolygon(Polygon p) {
		g.drawPolygon(p);
	}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		g.drawPolyline(xPoints, yPoints, nPoints);
	}

	public void drawRect(int x, int y, int width, int height) {
		g.drawRect(x, y, width, height);
	}

	public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
		g.drawRenderableImage(img, xform);
	}

	public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
		g.drawRenderedImage(img, xform);
	}

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	public void drawString(AttributedCharacterIterator iterator, float x, float y) {
		g.drawString(iterator, x, y);
	}

	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		g.drawString(iterator, x, y);
	}

	public void drawString(String s, float x, float y) {
		g.drawString(s, x, y);
	}

	public void drawString(String str, int x, int y) {
		g.drawString(str, x, y);
	}

	public boolean equals(Object obj) {
		return g.equals(obj);
	}

	public void fill(Shape s) {
		g.fill(s);
	}

	public void fill3DRect(int x, int y, int width, int height, boolean raised) {
		g.fill3DRect(x, y, width, height, raised);
	}

	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	public void fillOval(int x, int y, int width, int height) {
		g.fillOval(x, y, width, height);
	}

	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		g.fillPolygon(xPoints, yPoints, nPoints);
	}

	public void fillPolygon(Polygon p) {
		g.fillPolygon(p);
	}

	public void fillRect(int x, int y, int width, int height) {
		g.fillRect(x, y, width, height);
	}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	public void finalize() {
		g.finalize();
	}

	public Color getBackground() {
		return g.getBackground();
	}

	public Shape getClip() {
		return g.getClip();
	}

	public Rectangle getClipBounds() {
		return g.getClipBounds();
	}

	public Rectangle getClipBounds(Rectangle r) {
		return g.getClipBounds(r);
	}

	@SuppressWarnings("deprecation")
	public Rectangle getClipRect() {
		return g.getClipRect();
	}

	public Color getColor() {
		return g.getColor();
	}

	public Composite getComposite() {
		return g.getComposite();
	}

	public GraphicsConfiguration getDeviceConfiguration() {
		return g.getDeviceConfiguration();
	}

	public Font getFont() {
		return g.getFont();
	}

	public FontMetrics getFontMetrics() {
		return g.getFontMetrics();
	}

	public FontMetrics getFontMetrics(Font f) {
		return g.getFontMetrics(f);
	}

	public FontRenderContext getFontRenderContext() {
		return g.getFontRenderContext();
	}

	public Paint getPaint() {
		return g.getPaint();
	}

	public Object getRenderingHint(Key hintKey) {
		return g.getRenderingHint(hintKey);
	}

	public RenderingHints getRenderingHints() {
		return g.getRenderingHints();
	}

	public Stroke getStroke() {
		return g.getStroke();
	}

	public AffineTransform getTransform() {
		return g.getTransform();
	}

	public int hashCode() {
		return g.hashCode();
	}

	public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
		return g.hit(rect, s, onStroke);
	}

	public boolean hitClip(int x, int y, int width, int height) {
		return g.hitClip(x, y, width, height);
	}

	public void rotate(double theta, double x, double y) {
		g.rotate(theta, x, y);
	}

	public void rotate(double theta) {
		g.rotate(theta);
	}

	public void scale(double sx, double sy) {
		g.scale(sx, sy);
	}

	public void setBackground(Color color) {
		g.setBackground(color);
	}

	public void setClip(int x, int y, int width, int height) {
		g.setClip(x, y, width, height);
	}

	public void setClip(Shape clip) {
		g.setClip(clip);
	}

	public void setColor(Color c) {
		g.setColor(c);
	}

	public void setComposite(Composite comp) {
		g.setComposite(comp);
	}

	public void setFont(Font font) {
		g.setFont(font);
	}

	public void setPaint(Paint paint) {
		g.setPaint(paint);
	}

	public void setPaintMode() {
		g.setPaintMode();
	}

	public void setRenderingHint(Key hintKey, Object hintValue) {
		g.setRenderingHint(hintKey, hintValue);
	}

	public void setRenderingHints(Map<?, ?> hints) {
		g.setRenderingHints(hints);
	}

	public void setStroke(Stroke s) {
		g.setStroke(s);
	}

	public void setTransform(AffineTransform Tx) {
		g.setTransform(Tx);
	}

	public void setXORMode(Color c1) {
		g.setXORMode(c1);
	}

	public void shear(double shx, double shy) {
		g.shear(shx, shy);
	}

	public String toString() {
		return g.toString();
	}

	public void transform(AffineTransform Tx) {
		g.transform(Tx);
	}

	public void translate(double tx, double ty) {
		g.translate(tx, ty);
	}

	public void translate(int x, int y) {
		g.translate(x, y);
	}

	public void drawGlyphVector(GlyphVector g, float x, float y) {
		this.g.drawGlyphVector(g,x,y);
	}

}
*/