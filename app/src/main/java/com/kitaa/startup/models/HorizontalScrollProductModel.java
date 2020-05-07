package com.kitaa.startup.models;

public class HorizontalScrollProductModel
{
    private int productPhoto;
    private String productTitle;
    private String productDescription;
    private String productPrice;

    public HorizontalScrollProductModel(int productPhoto, String productTitle, String productDescription, String productPrice)
    {
        this.productPhoto = productPhoto;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public int getProductPhoto()
    {
        return productPhoto;
    }

    public void setProductPhoto(int productPhoto)
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
