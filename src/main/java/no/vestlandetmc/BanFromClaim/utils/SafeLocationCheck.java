package no.vestlandetmc.BanFromClaim.utils;

import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class SafeLocationCheck {

    /**
     * Checks if a block is safe to be on (solid with 2 breathable blocks above)
     *
     * @param block Location to check
     * @return True if block is safe
     */
    public static boolean BlockSafetyCheck(Block block) {
        if (!isSolid(block)) return false;
        final Block feet = block.getRelative(BlockFace.UP);
        if (isSolid(feet)) return false;
        final Block head = feet.getRelative(BlockFace.UP);
        if (isSolid(head)) return false;
        if (!isSolid(block.getRelative(BlockFace.DOWN)))
            return false;

        final WorldBorder worldBorder = block.getWorld().getWorldBorder();
        return worldBorder.isInside(block.getLocation());
    }

    private static boolean isSolid(Block block) {
        if (block.isLiquid()) return false;
        else return !block.isEmpty();
    }
}
