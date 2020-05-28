package com.kitaa.startup.models;

public class WishlistModel
{
    private String productItemImage;
    private String productTitleDisplay;
    private String productPriceDisplay;
    private String productUploadTimeDisplay;
    private String productRegionDisplay;
    private String productContact;

    public WishlistModel(String productItemImage, String productTitleDisplay, String productPriceDisplay, String productUploadTimeDisplay, String productRegionDisplay, String productContact)
    {
        this.productItemImage = productItemImage;
        this.productTitleDisplay = productTitleDisplay;
        this.productPriceDisplay = productPriceDisplay;
        this.productUploadTimeDisplay = productUploadTimeDisplay;
        this.productRegionDisplay = productRegionDisplay;
        this.productContact = productContact;
    }

    public String getProductItemImage()
    {
        return productItemImage;
    }

    public void setProductItemImage(String productItemImage)
    {
        this.productItemImage = productItemImage;
    }

    public String getProductTitleDisplay()
    {
        return productTitleDisplay;
    }

    public void setProductTitleDisplay(String productTitleDisplay)
    {
        this.productTitleDisplay = productTitleDisplay;
    }

    public String getProductPriceDisplay()
    {
        return productPriceDisplay;
    }

    public void setProductPriceDisplay(String productPriceDisplay)
    {
        this.productPriceDisplay = productPriceDisplay;
    }

    public String getProductUploadTimeDisplay()
    {
        return productUploadTimeDisplay;
    }

    public void setProductUploadTimeDisplay(String productUploadTimeDisplay)
    {
        this.productUploadTimeDisplay = productUploadTimeDisplay;
    }

    public String getProductRegionDisplay()
    {
        return productRegionDisplay;
    }

    public void setProductRegionDisplay(String productRegionDisplay)
    {
        this.productRegionDisplay = productRegionDisplay;
    }

    public String getProductContact()
    {
        return productContact;
    }

    public void setProductContact(String productContact)
    {
        this.productContact = productContact;
    }
}

