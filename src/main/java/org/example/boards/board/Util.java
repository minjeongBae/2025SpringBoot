package org.example.boards.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class Util {
    final static String HEAD_DATE = "20000101";
    final static String TAIL_DATE = "29991231";

    final static long MAX_FILE_SIZE = 10_485_760L; // 10MB

  //  @Value("${file.dir}")
 //   private String fileDir; // 파일첨부에 대한 저장 경로

    public String getHeadDateStr(LocalDate date) {
        if(date != null) {
            return String.valueOf(date);
        }
        return HEAD_DATE;
    }

    public String getTailDateStr(LocalDate date) {
        if(date != null) {
            return String.valueOf(date);
        }
        return TAIL_DATE;
    }

    public String getSearchKeyStr(String key) {
        if(key == null || key.isBlank()) {
            return "";
        }
        return key;
    }

    private static boolean isRightFile(MultipartFile file)  {
        if(file == null) {
            return false;
        }
        if(file.getSize() == 0 || file.getSize() > MAX_FILE_SIZE) {
            return false;
        }
        if (file.getContentType() != null && file.getContentType().startsWith("image")) {
            return true;
        }else {
           // throw new Exception("이미지 파일만 업로드할 수 있습니다.");
            return false;
        }
    }

    public static String[] multipartFileToString(MultipartFile file) {
        String[] result = new String[3]; // id, name, path
        if(!isRightFile(file)) {
            return new String[] {"", "", ""}; //  null 에러남 바로 엔티티에 붙이기 때문
        }
        UUID uuid = UUID.randomUUID();
        result[0] = uuid.toString();
        result[1] = file.getOriginalFilename();
        result[2] = "C:\\Users\\ssii6\\study_2025\\files\\" + result[1];

        return result;
    }
}
