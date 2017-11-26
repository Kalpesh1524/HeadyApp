package com.example.admin.volleywithheaders;

/**
 * Created by admin on 6/13/17.
 */

public class MyPojo {

    private String Phone;

    private String PostalCode;

    private String Region;

    private String ContactName;

    private String Fax;

    private String Address;

    private String CustomerID;

    private String CompanyName;

    private String Country;

    private String City;

    private String ContactTitle;

    public String getPhone ()
    {
        return Phone;
    }

    public void setPhone (String Phone)
    {
        this.Phone = Phone;
    }

    public String getPostalCode ()
    {
        return PostalCode;
    }

    public void setPostalCode (String PostalCode)
    {
        this.PostalCode = PostalCode;
    }

    public String getRegion ()
    {
        return Region;
    }

    public void setRegion (String Region)
    {
        this.Region = Region;
    }

    public String getContactName ()
    {
        return ContactName;
    }

    public void setContactName (String ContactName)
    {
        this.ContactName = ContactName;
    }

    public String getFax ()
    {
        return Fax;
    }

    public void setFax (String Fax)
    {
        this.Fax = Fax;
    }

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
    }

    public String getCustomerID ()
    {
        return CustomerID;
    }

    public void setCustomerID (String CustomerID)
    {
        this.CustomerID = CustomerID;
    }

    public String getCompanyName ()
    {
        return CompanyName;
    }

    public void setCompanyName (String CompanyName)
    {
        this.CompanyName = CompanyName;
    }

    public String getCountry ()
    {
        return Country;
    }

    public void setCountry (String Country)
    {
        this.Country = Country;
    }

    public String getCity ()
    {
        return City;
    }

    public void setCity (String City)
    {
        this.City = City;
    }

    public String getContactTitle ()
    {
        return ContactTitle;
    }

    public void setContactTitle (String ContactTitle)
    {
        this.ContactTitle = ContactTitle;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Phone = "+Phone+", PostalCode = "+PostalCode+", Region = "+Region+", ContactName = "+ContactName+", Fax = "+Fax+", Address = "+Address+", CustomerID = "+CustomerID+", CompanyName = "+CompanyName+", Country = "+Country+", City = "+City+", ContactTitle = "+ContactTitle+"]";
    }
}
