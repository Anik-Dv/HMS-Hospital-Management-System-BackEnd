package com.hms.anikdv.code.exceptions;

/**
 * @category Custom Exception
 * @author AnikDV
 */
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String resourceField;
    private long resourceId;

    /**
     * @param resourceName
     * @param resourceField
     * @param resourceId
     */
    public ResourceNotFoundException(final String resourceName, final String resourceField, final long resourceId) {
        super(String.format("%s Resource Not Found! %s : %s", resourceName, resourceField, resourceId));
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.resourceId = resourceId;
    }

    /**
     * @param resourceName
     * @param resourceField
     */
    public ResourceNotFoundException(final String resourceName, final String resourceField) {
        super(String.format("%s Resource Not Found! %s ", resourceName, resourceField));
        this.resourceName = resourceName;
        this.resourceField = resourceField;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the resourceField
     */
    public String getResourceField() {
        return resourceField;
    }

    /**
     * @param resourceField the resourceField to set
     */
    public void setResourceField(String resourceField) {
        this.resourceField = resourceField;
    }

    /**
     * @return the resourceId
     */
    public long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException [resourceName=" + resourceName + ", resourceField=" + resourceField
                + ", resourceId=" + resourceId + "]";
    }
}
