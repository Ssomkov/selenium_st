package models;

import java.util.Arrays;
import java.util.List;

public class Product {

    private String name;
    private String salePrice;
    private String salePriceColor;
    private String salePriceFontStyle;
    private String salePriceFontSize;
    private String commonPrice;
    private String commonPriceColor;
    private String commonPriceFontStyle;
    private String commonPriceFontSize;

    private String status;
    private String code;
    private String sex;
    private String quantity;
    private String image;
    private String dateValidFrom;
    private String dateValidTo;
    private String manufacturer;
    private String supplier;
    private String keywords;
    private String description;
    private String shortDescription;
    private String headTitle;
    private String metaDescription;
    private String purchasePrice;
    private String currencyCode;

    private List<Campaign> campaigns;


    public boolean isSalePriceColorRed() {
        if (salePriceColor != null) {
            String template = salePriceColor.substring(salePriceColor.indexOf("(") + 1, salePriceColor.lastIndexOf(")")).replaceAll(" ", "");
            List<String> listRGB = Arrays.asList(template.split(","));
            String green = listRGB.get(1);
            String blue = listRGB.get(2);
            return green.equals("0") && blue.equals("0");
        }
        return false;
    }

    public boolean isCommonPriceGrey() {
        if (commonPriceColor != null) {
            String template = commonPriceColor.substring(commonPriceColor.indexOf("(") + 1, commonPriceColor.lastIndexOf(")")).replaceAll(" ", "");
            List<String> listRGB = Arrays.asList(template.split(","));
            String red = listRGB.get(0);
            String green = listRGB.get(1);
            String blue = listRGB.get(2);
            return red.equals(green) && green.equals(blue);
        }
        return false;
    }

    public boolean isSalePriceBold() {
        if (salePriceFontStyle != null) {
            return salePriceFontStyle.contains("900") || salePriceFontStyle.contains("700");
        }
        return false;
    }

    public boolean isCommonPriceStrikeThrough() {
        if (commonPriceFontStyle != null) {
            return commonPriceFontStyle.contains("line-through");
        }
        return false;
    }

    public boolean isSalePriceSizeMoreCommonPriceSize() {
        if (commonPriceFontSize != null && salePriceFontSize != null) {
            double commonSize = Double.parseDouble(commonPriceFontSize);
            double saleSize = Double.parseDouble(salePriceFontSize);
            return saleSize > commonSize;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getSalePriceColor() {
        return salePriceColor;
    }

    public void setSalePriceColor(String salePriceColor) {
        this.salePriceColor = salePriceColor;
    }

    public String getSalePriceFontStyle() {
        return salePriceFontStyle;
    }

    public void setSalePriceFontStyle(String salePriceFontStyle) {
        this.salePriceFontStyle = salePriceFontStyle;
    }

    public String getSalePriceFontSize() {
        return salePriceFontSize;
    }

    public void setSalePriceFontSize(String salePriceFontSize) {
        this.salePriceFontSize = salePriceFontSize;
    }

    public String getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(String commonPrice) {
        this.commonPrice = commonPrice;
    }

    public String getCommonPriceColor() {
        return commonPriceColor;
    }

    public void setCommonPriceColor(String commonPriceColor) {
        this.commonPriceColor = commonPriceColor;
    }

    public String getCommonPriceFontStyle() {
        return commonPriceFontStyle;
    }

    public void setCommonPriceFontStyle(String commonPriceFontStyle) {
        this.commonPriceFontStyle = commonPriceFontStyle;
    }

    public String getCommonPriceFontSize() {
        return commonPriceFontSize;
    }

    public void setCommonPriceFontSize(String commonPriceFontSize) {
        this.commonPriceFontSize = commonPriceFontSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDateValidFrom() {
        return dateValidFrom;
    }

    public void setDateValidFrom(String dateValidFrom) {
        this.dateValidFrom = dateValidFrom;
    }

    public String getDateValidTo() {
        return dateValidTo;
    }

    public void setDateValidTo(String dateValidTo) {
        this.dateValidTo = dateValidTo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
