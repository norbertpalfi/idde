package edu.bbte.quiz.frontend;

class Data {
    private String kilometers;

    private String horsePower;

    private String ownerName;
    private String carModel;
    private String startDate;
    private String id;

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data(String kilometers, String horsePower, String ownerName, String carModel, String startDate, String id) {
        this.kilometers = kilometers;
        this.horsePower = horsePower;
        this.ownerName = ownerName;
        this.carModel = carModel;
        this.startDate = startDate;
        this.id = id;
    }

}
