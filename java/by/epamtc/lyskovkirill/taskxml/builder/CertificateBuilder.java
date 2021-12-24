package by.epamtc.lyskovkirill.taskxml.builder;

import by.epamtc.lyskovkirill.taskxml.bean.Certificate;

import java.util.Date;

public class CertificateBuilder {
    private int number;
    private Date issueDate;
    private Date expiryDate;
    private String organization;

    public CertificateBuilder withNumber(int number) {
        this.number = number;
        return this;
    }

    public CertificateBuilder withIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public CertificateBuilder withExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public CertificateBuilder withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public Certificate build()
    {
        Certificate certificate = new Certificate();
        certificate.setNumber(number);
        certificate.setIssueDate(issueDate);
        certificate.setExpiryDate(expiryDate);
        certificate.setOrganization(organization);
        return certificate;
    }
}
