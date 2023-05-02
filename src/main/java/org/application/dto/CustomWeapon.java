package org.application.dto;

import java.util.ArrayList;
import java.util.List;

//inheritance from Gun
public class CustomWeapon 
{
    private int id;
    private String nickname;
    private Weapon originalWeapon;
    private List<Attachment> attachments;

    public CustomWeapon(int id ,String nickname,Weapon originalWeapon, List<Attachment> attachments)
    {
        this.id = id;
        this.nickname = nickname;
        this.originalWeapon = originalWeapon;
        this.attachments = attachments;
    }

    public CustomWeapon(int customWeaponId, String customWeaponName, Weapon weapon)
    {
        this.id = customWeaponId;
        this.nickname = customWeaponName;
        this.originalWeapon = weapon;
        this.attachments = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public String getNickname()
    {
        return nickname;
    }

    public Weapon getOriginalWeapon()
    {
        return originalWeapon;
    }

    public List<Attachment> getAttachments()
    {
        return attachments;
    }
    @Override
    public String toString()
    {
        return "CustomWeapon{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", originalWeapon=" + originalWeapon +
                ", attachments=" + attachments +
                '}';
    }

    public void addAttachment(Attachment attachment)
    {
        attachments.add(attachment);
    }
    public int getPrice()
    {
        int price = originalWeapon.getPrice();
        for(Attachment attachment : attachments)
        {
            price += attachment.getPrice();
        }
        return price;
    }
    public float getWeight()
    {
        float weight = originalWeapon.getWeight();
        for(Attachment attachment : attachments)
        {
            weight += attachment.getWeight();
        }
        return weight;
    }
    public int getDamage()
    {
        int damage = originalWeapon.getDamage();
        for(Attachment attachment : attachments)
        {
            damage += attachment.getDamageBonus();
        }
        return damage;
    }
}
