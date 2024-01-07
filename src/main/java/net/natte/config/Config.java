package net.natte.config;


public class Config extends MidnightConfig {
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
}