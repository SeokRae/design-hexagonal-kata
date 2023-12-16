package com.example.chapter2.domain.vo;

/**
 * IP 클래스는 IP 주소를 나타냅니다.
 */
public class IP {

    private final String address;
    private final Protocol protocol;

    private IP(String address) {
        if (address == null)
            throw new IllegalArgumentException("Null IP address");
        this.address = address;
        if (address.length() <= 15) {
            this.protocol = Protocol.IPV4;
        } else {
            this.protocol = Protocol.IPV6;
        }
    }

    // Factory method
    public static IP withAddress(String address) {
        return new IP(address);
    }

    @Override
    public String toString() {
        return "IP{" +
                "address='" + address + '\'' +
                ", protocol=" + protocol +
                '}';
    }
}
