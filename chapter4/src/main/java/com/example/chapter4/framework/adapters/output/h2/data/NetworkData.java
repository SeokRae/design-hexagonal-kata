package com.example.chapter4.framework.adapters.output.h2.data;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;

import java.io.Serializable;
import java.util.UUID;


@Getter
@Entity
@Table(name = "networks")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@Converter(name = "uuidConverter", converterClass = UUIDTypeConverter.class)
public class NetworkData implements Serializable {

    @Column(name = "switch_id")
    @Convert("uuidConverter")
    private UUID switchId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "address",
                    column = @Column(
                            name = "network_address")),
            @AttributeOverride(
                    name = "protocol",
                    column = @Column(
                            name = "network_protocol")),
    })
    IPData ip;
    @Column(name = "network_name")
    String name;
    @Column(name = "network_cidr")
    Integer cidr;
    @Id
    @Column(name = "network_id")
    private int id;

    public NetworkData(UUID switchId, IPData ip, String name, Integer cidr) {
        this.switchId = switchId;
        this.ip = ip;
        this.name = name;
        this.cidr = cidr;
    }
}
