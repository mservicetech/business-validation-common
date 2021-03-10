package com.mservicetech.business.validation.sample;

public class DrivingLicense {
    private String placeOfIssue;
    private String countryOfIssue;
    private String drivingLicenseNumber;
    private String driverLastName;

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getDriverLastName() {
        return driverLastName;
    }

    public void setDriverLastName(String driverLastName) {
        this.driverLastName = driverLastName;
    }

    @Override
    public String toString() {
        return "DrivingLicense{" +
                "placeOfIssue='" + placeOfIssue + '\'' +
                ", countryOfIssue='" + countryOfIssue + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                '}';
    }
}
