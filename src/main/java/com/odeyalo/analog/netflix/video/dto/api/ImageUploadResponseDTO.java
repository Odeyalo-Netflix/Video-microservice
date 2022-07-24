package com.odeyalo.analog.netflix.video.dto.api;

public class ImageUploadResponseDTO {
    private String imageUrl;

    public ImageUploadResponseDTO() {}

    public ImageUploadResponseDTO(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
