package net.minecraft.src;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FCModelBlock extends FCUtilsPrimitiveGeometric
{
    private List m_primitiveList;
    private int m_iAssemblyID;
    private int m_iActivePrimitiveID;

    public FCModelBlock()
    {
        this.m_iAssemblyID = -1;
        this.m_iActivePrimitiveID = -1;
        this.m_primitiveList = new LinkedList();
        this.InitModel();
    }

    public FCModelBlock(int var1)
    {
        this();
        this.SetAssemblyID(var1);
    }

    public FCModelBlock MakeTemporaryCopy()
    {
        FCModelBlock var1 = new FCModelBlock(this.GetAssemblyID());
        this.MakeTemporaryCopyOfPrimitiveList(var1);
        return var1;
    }

    public void RotateAroundJToFacing(int var1)
    {
        if (var1 > 2)
        {
            Iterator var2 = this.m_primitiveList.iterator();

            while (var2.hasNext())
            {
                ((FCUtilsPrimitiveGeometric)var2.next()).RotateAroundJToFacing(var1);
            }
        }
    }

    public void TiltToFacingAlongJ(int var1)
    {
        if (var1 != 1)
        {
            Iterator var2 = this.m_primitiveList.iterator();

            while (var2.hasNext())
            {
                ((FCUtilsPrimitiveGeometric)var2.next()).TiltToFacingAlongJ(var1);
            }
        }
    }

    public void Translate(double var1, double var3, double var5)
    {
        Iterator var7 = this.m_primitiveList.iterator();

        while (var7.hasNext())
        {
            ((FCUtilsPrimitiveGeometric)var7.next()).Translate(var1, var3, var5);
        }
    }

    public void AddToRayTrace(FCUtilsRayTraceVsComplexBlock var1)
    {
        Iterator var2 = this.m_primitiveList.iterator();

        while (var2.hasNext())
        {
            ((FCUtilsPrimitiveGeometric)var2.next()).AddToRayTrace(var1);
        }
    }

    public void AddIntersectingBoxesToCollisionList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6)
    {
        Iterator var7 = this.m_primitiveList.iterator();

        while (var7.hasNext())
        {
            ((FCUtilsPrimitiveGeometric)var7.next()).AddIntersectingBoxesToCollisionList(var1, var2, var3, var4, var5, var6);
        }
    }

    public int GetAssemblyID()
    {
        return this.m_iAssemblyID;
    }

    protected void InitModel() {}

    public void MakeTemporaryCopyOfPrimitiveList(FCModelBlock var1)
    {
        Iterator var2 = this.m_primitiveList.iterator();

        while (var2.hasNext())
        {
            var1.m_primitiveList.add(((FCUtilsPrimitiveGeometric)var2.next()).MakeTemporaryCopy());
        }
    }

    public void AddPrimitive(FCUtilsPrimitiveGeometric var1)
    {
        this.m_primitiveList.add(var1);
    }

    public void AddBox(double var1, double var3, double var5, double var7, double var9, double var11)
    {
        this.m_primitiveList.add(new AxisAlignedBB(var1, var3, var5, var7, var9, var11));
    }

    public MovingObjectPosition CollisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        this.AddToRayTrace(var7);
        return var7.GetFirstIntersection();
    }

    public int GetActivePrimitiveID()
    {
        return this.m_iActivePrimitiveID;
    }

    public void SetAssemblyID(int var1)
    {
        this.m_iAssemblyID = var1;
    }
}
