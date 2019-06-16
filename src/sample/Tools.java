package sample;

public class Tools {

    public static int scale(int max, int min, int baseMin, int baseMax, int valueIn) {
        return ((max - min) * (valueIn - baseMin) / (baseMax - baseMin)) + min;
    }


}
