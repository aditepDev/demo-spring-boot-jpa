package com.demo.shop.member.constant;

import java.util.HashMap;
import java.util.Map;

public enum MemberMork {

    MEMBER_MORK_BUYER(1L);

    private final Long id;

    private static final Map<Long, MemberMork> lookup = new HashMap<>();

    static {
        for (MemberMork d : MemberMork.values()) {
            lookup.put(d.id, d);
        }
    }

    private MemberMork(Long id) {
        this.id = id;
    }

    public static MemberMork get(Long abbreviation) {
        return lookup.get(abbreviation);
    }



    public Long getId() {
        return id;
    }
}
