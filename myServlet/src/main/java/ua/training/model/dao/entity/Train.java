package ua.training.model.dao.entity;



import java.util.ArrayList;
import java.util.List;

public class Train {
    private int idTrain;
    private String trainName;
    private String trainNameUA;


    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNameUA() {
        return trainNameUA;
    }

    public void setTrainNameUA(String trainNameUA) {
        this.trainNameUA = trainNameUA;
    }

    @Override
    public String toString() {
        return "Train{" +
                "idTrain=" + idTrain +
                ", trainName='" + trainName + '\'' +
                ", trainNameUA='" + trainNameUA + '\'' +
                '}';
    }
}
