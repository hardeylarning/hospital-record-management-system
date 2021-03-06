package com.rocktech.hospitalrms;

public class Report {
    private int id;
    private String reportId;
    private String reportMatric;
    private String reportDesc;
    private String reportDate;
    private String name;
    private String phone;
    private String gender;
    private String age;
    private String attendance;
    private String diagnosis;
    private String test;
    private String drug;
    private String outcome;

//    public Report(String reportId, String reportMatric, String reportDesc, String reportDate) {
//        this.reportId = reportId;
//        this.reportMatric = reportMatric;
//        this.reportDesc = reportDesc;
//        this.reportDate = reportDate;
//    }
//
//    public Report(int id, String reportMatric, String reportDesc, String reportDate) {
//        this.id = id;
//        this.reportMatric = reportMatric;
//        this.reportDesc = reportDesc;
//        this.reportDate = reportDate;
//    }

    public Report(String reportId, String reportMatric, String reportDesc, String reportDate,
                  String name, String phone, String gender, String age, String attendance,
                  String diagnosis, String test, String drug, String outcome) {
        this.reportId = reportId;
        this.reportMatric = reportMatric;
        this.reportDesc = reportDesc;
        this.reportDate = reportDate;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.attendance = attendance;
        this.diagnosis = diagnosis;
        this.test = test;
        this.drug = drug;
        this.outcome = outcome;
    }

    public int getId() {
        return id;
    }

    public String getReportId() {
        return reportId;
    }

    public String getReportMatric() {
        return reportMatric;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getAttendance() {
        return attendance;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTest() {
        return test;
    }

    public String getDrug() {
        return drug;
    }

    public String getOutcome() {
        return outcome;
    }
}
