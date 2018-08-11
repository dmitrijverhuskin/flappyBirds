package tanks.com.thebuteguru.display.game;


import graphics.TecstureAtlas;
import tanks.com.thebuteguru.display.Display;
import tanks.com.thebuteguru.display.Input;
import tanks.com.thebuteguru.display.utils.Time;



public class Game implements Runnable{
    
// int width, int height, String title,int _clearColor, int numBuffers
    
    public static final int WIDTH = 1200;
    public static final int HEIGHT =980;
    public static String TITLE = "Tanks";
    public static int CLEAR_COLOR = 0xff696969;
    public static int NUM_BUFFERS = 3;
    
    public static final float UPDATE_RATE =60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDLE_TIME = 1;
    
    public static final String ATLAS_FILE_NAME = "texture_atlas.png";
    
    private boolean running;
    private Thread gameThread;
    private Input input;
    private TecstureAtlas atlas;
    
    public Game(){
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        input =new Input();
        Display.addInputListener(input);
        
        atlas = new TecstureAtlas(ATLAS_FILE_NAME);
        
    }
    
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    
    public synchronized void stop(){
        if(!running)
            return;
        try{
            gameThread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        cleanUp();
    }
    
    private void update(){
        
    }
    
    private void render(){
        Display.clear();
        
        graphics.drawImage(atlas.cut(0, 0, 32, 32),300, 300, null);
        Display.swapBuffers();
        
        
    }
    
    
    
    @Override
    public void run(){
        
        int fps = 0;
        int upd = 0;
        int updl = 0;
        
        long count = 0;
        
        float delta = 0;
        
        long lastTime = Time.get();
        while (running){
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime= now;
            
            count += elapsedTime;
            
            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while(delta > 1){
                update();
                upd++;
                delta--;
                if(render){
                    updl++;
                }else{
                render = true;
                }
            }
            
            if(render){
                render();
                fps++;
            }else{
                try {
                    Thread.sleep(IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            if(count>= Time.SECOND){
                Display.setTitle(TITLE + " || Fps: " + fps +" | Upd: " + upd + "| Updl: " + updl);
                upd = 0;
                fps = 0;
                updl = 0;
                count = 0;
            }
            
        }
    }
    
    
    private void cleanUp(){
        Display.destroy();
    }
    
    
}
