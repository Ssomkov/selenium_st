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
}
