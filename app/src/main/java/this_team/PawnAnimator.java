//app/src/main/java/com/ludo/animation/PawnAnimator.java
package com.ludo.animation;

import org.jogamp.java3d.*;
import org.jogamp.vecmath.*;
import com.ludo.utilities.ObjectManager;

public class PawnAnimator {
    private TransformGroup pawnTG;
    private Transform3D tmpTransform = new Transform3D();
    private Alpha movementAlpha;
    
    public void initAnimator(ObjectManager pawn) {
        pawnTG = pawn.getTransformGroup();
        pawnTG.getTransform(tmpTransform); 
        
        movementAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 
            0, 0,  1000, 0, 0, 1000, 0, 0); 
    }

    public void startMovement(Vector3d endPos) {

        PositionInterpolator animator = new PositionInterpolator(
            movementAlpha, pawnTG, tmpTransform,
            tmpTransform, 0.0f, 1.0f);
            
        animator.setSchedulingBounds(new BoundingSphere());
        pawnTG.addChild(animator);
        movementAlpha.start(); 
    }
}
