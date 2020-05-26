package com.kitaa.startup.models;

public class HorizontalScrollProductModel
{
    private String productID;
    private String productPhoto;
    private String productTitle;
    private String productDescription;
    private String productPrice;

    public HorizontalScrollProductModel(String productID, String productPhoto, String productTitle, String productDescription, String productPrice)
    {
        this.productID = productID;
        this.productPhoto = productPhoto;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductID()
    {
        return productID;
    }

    public void setProductID(String productID)
    {
        this.productID = productID;
    }

    public String getProductPhoto()
    {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto)
    {
        this.productPhoto = productPhoto;
    }

    public String getProductTitle()
    {
        return productTitle;
    }

    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    public String getProductPrice()
    {
        return productPrice;
    }

    public void setProductPrice(String productPrice)
    {
        this.productPrice = productPrice;
    }
}
