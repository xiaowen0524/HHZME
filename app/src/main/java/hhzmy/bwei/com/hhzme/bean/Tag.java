package hhzmy.bwei.com.hhzme.bean;

/**
 * Created by asus on 2016/11/9.
 */
public class Tag {
    private String elementName;
    private String linkUrl;
    private String picUrl;
    private String businessType;
    private String color;
    private String elementDesc;
    private String elementType;
    private String linkType;
    private String modelFullId;
    private String sequence;
    private String templateFullId;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getModelFullId() {
        return modelFullId;
    }

    public void setModelFullId(String modelFullId) {
        this.modelFullId = modelFullId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getTemplateFullId() {
        return templateFullId;
    }

    public void setTemplateFullId(String templateFullId) {
        this.templateFullId = templateFullId;
    }

    public Tag(String elementName, String linkUrl, String picUrl, String businessType, String color, String elementDesc, String elementType, String linkType, String modelFullId, String sequence, String templateFullId) {
        this.elementName = elementName;
        this.linkUrl = linkUrl;
        this.picUrl = picUrl;
        this.businessType = businessType;
        this.color = color;
        this.elementDesc = elementDesc;
        this.elementType = elementType;
        this.linkType = linkType;
        this.modelFullId = modelFullId;
        this.sequence = sequence;
        this.templateFullId = templateFullId;
    }

    public Tag() {
        super();
    }

    @Override
    public String toString() {
        return "Tag{" +
                "elementName='" + elementName + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", businessType='" + businessType + '\'' +
                ", color='" + color + '\'' +
                ", elementDesc='" + elementDesc + '\'' +
                ", elementType='" + elementType + '\'' +
                ", linkType='" + linkType + '\'' +
                ", modelFullId='" + modelFullId + '\'' +
                ", sequence='" + sequence + '\'' +
                ", templateFullId='" + templateFullId + '\'' +
                '}';
    }
}
