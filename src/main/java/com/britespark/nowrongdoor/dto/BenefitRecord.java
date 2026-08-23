package com.britespark.nowrongdoor.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class BenefitRecord {

    @JacksonXmlProperty(localName = "Ref")
    private String ref;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "Born")
    private String born;

    @JacksonXmlProperty(localName = "Addr")
    private String address;

    @JacksonXmlProperty(localName = "Town")
    private String town;

    @JacksonXmlProperty(localName = "BenefitCode")
    private String benefitCode;

    @JacksonXmlProperty(localName = "ReviewDue")
    private String reviewDue;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getBenefitCode() {
        return benefitCode;
    }

    public void setBenefitCode(String benefitCode) {
        this.benefitCode = benefitCode;
    }

    public String getReviewDue() {
        return reviewDue;
    }

    public void setReviewDue(String reviewDue) {
        this.reviewDue = reviewDue;
    }
}
