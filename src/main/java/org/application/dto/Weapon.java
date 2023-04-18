package org.application.dto;

public class Weapon
{
    private int id;
    private String name;
    private String type;
    private int ammoCapacity;
    private int damage;
    private int rateOfFire;
    private float weight;
    private float reloadTime;
    private float accuracy;
    private int range;
    private float recoil;
    private int attachmentSlots;
    private int price;

    public Weapon(int id, String name, String type, int ammoCapacity, int damage, int rateOfFire, float weight, float reloadTime, float accuracy, int range, float recoil, int attachmentSlots, int price)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.ammoCapacity = ammoCapacity;
        this.damage = damage;
        this.rateOfFire = rateOfFire;
        this.weight = weight;
        this.reloadTime = reloadTime;
        this.accuracy = accuracy;
        this.range = range;
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

    public int getRateOfFire()
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

    //To string
    @Override
    public String toString()
    {
        return "Gun{" + "id=" + id + ", name=" + name + ", type=" + type + ", ammoCapacity=" + ammoCapacity + ", damage=" + damage + ", rateOfFire=" + rateOfFire + ", weight=" + weight + ", reloadTime=" + reloadTime + ", accuracy=" + accuracy + ", range=" + range + ", recoil=" + recoil + ", attachmentSlots=" + attachmentSlots + ", price=" + price + '}';
    }
}
