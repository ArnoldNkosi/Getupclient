package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.util.List;

public class ContactPrefMediumDto  implements Serializable {
    private List<PreferenceInboundMediumDTO> inboundMedium;
    private List<PreferenceOutboundMediumDTO> outboundMedium;

    public List<PreferenceInboundMediumDTO> getInboundMedium() {
        return inboundMedium;
    }

    public void setInboundMedium(List<PreferenceInboundMediumDTO> inboundMedium) {
        this.inboundMedium = inboundMedium;
    }

    public List<PreferenceOutboundMediumDTO> getOutboundMedium() {
        return outboundMedium;
    }

    public void setOutboundMedium(List<PreferenceOutboundMediumDTO> outboundMedium) {
        this.outboundMedium = outboundMedium;
    }

    @Override
    public String toString() {
        return "ContactPrefMediumDto{" +
                "inboundMedium=" + inboundMedium +
                ", outboundMedium=" + outboundMedium +
                '}';
    }
}
