package be.ecotravel.back.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    private String cloudName;
    private String apiKey;
    private String apiSecret;

    public CloudinaryService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret
    ) {
        this.cloudName = cloudName;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    public String uploadImageToFolder(MultipartFile file, String folderPath, String publicId) throws IOException {
        Map<String, Object> uploadParams = new HashMap<>();

        uploadParams.put("folder", folderPath);

        uploadParams.put("public_id", publicId);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        return (String) uploadResult.get("secure_url");
    }

    /**
     * Récupère la liste des urls de toutes les images dans le folder
     * @param folder Dossier dont on veut les images
     * @return Liste d'url
     * @throws Exception
     */
    public List<String> getImagesFromFolder(String folder) throws Exception {
        Map<String, Object> query = ObjectUtils.asMap("expression", "folder:" + folder);

        ApiResponse response = cloudinary.search().expression("folder:" + folder).maxResults(30).execute();

        List<String> imageUrls = new ArrayList<>();
        List<Map<String, Object>> resources = (List<Map<String, Object>>) response.get("resources");

        if (resources != null) {
            for (Map<String, Object> resource : resources) {
                imageUrls.add((String) resource.get("secure_url"));
            }
        }

        return imageUrls;
    }

    public boolean deleteImageByUrl(String imageUrl) {
        try {
            String publicId = extractPublicIdFromUrl(imageUrl);

            Map response = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

            return "ok".equals(response.get("result"));
        } catch (Exception e) {
            return false;
        }
    }


    private String extractPublicIdFromUrl(String imageUrl) {
        String baseUrl = "https://res.cloudinary.com/" + cloudName + "/image/upload/";
        if (imageUrl.startsWith(baseUrl)) {
            String publicIdWithExtension = imageUrl.substring(baseUrl.length());

            int dotIndex = publicIdWithExtension.lastIndexOf('.');
            return (dotIndex != -1) ? publicIdWithExtension.substring(0, dotIndex) : publicIdWithExtension;
        }
        throw new IllegalArgumentException("URL invalide : " + imageUrl);
    }

}