package net.minecraft.src;

import java.util.Comparator;

class TimerTaskMcoServerListUpdateComparator implements Comparator
{
    private final String field_102024_b;

    final TimerTaskMcoServerListUpdate field_102025_a;

    private TimerTaskMcoServerListUpdateComparator(TimerTaskMcoServerListUpdate par1TimerTaskMcoServerListUpdate, String par2Str)
    {
        this.field_102025_a = par1TimerTaskMcoServerListUpdate;
        this.field_102024_b = par2Str;
    }

    public int func_102023_a(McoServer par1McoServer, McoServer par2McoServer)
    {
        if (par1McoServer.field_96405_e.equals(par2McoServer.field_96405_e))
        {
            return par1McoServer.field_96408_a < par2McoServer.field_96408_a ? 1 : (par1McoServer.field_96408_a > par2McoServer.field_96408_a ? -1 : 0);
        }
        else if (par1McoServer.field_96405_e.equals(this.field_102024_b))
        {
            return -1;
        }
        else if (par2McoServer.field_96405_e.equals(this.field_102024_b))
        {
            return 1;
        }
        else
        {
            if (par1McoServer.field_96404_d.equals("CLOSED") || par2McoServer.field_96404_d.equals("CLOSED"))
            {
                if (par1McoServer.field_96404_d.equals("CLOSED"))
                {
                    return 1;
                }

                if (par2McoServer.field_96404_d.equals("CLOSED"))
                {
                    return 0;
                }
            }

            return par1McoServer.field_96408_a < par2McoServer.field_96408_a ? 1 : (par1McoServer.field_96408_a > par2McoServer.field_96408_a ? -1 : 0);
        }
    }

    public int compare(Object par1Obj, Object par2Obj)
    {
        return this.func_102023_a((McoServer)par1Obj, (McoServer)par2Obj);
    }

    TimerTaskMcoServerListUpdateComparator(TimerTaskMcoServerListUpdate par1TimerTaskMcoServerListUpdate, String par2Str, McoServerListINNER1 par3McoServerListINNER1)
    {
        this(par1TimerTaskMcoServerListUpdate, par2Str);
    }
}
