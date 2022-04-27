package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        // 파일 네임 받아서 fullpath 반환
        return fileDir+filename;
    }
    // 여러개 업로드 해야함
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    // 멀티파트 파일을 서버에 저장하는 역할 담당.
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) return null;

        String originalFilename = multipartFile.getOriginalFilename(); // 사용자가 업로드한 파일명 가져옴
        String storeFileName = createStoreFileName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        // 확장자 가져오기
        String ext = extractExt(originalFilename);
        // 서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        // uuid에 확장자 붙여주면 저장할 파일 이름이 된다.
        String storeFileName = uuid + "." + ext;
        return storeFileName;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        return ext;
    }

}
