package org.application.dao.CustomWeapon;

import org.application.dto.CustomWeapon;

import java.util.Comparator;

public class CustomWeaponComparator
{
    public static final Comparator<CustomWeapon> PRICE_COMPARATOR = new Comparator<CustomWeapon>()
    {
        @Override
        public int compare(CustomWeapon o1, CustomWeapon o2)
        {
            return o1.getPrice() - o2.getPrice();
        }
    };
    public static final Comparator<CustomWeapon> DAMAGE_COMPARATOR= new Comparator<CustomWeapon>()
    {
        @Override
        public int compare(CustomWeapon o1, CustomWeapon o2)
        {
            return o1.getDamage() - o2.getDamage();
        }
    };
    public static final Comparator<CustomWeapon> WEIGHT_COMPARATOR= new Comparator<CustomWeapon>()
    {
        @Override
        public int compare(CustomWeapon o1, CustomWeapon o2)
        {
            return (int) (o1.getWeight() - o2.getWeight());
        }
    };

}
