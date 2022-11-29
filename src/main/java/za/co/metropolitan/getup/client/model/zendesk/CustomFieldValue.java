package za.co.metropolitan.getup.client.model.zendesk;
import java.util.Arrays;

public class CustomFieldValue  {
    private Long id;
    private String[] value;

    public CustomFieldValue(Long id, String[] value) {
        this.id = id;
        this.value = value;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String[] getValue() {
        return value;
    }
    public void setValue(String[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CustomFieldValue{" +
                "id=" + id +
                ", value=" + Arrays.toString(value) +
                '}';
    }
}

