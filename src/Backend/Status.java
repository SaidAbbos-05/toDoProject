package src.Backend;

import java.util.StringJoiner;

public enum Status {
    CREATED,
    IN_PROGRESS,
    DONE,
    CANCELED,
    DEFAULT;


    public static String getAllValuesAsString(){
        Status[] values = values();
        StringJoiner joiner = new StringJoiner("\n");
        for (Status value : values) {
            joiner.add(value.ordinal()+1+"."+value);
        }
        return joiner.toString();
    }
    public static Status getValueByOrder(int order){
        Status[] values = values();
        for (Status value : values) {
            if (value.ordinal()==order-1){
                return value;
            }
        }
        return DEFAULT;
    }

}
