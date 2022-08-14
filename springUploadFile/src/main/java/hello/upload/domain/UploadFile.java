package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {
    private String uploadFileName;
    // 고유값
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
