
import org.jogamp.java3d.*;
import org.jogamp.java3d.utils.picking.PickIntersection;
import org.jogamp.java3d.utils.picking.PickResult;
import org.jogamp.java3d.utils.picking.PickTool;
import org.jogamp.vecmath.Point3d;
import javax.swing.Timer;
import java.awt.event.*;

public class PawnSelector {
    private static final double PICK_RADIUS = 0.15; 
    private TransformGroup selectedPawnTG;
    private final Canvas3D canvas;
    
    public PawnSelector(Canvas3D canvas3D, BranchGroup sceneBG) {
        this.canvas = canvas3D;
        setup_Picking_Environment(sceneBG);
    }

    private void setup_Picking_Environment(BranchGroup scene) {
        scene.setPickable(true);
        scene.setCapability(BranchGroup.ALLOW_PICKABLE_READ);
        PickTool.setCapabilities(scene, PickTool.INTERSECT_FULL);

        canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point3d clickPt = new Point3d(e.getX(), e.getY(), PICK_RADIUS);
                Transform3D viewTransform = new Transform3D();
                canvas.getCenterEyeInImagePlate(viewTransform);
                
                PickTool picker = new PickTool(canvas.getSceneGraph());
                picker.setMode(PickTool.BOUNDS);
                PickResult[] results = picker.pickAllSorted(clickPt);
                handle_Pick_Results(results);
            }
        });
    }

    private void handle_Pick_Results(PickResult[] pickResults) {
        if (pickResults == null || pickResults.length == 0) return;

        for (PickResult result : pickResults) {
            Node objNode = result.getObject();
            if (objNode.getUserData() instanceof String tag && tag.equals("pawn")) {
                highlight_Selected_Pawn((TransformGroup) objNode.getParent());
                return;
            }
        }
    }

    private void highlight_Selected_Pawn(TransformGroup pawnTG) {
        if (selectedPawnTG != null) {
            MaterialManager.reset_Appearance(selectedPawnTG);
        }
        selectedPawnTG = pawnTG;
        MaterialManager.set_Highlight_Effect(pawnTG);
    }
}
