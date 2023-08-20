package com.mindfulengineering.travelbooking.util;

import com.mindfulengineering.travelbooking.domain.TravelTicket;
import java.util.Comparator;

public class OriginSortComparator implements Comparator<TravelTicket> {

    @Override
    public int compare(TravelTicket t, TravelTicket t1) {
        return t.getOrigin().compareTo(t1.getOrigin());
    }
    
}
