package this_team;
//app/src/main/java/com/ludo/core/GameController.java 
public class GameController implements KeyListener {

    private PawnAnimator pawnAnimator = new PawnAnimator();
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Vector3d targetPos = new Vector3d(0.13f, 0.05f, -0.264f); 
            pawnAnimator.startMovement(targetPos); 
        }
    }
}
