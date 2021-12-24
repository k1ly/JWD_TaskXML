package by.epamtc.lyskovkirill.taskxml.bean;

import java.io.Serializable;
import java.util.Date;

public class Certificate implements Serializable {
    private int number;
    private Date issueDate;
    private Date expiryDate;
    private String organization;

    public Certificate() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "number=" + number +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                ", organization='" + organization + '\'' +
                '}';
    }
}
