package net.minecraft.src;

public interface FCIEntityPacketHandler
{
    Packet GetSpawnPacketForThisEntity();

    int GetTrackerViewDistance();

    int GetTrackerUpdateFrequency();

    boolean GetTrackMotion();

    boolean ShouldServerTreatAsOversized();
}
