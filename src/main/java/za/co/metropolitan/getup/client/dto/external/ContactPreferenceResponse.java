package za.co.metropolitan.getup.client.dto.external;

import za.co.metropolitan.getup.client.dto.UpdateResponseDto;

import java.io.Serializable;

public class ContactPreferenceResponse implements Serializable {

    private CorePreferencesResponse corePreferencesResponse;
    private UpdateResponseDto updateResponseDto;


    public UpdateResponseDto getUpdateResponse() {
        return updateResponseDto;
    }

    public void setUpdateResponse(UpdateResponseDto updateResponseDto) {
        this.updateResponseDto = updateResponseDto;
    }

    public CorePreferencesResponse getCorePreferencesResponse() {
        return corePreferencesResponse;
    }

    public void setCorePreferencesResponse(CorePreferencesResponse corePreferencesResponse) {
        this.corePreferencesResponse = corePreferencesResponse;
    }

    @Override
    public String toString() {
        return "ContactPreferenceResponse{" +
                "updateResponseDto=" + updateResponseDto +
                ", corePreferencesResponse=" + corePreferencesResponse +
                '}';
    }
}
