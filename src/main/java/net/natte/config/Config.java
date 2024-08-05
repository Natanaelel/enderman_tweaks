package net.natte.config;

public class Config extends MidnightConfig {
    // serverside
    @Entry public static boolean doesWaterHurt = true;
    @Entry public static boolean doesProjectileHit = false;
    @Entry public static boolean doesStareAnger = true;
    @Entry public static boolean canPickUpBlocks = true;
    @Entry public static boolean canPlaceBlocks = true;
    @Entry public static boolean canTeleport = true;
    @Entry public static boolean isPassive = false;
    @Entry public static boolean doesSpawnOnMainIsland = true;
    @Entry public static int mainIslandRadius = 170;
    @Entry public static boolean doesEndermiteAnger = true;

    // clientside
    @Entry public static boolean doesEndermanEmitParticles = true;
    @Entry public static float endermanParticleRed = 0.9f;
    @Entry public static float endermanParticleGreen = 0.3f;
    @Entry public static float endermanParticleBlue = 1.0f;
    @Entry public static boolean doesColorApplyToNetherPortalParticles = false;
    @Entry public static boolean doesColorApplyToAllPortalParticles = false;

}
