package com.github.xericore.easycarts.data;

import com.github.xericore.easycarts.utilities.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rail;
import org.bukkit.util.Vector;

public class NextBlockWithDirection
{
    private static final double Degrees90FromRad = Math.PI/2;

    private BlockFace _nextFacing;
    private Block _nextBlock;

    public NextBlockWithDirection(Block lastBlock, BlockFace previousFacing)
    {
        Vector previousDirection = Utils.getDirectionFromBlockFace(previousFacing);
        Location nextLocation = lastBlock.getLocation().clone().add(previousDirection);
        _nextBlock = nextLocation.getBlock();

        if(_nextBlock.getBlockData().getMaterial() != Material.RAIL)
        {
            _nextFacing = null;
            return;
        }

        Rail nextRail = (Rail) lastBlock.getBlockData();
        Rail.Shape nextRailShape = nextRail.getShape();


    }

    public BlockFace getNextFacing()
    {
        return _nextFacing;
    }

    public Block getNextBlock()
    {
        return _nextBlock;
    }

    public static BlockFace getNextFacingFromRailShape(Rail.Shape currentRailShape, BlockFace previousFacing)
    {
        double rotateAround = 0d;

        switch (currentRailShape)
        {
            case NORTH_SOUTH:
            case EAST_WEST:
            case ASCENDING_EAST:
            case ASCENDING_WEST:
            case ASCENDING_NORTH:
            case ASCENDING_SOUTH:
                return previousFacing;
            case SOUTH_EAST:
                if(previousFacing == BlockFace.WEST)
                    rotateAround = Degrees90FromRad;
                else if (previousFacing == BlockFace.NORTH)
                    rotateAround = -Degrees90FromRad;
                else
                    return null;
                break;
            case SOUTH_WEST:
                if(previousFacing == BlockFace.NORTH)
                    rotateAround = Degrees90FromRad;
                else if (previousFacing == BlockFace.EAST)
                    rotateAround = -Degrees90FromRad;
                else
                    return null;
                break;
            case NORTH_WEST:
                if(previousFacing == BlockFace.EAST)
                    rotateAround = Degrees90FromRad;
                else if (previousFacing == BlockFace.SOUTH)
                    rotateAround = -Degrees90FromRad;
                else
                    return null;
                break;
            case NORTH_EAST:
                if(previousFacing == BlockFace.SOUTH)
                    rotateAround = Degrees90FromRad;
                else if (previousFacing == BlockFace.WEST)
                    rotateAround = -Degrees90FromRad;
                else
                    return null;
                break;
        }

        Vector previousDirection = Utils.getDirectionFromBlockFace(previousFacing);
        Vector newDirection = previousDirection.clone().rotateAroundY(rotateAround);
        return Utils.getBlockFaceFromDirection(newDirection);
    }
}
