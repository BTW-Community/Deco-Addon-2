package net.minecraft.src;

import java.awt.Component;
import org.lwjgl.input.Mouse;

public class MouseHelper
{
    private final Component windowComponent;
    private final GameSettings field_85184_d;

    /** Mouse delta X this frame */
    public int deltaX;

    /** Mouse delta Y this frame */
    public int deltaY;

    public MouseHelper(Component par1Component, GameSettings par2GameSettings)
    {
        this.windowComponent = par1Component;
        this.field_85184_d = par2GameSettings;
    }

    /**
     * Grabs the mouse cursor it doesn't move and isn't seen.
     */
    public void grabMouseCursor()
    {
        Mouse.setGrabbed(true);
        this.deltaX = 0;
        this.deltaY = 0;
    }

    /**
     * Ungrabs the mouse cursor so it can be moved and set it to the center of the screen
     */
    public void ungrabMouseCursor()
    {
        int var1 = this.windowComponent.getWidth();
        int var2 = this.windowComponent.getHeight();

        if (this.windowComponent.getParent() != null)
        {
            var1 = this.windowComponent.getParent().getWidth();
            var2 = this.windowComponent.getParent().getHeight();
        }

        Mouse.setCursorPosition(var1 / 2, var2 / 2);
        Mouse.setGrabbed(false);
    }

    public void mouseXYChange()
    {
        this.deltaX = Mouse.getDX();
        this.deltaY = Mouse.getDY();
    }
}
