package org.application.dao.Weapon;

import org.application.dto.Weapon;

import java.util.Comparator;

public class WeaponComparator
{
    public static final Comparator<Weapon> PRICE_COMPARATOR = new Comparator<Weapon>()
    {
        @Override
        public int compare(Weapon o1, Weapon o2)
        {
            return o1.getPrice() - o2.getPrice();
        }
    };
    public static final Comparator<Weapon> DAMAGE_COMPARATOR= new Comparator<Weapon>()
    {
        @Override
        public int compare(Weapon o1, Weapon o2)
        {
            return o1.getDamage() - o2.getDamage();
        }
    };
    public static final Comparator<Weapon> WEIGHT_COMPARATOR = new Comparator<Weapon>()
    {
        @Override
        public int compare(Weapon o1, Weapon o2)
        {
            return (int) (o1.getWeight() - o2.getWeight());
        }
    };
}
