package com.odeyalo.analog.netflix.video.dto.api;

public class StreamVideoUrlResponseDTO {
    private String streamUrl;

    public StreamVideoUrlResponseDTO() {}

    public StreamVideoUrlResponseDTO(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }
}
