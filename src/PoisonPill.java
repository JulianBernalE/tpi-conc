
public class PoisonPill extends Task{
    /*Ni idea is es asi */
    public void run() {
        throw new RuntimeException("mori");
    }

    
}