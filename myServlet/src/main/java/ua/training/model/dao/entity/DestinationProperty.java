package ua.training.model.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class DestinationProperty {
    private int idDestination;
    private int idProperty;
    private int idTrain;
    private String timeDeparture;
    private String timeArrival;

    private String dateDeparture;
    private String dateArrival;
    private int price;
    private List<Train> trains = new ArrayList<>();

    private List<Destinations> destinations = new ArrayList<>();

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public String getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(String timeArrival) {
        this.timeArrival = timeArrival;
    }


    public String getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(String dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty = idProperty;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public List<Destinations> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destinations> destinations) {
        this.destinations = destinations;
    }

    @Override
    public String toString() {
        return "DestinationProperty{" +
                "idDestination=" + idDestination +
                ", idProperty=" + idProperty +
                ", idTrain=" + idTrain +
                ", timeDeparture='" + timeDeparture + '\'' +
                ", timeArrival='" + timeArrival + '\'' +
                ", dateDeparture='" + dateDeparture + '\'' +
                ", dateArrival='" + dateArrival + '\'' +
                ", price=" + price +
                ", trains=" + trains +
                ", destinations=" + destinations +
                '}';
    }
}
