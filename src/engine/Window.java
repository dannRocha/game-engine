package src.engine;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;



import javax.swing.JFrame;

public class Window 
{
    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private Graphics g;
    private BufferStrategy bs;
    

    public Window(Engine container)
    {
        image = new BufferedImage(
            container.getWidth(),
            container.getHeight(),
            BufferedImage.TYPE_INT_RGB 
        );

        canvas = new Canvas();

        Dimension s = new Dimension(
           ( int )( container.getWidth() * container.getScale() ), 
           ( int )( container.getHeight() * container.getScale() )
        );

        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);


        frame = new JFrame(container.getTitle());
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setLayout( new BorderLayout() );
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }
    
    
    public void update()
    {
        g.drawImage( image, 0, 0,
            canvas.getWidth(), canvas.getHeight(),
            null
        ); 
        
        bs.show();
    }

    public Canvas getCanvas(){ return this.canvas; }
    public BufferedImage getImage(){ return this.image; } 

    public void setCanvas( Canvas canvas ){ this.canvas = canvas; }
}