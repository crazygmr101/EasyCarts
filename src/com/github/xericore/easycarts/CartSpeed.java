package com.github.xericore.easycarts;

import org.bukkit.entity.minecart.RideableMinecart;

class CartSpeed
{
    // Max speed that a minecart can have before it derails in curves or stops on upward slopes
    private static final double MAX_SAFE_DERAIL_SPEED = 0.4D;
    // Max speed that a minecart can have before detection of intersection fails
    private static final double MAX_SAFE_INTERSECTION_SPEED = 1.0D;

    public static void setCartSpeedToAvoidDerailing(RideableMinecart cart)
    {
        setCartSpeed(cart, MAX_SAFE_DERAIL_SPEED);
    }

    public static void setCartSpeedToAvoidMissingIntersection(RideableMinecart cart)
    {
        setCartSpeed(cart, MAX_SAFE_DERAIL_SPEED);
    }

    private static void setCartSpeed(RideableMinecart cart, double maxSpeed)
    {
        cart.setVelocity(cart.getVelocity().clone().normalize().multiply(maxSpeed));
        cart.setMaxSpeed(maxSpeed);
    }

    public static boolean isCartTooFastToDetectIntersection(RideableMinecart cart)
    {
        return cart.getVelocity().length() > MAX_SAFE_INTERSECTION_SPEED;
    }
}
