package za.co.metropolitan.getup.client.dto.external;

import java.io.Serializable;

public class CoreContactPref  implements Serializable {

    private String bestContactTime = null;
    private String contactMethod = null;
//    private String faceToFace;

    public String getBestContactTime() {
        return bestContactTime;
    }

    public void setBestContactTime(String bestContactTime) {
        this.bestContactTime = bestContactTime;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

//    public String getFaceToFace() {
//        return faceToFace;
//    }
//
//    public void setFaceToFace(String faceToFace) {
//        this.faceToFace = faceToFace;
//    }

    @Override
    public String toString() {
        return "CoreContactPref{" +
                "bestContactTime='" + bestContactTime + '\'' +
                ", contactMethod='" + contactMethod + '\'' +
//                ", faceToFace='" + faceToFace + '\'' +
                '}';
    }
}
