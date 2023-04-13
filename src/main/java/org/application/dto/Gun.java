package org.application.dto;

public class Gun
{
    private int id;
    private String name;
    private String type;
    private int ammoCapacity;
    private float damage;
    private float rateOfFire;
    private float weight;
    private float reloadTime;
    private float accuracy;
    private float recoil;
    private int attachmentSlots;
    private int price;

    public Gun(int id, String Name, String Type, int ammoCapacity, float damage, float rateOfFire, float weight, float reloadTime, float accuracy, float recoil, int attachmentSlots, int price)
    {
        this.id = id;
        this.name = Name;
        this.type = Type;
        this.ammoCapacity = ammoCapacity;
        this.damage = damage;
        this.rateOfFire = rateOfFire;
        this.weight = weight;
        this.reloadTime = reloadTime;
        this.accuracy = accuracy;
        this.recoil = recoil;
        this.attachmentSlots = attachmentSlots;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int getAmmoCapacity()
    {
        return ammoCapacity;
    }

    public float getDamage()
    {
        return damage;
    }

    public float getRateOfFire()
    {
        return rateOfFire;
    }

    public float getWeight()
    {
        return weight;
    }

    public float getReloadTime()
    {
        return reloadTime;
    }

    public float getAccuracy()
    {
        return accuracy;
    }

    public float getRecoil()
    {
        return recoil;
    }

    public int getAttachmentSlots()
    {
        return attachmentSlots;
    }

    public int getPrice()
    {
        return price;
    }
}
