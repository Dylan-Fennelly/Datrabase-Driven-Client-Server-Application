package org.application.dto;

public class Attachment
{
    private int id;
    private String name;
    private String type;
    private float weight;
    private float accuracyBonus;
    private float recoilBonus;
    private float damageBonus;
    private float rateOfFireBonus;
    private float reloadTimeBonus;
    private int price;

public Attachment(int id, String name, String type, float weight, float accuracyBonus, float recoilBonus, float damageBonus, float rateOfFireBonus, float reloadTimeBonus, int price)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.accuracyBonus = accuracyBonus;
        this.recoilBonus = recoilBonus;
        this.damageBonus = damageBonus;
        this.rateOfFireBonus = rateOfFireBonus;
        this.reloadTimeBonus = reloadTimeBonus;
        this.price = price;
    }
    public Attachment(String name, String type, float weight, float accuracyBonus, float recoilBonus, float damageBonus, float rateOfFireBonus, float reloadTimeBonus, int price)
    {
        this.id = -1;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.accuracyBonus = accuracyBonus;
        this.recoilBonus = recoilBonus;
        this.damageBonus = damageBonus;
        this.rateOfFireBonus = rateOfFireBonus;
        this.reloadTimeBonus = reloadTimeBonus;
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

    public float getWeight()
    {
        return weight;
    }

    public float getAccuracyBonus()
    {
        return accuracyBonus;
    }

    public float getRecoilBonus()
    {
        return recoilBonus;
    }

    public float getDamageBonus()
    {
        return damageBonus;
    }

    public float getRateOfFireBonus()
    {
        return rateOfFireBonus;
    }

    public float getReloadTimeBonus()
    {
        return reloadTimeBonus;
    }

    public int getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Attachment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", accuracyBonus=" + accuracyBonus +
                ", recoilBonus=" + recoilBonus +
                ", damageBonus=" + damageBonus +
                ", rateOfFireBonus=" + rateOfFireBonus +
                ", reloadTimeBonus=" + reloadTimeBonus +
                ", price=" + price +
                '}';
    }


}
