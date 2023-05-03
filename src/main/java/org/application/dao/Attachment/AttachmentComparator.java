package org.application.dao.Attachment;

import org.application.dto.Attachment;

import java.util.Comparator;

public class AttachmentComparator
{
    public static final Comparator<Attachment> PRICE_COMPARATOR = new Comparator<Attachment>()
    {
        @Override
        public int compare(Attachment o1, Attachment o2)
        {
            return o1.getPrice() - o2.getPrice();
        }
    };
    public static final Comparator<Attachment> WEIGHT_COMPARATOR= new Comparator<Attachment>()
    {
        @Override
        public int compare(Attachment o1, Attachment o2)
        {
            return (int) (o1.getWeight() - o2.getWeight());
        }
    };
    public static final Comparator<Attachment> DAMAGE_COMPARATOR = new Comparator<Attachment>()
    {
        @Override
        public int compare(Attachment o1, Attachment o2)
        {
            return (int) (o1.getDamageBonus() - o2.getDamageBonus());
        }
    };
}
