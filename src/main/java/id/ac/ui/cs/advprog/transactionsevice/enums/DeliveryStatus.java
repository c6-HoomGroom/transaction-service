package id.ac.ui.cs.advprog.transactionsevice.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    WAITING_VERIFICATION("MENUNGGU VERIVIKASI"),
    PROCESSING("DIPROSES"),
    SHIPPED("DIKIRIM"),
    ARRIVED("TIBA"),
    DONE("SELESAI"),;

    private final String value;

    private DeliveryStatus(String value) {
        this.value = value; //set value order status
    }

    public static boolean contains(String param){
        for (DeliveryStatus deliveryStatus : DeliveryStatus.values()){
            if (deliveryStatus.name().equals(param)){
                return true; //berarti valid
            }
        }

        return false;
    }
}
