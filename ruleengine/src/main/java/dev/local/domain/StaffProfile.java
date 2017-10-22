package dev.local.domain;

import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

public class StaffProfile {
    private String id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Staff staff;
}
