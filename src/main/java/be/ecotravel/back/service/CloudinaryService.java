package be.ecotravel.back.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

    public String uploadFileToFolder(MultipartFile file, String folderPath, String publicId) throws IOException {
        Map<String, Object> uploadParams = new HashMap<>();

        uploadParams.put("folder", folderPath);

        uploadParams.put("public_id", publicId);

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

        return (String) uploadResult.get("secure_url");
    }

    /**
     * Récupère la liste des urls de toutes les images dans le folder
     *
     * @param folder Dossier dont on veut les images
     * @return Liste d'url
     * @throws Exception
     */
    public List<String> getFileFromFolder(String folder) throws Exception {
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

    public void deleteImageByUrl(String imageUrl) {
        String publicId = extractPublicIdFromUrl(imageUrl);

        Map<String, Object> deleteResult = null;
        try {
            deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String result = (String) deleteResult.get("result");
        if (!"ok".equals(result)) {
            System.out.println("Erreur lors de la suppression de l'image : " + result);
        }
    }

    private String extractPublicIdFromUrl(String imageUrl) {
        String baseUrl = "https://res.cloudinary.com/" + cloudName + "/image/upload/";

        if (imageUrl.startsWith(baseUrl)) {
            String pathWithVersionAndExtension = imageUrl.substring(baseUrl.length());

            int firstSlashIndex = pathWithVersionAndExtension.indexOf('/');
            if (firstSlashIndex != -1) {
                String pathWithoutVersion = pathWithVersionAndExtension.substring(firstSlashIndex + 1);

                try {
                    pathWithoutVersion = URLDecoder.decode(pathWithoutVersion, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return pathWithoutVersion;
                }

                int dotIndex = pathWithoutVersion.lastIndexOf('.');
                return (dotIndex != -1) ? pathWithoutVersion.substring(0, dotIndex) : pathWithoutVersion;
            }
        }

        throw new IllegalArgumentException("URL invalide : " + imageUrl);
    }


}