package za.co.metropolitan.getup.client.model.knownconsumer.constants;

public enum ContactType {

    CELLPHONE("Cellphone"),
    EMAIL("Email Address");

    public final String label;

    private ContactType(String label){
        this.label = label;
    }

    public static ContactType valueOfLabel(String label){
        for (ContactType c: values()){
            if (c.label.equals(label)){
                return c;
            }
        }
        throw new IllegalArgumentException(String.format("No ContactType defined for %s", label));
    }
}
