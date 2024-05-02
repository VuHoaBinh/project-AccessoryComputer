package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FixButton extends JButton {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean isPressed = false;
    private boolean isHover = false;
    private boolean mouseHoverEnable = true;
    private Color backgroundColor = new Color(0, 187, 187);
    private int borderRadius = 15;

    public FixButton() {
        super();
        settingButton();
    }

    public FixButton(String text) {
        super(text);

        settingButton();
    }
    public FixButton(String text,String imagePath, int desiredWidth, int desiredHeight)
    {
    	 super(text);

         settingButton();
    	try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();
            double aspectRatio = (double) originalWidth / originalHeight;

            int scaledWidth = (int) (desiredHeight * aspectRatio);
            int scaledHeight = desiredHeight;

            if (scaledWidth > desiredWidth) {
                scaledWidth = desiredWidth;
                scaledHeight = (int) (desiredWidth / aspectRatio);
            }

            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    private void settingButton() {
    	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(true);
        setContentAreaFilled(false);
        setForeground(Color.white);
       

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mousePressed(e);
                isPressed = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseEntered(e);
                if (mouseHoverEnable) {
                	isHover = true;
				}
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseExited(e);
                isHover = false;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                super.mouseReleased(e);
                isPressed = false;
            }
        });
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    @Override
    public void setBackground(Color bg) {
        backgroundColor = bg;
    }
    
    public void setMouseHoverEnable(boolean mouseHoverEnable) {
        this.mouseHoverEnable = mouseHoverEnable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g.create();

        Color color = backgroundColor;

        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        
        if (isHover) {
            color = backgroundColor.brighter();
            if (isPressed) {
                color = backgroundColor.darker();
            }
        }

        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2d.setColor(color);

        graphics2d.fill(shape);
        graphics2d.dispose();

        super.paintComponent(g);

    }

    
}
