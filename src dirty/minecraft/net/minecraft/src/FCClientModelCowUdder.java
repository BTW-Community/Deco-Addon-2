package net.minecraft.src;

public class FCClientModelCowUdder extends ModelQuadruped
{
    public FCClientModelCowUdder()
    {
        super(12, 0.0F);
        this.body = new ModelRenderer(this, 18, 4);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.body.setTextureOffset(50, 0);
        this.body.addBox(-2.0F, 2.0F, -11.0F, 4, 6, 3);
    }
}
