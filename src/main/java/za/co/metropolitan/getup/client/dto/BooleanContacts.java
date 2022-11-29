package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class BooleanContacts  implements Serializable {

    // private boolean addBenefitsToPlan;
    private boolean email = false;
    private boolean cell = false;

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean isCell() {
        return cell;
    }

    public void setCell(boolean cell) {
        this.cell = cell;
    }
}
