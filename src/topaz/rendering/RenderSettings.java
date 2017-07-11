package topaz.rendering;

import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import topaz.core.Display;

public class RenderSettings {

    private static int vSync = 1;
    private static int numSamples = 4;
    private static boolean visible = true;

    public static void setZBuffer(boolean zBuffer) {
        if (zBuffer) {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        } else {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }
    }

    public static void setMultisampling(boolean multisampling) {
        if (multisampling) {
            GL11.glEnable(GL13.GL_MULTISAMPLE);
        } else {
            GL11.glDisable(GL13.GL_MULTISAMPLE);
        }
    }

    public static void setFaceCulling(boolean faceCulling) {
        if (faceCulling) {
            GL11.glEnable(GL11.GL_CULL_FACE);
        } else {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
    }

    public static void setVSync(int newVSync) {
        vSync = newVSync;
        GLFW.glfwSwapInterval(vSync);
    }

    public static int getVSync() {
        return vSync;
    }

    public static void setDisplayVisible(boolean isVisible) {
        if (isVisible) {
            GLFW.glfwShowWindow(Display.getWindowID());
            visible = true;
        } else {
            GLFW.glfwShowWindow(0);
            visible = false;
        }
    }

    public static boolean isDisplayVisible() {
        return visible;
    }

    public static int getNumSamples() {
        return numSamples;
    }

    public static void setDisplayTitle(String title) {
        Display.setTitle(title);
    }

    public static String getDisplayTitle() {
        return Display.getTitle();
    }

    public static void setDisplaySize(int width, int height) {
        GLFW.glfwSetWindowSize(Display.getWindowID(), width, height);
    }

    public static void setDisplayWidth(int width) {
        GLFW.glfwSetWindowSize(Display.getWindowID(), width, getDisplayHeight());
    }

    public static void setDisplayHeight(int height) {
        GLFW.glfwSetWindowSize(Display.getWindowID(), getDisplayWidth(), height);
    }

    public static int getDisplayWidth() {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(Display.getWindowID(), widthBuffer, heightBuffer);
        return widthBuffer.get(0);
    }

    public static int getDisplayHeight() {
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(Display.getWindowID(), widthBuffer, heightBuffer);
        return heightBuffer.get(0);
    }
}