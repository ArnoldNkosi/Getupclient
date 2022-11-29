package za.co.metropolitan.getup.client.dto;

public class LifeHackProductRequestDto {

    private String productName;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "LifeHackProductRequestDto{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
