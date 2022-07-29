package com.odeyalo.analog.netflix.video.service.utils;

import com.odeyalo.analog.netflix.video.dto.GenericVideoData;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import java.io.IOException;

public class MultipartFileUtils {

    public static HttpEntity<MultiValueMap<String, Object>> buildHttpEntityWithFile(String fileKey, MultipartFile file) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            HttpHeaders httpHeaders = new HttpHeaders();
            ContentDisposition disposition = ContentDisposition
                    .builder("form-data")
                    .name("file")
                    .filename(file.getName())
                    .build();
            httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            body.add(fileKey, resource);
            body.add(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
            return new HttpEntity<>(body, httpHeaders);
        } catch (IOException e) {
            return null;
        }
    }

    public static MultipartFile copyMultipartFile(MultipartFile file) throws IOException {
        return new MockMultipartFile(file.getName(), file.getOriginalFilename(), file.getContentType(), file.getInputStream());
    }
}
