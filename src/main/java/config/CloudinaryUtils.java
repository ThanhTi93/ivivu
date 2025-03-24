package config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Part;

public class CloudinaryUtils {
    private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "dkbmaehcb",
        "api_key", "915129591265419",
        "api_secret", "bDCgn9ANeSrvQeYq114XFoaxjMY"
    ));

public static String uploadImage(Part filePart) throws IOException {
    if (filePart == null || filePart.getSize() == 0) {
        throw new IllegalArgumentException("File không hợp lệ");
    }

    // Chuyển InputStream thành byte[]
    try (InputStream inputStream = filePart.getInputStream();
         ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

        byte[] fileBytes = buffer.toByteArray();

        // Tạo metadata cho file upload
        Map<String, Object> uploadParams = new HashMap<>();
        uploadParams.put("resource_type", "image");
        uploadParams.put("public_id", "uploaded_image_" + System.currentTimeMillis());

        // Upload file lên Cloudinary
        Map uploadResult = cloudinary.uploader().upload(fileBytes, uploadParams);
        return (String) uploadResult.get("url");
    }
}

}
