package com.test.votting.vottingtest.Moduls;

public class SetGetCandidatesInformations {
    private String candidateNationalID,name,birthOfDate,password,city,year,phoneNumber,campaign,pic;
    private int numberOfVotes,statusVoted;

    public String getCandidateNationalID() {
        return candidateNationalID;
    }

    public void setCandidateNationalID(String candidateNationalID) {
        this.candidateNationalID = candidateNationalID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(String birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getStatusVoted() {
        return statusVoted;
    }

    public void setStatusVoted(int statusVoted) {
        this.statusVoted = statusVoted;
    }
}
