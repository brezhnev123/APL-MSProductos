package com.brez.productos.utils;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeadersInType", propOrder = {
        "country",
        "lang",
        "entity",
        "system",
        "subsystem",
        "originator",
        "sender",
        "userId",
        "wsId",
        "wsIp",
        "wsIpv6",
        "operation",
        "destination",
        "pid",
        "execId",
        "msgId",
        "timestamp",
        "msgType",
        "varArg"
})
@Data
@XmlRootElement(name ="HeaderIn")
public class HeadersInType {

    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    protected String lang;
    @XmlElement(required = true)
    protected String entity;
    @XmlElement(required = true)
    protected String system;
    @XmlElement(required = true)
    protected String subsystem;
    @XmlElement(required = true)
    protected String originator;
    protected String sender;
    @XmlElement(required = true)
    protected String userId;
    protected String wsId;
    protected String wsIp;
    protected String wsIpv6;
    @XmlElement(required = true)
    protected String operation;
    @XmlElement(required = true)
    protected String destination;
    protected String pid;
    @XmlElement(required = true)
    protected String execId;
    protected String msgId;
    @XmlElement(required = true)
    protected String timestamp;
    protected String msgType;



    @Override
    public String toString() {
        return "HeaderInType [country=" + country + ", lang=" + lang + ", entity=" + entity + ", system=" + system
                + ", subsystem=" + subsystem + ", originator=" + originator + ", sender=" + sender + ", userId="
                + userId + ", wsId=" + wsId + ", wsIp=" + wsIp + ", wsIpv6=" + wsIpv6 + ", operation=" + operation
                + ", destination=" + destination + ", pid=" + pid + ", execId=" + execId + ", msgId=" + msgId
                + ", timestamp=" + timestamp + ", msgType=" + msgType + "]";
    }

}
