package org.application.dto;

import java.util.ArrayList;

//inheritance from Gun
public class CustomGun extends Weapon
{
    private int id;
    private String nickname;
    private ArrayList<Attachment> attachments;

    public CustomGun(int id, String name, String type, int ammoCapacity, int damage, int rateOfFire, float weight, float reloadTime, float accuracy, int range, float recoil, int attachmentSlots, int price, String nickname, ArrayList<Attachment> attachments)
    {
        super(id, name, type, ammoCapacity, damage, rateOfFire, weight, reloadTime, accuracy, range, recoil, attachmentSlots, price);
        this.id = id;
        this.nickname = nickname;
        this.attachments = attachments;
    }

}
