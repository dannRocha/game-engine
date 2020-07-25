package src.engine;

import java.awt.image.DataBufferInt;


public class Renderer {

    private int positionWidth, positionHeight;
    private int[] positions;

    public Renderer( Engine container ) 
    {
        positionWidth = container.getWidth();
        positionHeight = container.getHeight();
        positions = (
            (DataBufferInt) container.getWindow()
                .getImage()
                .getRaster()
                .getDataBuffer() )
            .getData();
    }

    
    public void clear()
    {
        for( int i = 0; i < positions.length; i++ )
        {
            // positions[i] += i;
            positions[i] = 0;
        }
    }
}