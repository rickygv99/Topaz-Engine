package topaz.game;

import org.joml.Vector3f;
import topaz.input.KeyManager;
import topaz.physics.PhysicsObject;
import topaz.rendering.Camera;

public class BasicPlayer {

    private KeyManager keyManager;
    private Camera camera;

    private PhysicsObject physicalObject;
    private float jumpVelocity;
    private float moveSpeed;
    private Vector3f addedLinearVelocity;

    private boolean useDefaultInput = true;

    private int health;

    public BasicPlayer(KeyManager keyManager, Camera camera, PhysicsObject physicalObject) {
        this.keyManager = keyManager;
        this.camera = camera;

        this.physicalObject = physicalObject;
        this.physicalObject.getCollisionObject().setCenter(camera.getLocation());
        this.physicalObject.getCollisionObject().setActive(true);
        this.physicalObject.setAccelerationFromGravity(-0.0008f);
        this.physicalObject.setGravityEnabled(true);

        addedLinearVelocity = new Vector3f(0, 0, 0);
        jumpVelocity = 0.025f;
        moveSpeed = 0.009f;

        health = 10;
    }

    public void tick(double delta) {
        if (useDefaultInput) {
            physicalObject.addLinearVelocity(new Vector3f(addedLinearVelocity).negate());
            addedLinearVelocity = new Vector3f(0, 0, 0);

            if (keyManager.KEY_W.isPressed()) {
                move(camera.getForward().mul(moveSpeed));
            } else if (keyManager.KEY_S.isPressed()) {
                move(camera.getBackward().mul(moveSpeed));
            }
            if (keyManager.KEY_A.isPressed()) {
                move(camera.getLeft().mul(moveSpeed));
            } else if (keyManager.KEY_D.isPressed()) {
                move(camera.getRight().mul(moveSpeed));
            }
            if (keyManager.KEY_SPACE.isPressed()) {
                jump();
            }

            physicalObject.addLinearVelocity(addedLinearVelocity);
        }

        camera.setLocation(physicalObject.getCollisionObject().getCenter());
    }

    public void move(Vector3f translation) {
        addedLinearVelocity.add(new Vector3f(translation.x, 0, translation.z));
    }

    public void jump() {
        if (physicalObject.getLinearVelocity().y != 0) {
            return;
        }
        physicalObject.getLinearVelocity().y = jumpVelocity;
    }

    public void enableDefaultInput(boolean toggle) {
        useDefaultInput = toggle;
    }

    public void setLocation(float x, float y, float z) {
        physicalObject.getCollisionObject().setLocation(x, y, z);
        camera.setLocation(physicalObject.getCollisionObject().getCenter());
    }

    public Vector3f getLocation() {
        return physicalObject.getCollisionObject().getLocation();
    }

    public float getJumpVelocity() {
        return jumpVelocity;
    }

    public void setJumpVelocity(float jumpVelocity) {
        this.jumpVelocity = jumpVelocity;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
