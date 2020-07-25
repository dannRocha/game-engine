package src.engine;


public class Engine implements Runnable
{
    private Thread thread;
    private Window window;
    private Renderer renderer;
    
    // ENGINE
    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;

    // WINDOW
    private int width, height;
    private float scale = 1f;
    private String title = "Core v0.0.1";

    public Engine()
    {
        this.width = 320;
        this.height = 280;
    }


    public Engine( String title )
    {
        this.title = title;
    }


    public Engine( int width, int height )
    {
        this.width = width;
        this.height = height;
    }


    public Engine( String title, int width, int height )
    {
        this.title = title;
        this.width = width;
        this.height = height;
    }


    public void start(){

        window = new Window( this );
        renderer = new Renderer( this );

        thread = new Thread( this );
        thread.run();
    }


    public void stop(){}
    

    public void run()
    {
        running = true;

        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while( running )
        {

            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;
            frameTime += passedTime;


            while( unprocessedTime >= UPDATE_CAP )
            {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                if( frameTime >= 1.0 )
                {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }

                System.out.println( "FPS: " + fps );
            }


            if( render )
            {

                // TODO : render Game
                renderer.clear();
                window.update();
                frames++;

            }
            else
            {
                try {
                    Thread.sleep(1);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        dispose();
    }
    
    public int getWidth(){ return this.width; }
    public int getHeight(){ return  this.height; }
    public float getScale(){ return this.scale; }
    public String getTitle(){ return this.title; }
    public Window getWindow(){ return this.window; }

    public void setWidth( int width ){ this.width = width; }
    public void setHeight( int height ){ this.height = height; }
    public void setScale( float scale ){ this.scale = scale; }
    public void setTitle( String title ){ this.title = title; }
    public void setWindow(Window window ){ this.window = window; }
    
    public void dispose(){}
}