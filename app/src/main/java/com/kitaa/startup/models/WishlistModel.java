package com.kitaa.startup.models;

public class WishlistModel
{
    private int productItemImage;
    private String productTitleDisplay;
    private String productPriceDisplay;
    private String productUploadTimeDisplay;
    private String productRegionDisplay;

    public WishlistModel(int productItemImage, String productTitleDisplay, String productPriceDisplay, String productUploadTimeDisplay, String productRegionDisplay)
    {
        this.productItemImage = productItemImage;
        this.productTitleDisplay = productTitleDisplay;
        this.productPriceDisplay = productPriceDisplay;
        this.productUploadTimeDisplay = productUploadTimeDisplay;
        this.productRegionDisplay = productRegionDisplay;
    }

    public int getProductItemImage()
    {
        return productItemImage;
    }

    public void setProductItemImage(int productItemImage)
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
}
