package com.example.demorestaurant.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetCeoResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetImageResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetRestaurantResponse;
import com.example.demorestaurant.entities.Image;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.exceptions.InternalServerError;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.entities.exceptions.NotValidException;
import com.example.demorestaurant.entities.projections.FileProjection;
import com.example.demorestaurant.repositories.IFileRepository;
import com.example.demorestaurant.services.interfaces.IFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private CeoServiceImpl ceoService;
    @Autowired
    private RestaurantServiceImpl restaurantService;

    @Autowired
    private IFileRepository repository;

    private AmazonS3 s3client;

    private String ENDPOINT_URL = "s3.us-east-2.amazonaws.com";

    private String BUCKET_NAME = "delichi";

    private String ACCESS_KEY = "AKIATH7APQJ7TP5UMR3Z";

    private String SECRET_KEY = "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";


    // Upload a restaurant img by restaurant
    @Override
    public BaseResponse uploadRestaurantImages(MultipartFile multipartFile, Long idCeo, Long idRestaurant, String imgType) {
        Image image = new Image();
        String urlDirection = "";
        GetCeoResponse ceo = ceoService.get(idCeo);
        Restaurant restaurant = restaurantService.FindAndEnsureExist(idRestaurant);

        if (ValidateFileExtension(multipartFile)){

            switch (imgType){
                case "images":
                    urlDirection = "data/bussines_info/ceo/" + ceo.getEmail()
                            + "/properties/ceo_restaurants/" + restaurant.getName().replace(" ","_") + "/images/restaurantImages/";
                    break;
                case "logo":
                    if (ValidateNumberOfLogos(idRestaurant)){
                        return BaseResponse.builder()
                                .message("Existing Logo in Database")
                                .success(Boolean.FALSE)
                                .httpStatus(HttpStatus.CONFLICT)
                                .build();
                    }else {
                        urlDirection = "data/bussines_info/ceo/" + ceo.getEmail()
                                + "/properties/ceo_restaurants/" + restaurant.getName().replace(" ","_") + "/images/logo/";
                    }

                    break;
                case "banner":
                    if(ValidateNumberOfBanners(idRestaurant)){
                        return BaseResponse.builder()
                                .message("Existing Banner in Database")
                                .success(Boolean.FALSE)
                                .httpStatus(HttpStatus.CONFLICT)
                                .build();
                    }else{
                        urlDirection = "data/bussines_info/ceo/" + ceo.getEmail()
                                + "/properties/ceo_restaurants/" + restaurant.getName().replace(" ","_") + "/images/banner/";
                    }
                    break;
            }

            // Create the urlDirection where the img will be uploaded

            String fileUrl = "";

            try {
                File file = convertMultiPartToFile(multipartFile);
                String filePath = urlDirection + generateFileName(multipartFile); // added the filename to the url

                fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + filePath;
                uploadFileTos3bucket(filePath, file); // Ubication, file

                image.setFileUrl(fileUrl);
                image.setRestaurant(restaurantService.FindAndEnsureExist(idRestaurant));
                image.setName(generateFileName(multipartFile));
                image.setImageType(imgType);

                repository.save(image);

                file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return BaseResponse.builder()
                    .data(fileUrl)
                    .message("Image uploaded successfully")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }else {
            throw new NotValidException("File Not Supported");
        }

    }


    @Override
    public BaseResponse UpdateRestaurantLogo(MultipartFile multipartFile, Long idRestaurant, Long idCeo) {

        Image newImage = fromProjectionToImage(repository.GetLogoImageByRestaurantId(idRestaurant)
                .orElseThrow(NotFoundException::new));

        String urlDirection = "";
        GetCeoResponse ceo = ceoService.get(idCeo);
        Restaurant restaurant = restaurantService.FindAndEnsureExist(idRestaurant);

        if (ValidateFileExtension(multipartFile)){

            urlDirection = "data/bussines_info/ceo/" + ceo.getEmail()
                    + "/properties/ceo_restaurants/" + restaurant.getName().replace(" ","_") + "/images/logo/";

            String fileUrl = "";

            try {
                File file = convertMultiPartToFile(multipartFile);
                String filePath = urlDirection + generateFileName(multipartFile); // added the filename to the url

                fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + filePath;
                uploadFileTos3bucket(filePath, file); // Ubication, file

                // Obtain the old URL from the logo image that the restaurant want to update
                String oldUrl = newImage.getFileUrl();
                deleteFileFromS3Bucket(oldUrl); // Remove from bucket by old url provided

                // Save the new URL in the DB
                newImage.setFileUrl(fileUrl);
                newImage.setRestaurant(restaurantService.FindAndEnsureExist(idRestaurant));
                newImage.setName(generateFileName(multipartFile));
                newImage.setImageType("logo");

                repository.save(newImage);

                file.delete();

                return BaseResponse.builder()
                        .data(fileUrl)
                        .message("Image uploaded successfully")
                        .success(Boolean.TRUE)
                        .httpStatus(HttpStatus.OK)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            throw new NotValidException("File Not Supported");
        }

        return BaseResponse.builder()
                .message("Image don't updated")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .build();
    }

    @Override
    public BaseResponse UpdateRestaurantBanner(MultipartFile multipartFile, Long idRestaurant, Long idCeo) {

        Image newImage = fromProjectionToImage(repository.GetBannerImageByRestaurantId(idRestaurant)
                .orElseThrow(NotFoundException::new));

        String urlDirection = "";
        GetCeoResponse ceo = ceoService.get(idCeo);
        Restaurant restaurant = restaurantService.FindAndEnsureExist(idRestaurant);

        if (ValidateFileExtension(multipartFile)){

            urlDirection = "data/bussines_info/ceo/" + ceo.getEmail()
                    + "/properties/ceo_restaurants/" + restaurant.getName().replace(" ","_") + "/images/banner/";

            String fileUrl = "";

            try {
                File file = convertMultiPartToFile(multipartFile);
                String filePath = urlDirection + generateFileName(multipartFile); // added the filename to the url

                fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/" + filePath;
                uploadFileTos3bucket(filePath, file); // Ubication, file

                // Obtain the old URL from the logo image that the restaurant want to update
                String oldUrl = newImage.getFileUrl();
                deleteFileFromS3Bucket(oldUrl); // Remove from bucket by old url provided

                // Save the new URL in the DB
                newImage.setFileUrl(fileUrl);
                newImage.setRestaurant(restaurantService.FindAndEnsureExist(idRestaurant));
                newImage.setName(generateFileName(multipartFile));
                newImage.setImageType("Banner");

                repository.save(newImage);

                file.delete();

                return BaseResponse.builder()
                        .data(fileUrl)
                        .message("Image uploaded successfully")
                        .success(Boolean.TRUE)
                        .httpStatus(HttpStatus.OK)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            throw new NotValidException("File Not Supported");
        }

        return BaseResponse.builder()
                .message("Image don't updated")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .build();
    }

    @Override
    public BaseResponse DeleteImage(Long idImage) {
        repository.delete(FindImageAndEnsureExist(idImage));
        return BaseResponse.builder()
                .message("Image deleted correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }


    // Images type images
    @Override
    public BaseResponse listAllImagesByRestaurantId(Long idRestaurant) {
        List<FileProjection> files = repository.listAllImagesByRestaurantId(idRestaurant);

        return BaseResponse.builder()
                .data(files
                        .stream()
                        .map(this::from)
                        .collect(Collectors.toList()))
                .message("list all images by restaurant")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    // Images type logo
    @Override
    public BaseResponse GetLogoImageByRestaurantId(Long idRestaurant) {
        FileProjection fileProjection = repository.GetLogoImageByRestaurantId(idRestaurant).orElseThrow(NotFoundException::new);
        try{
            return BaseResponse.builder()
                    .data(fileProjection)
                    .message("list all logo images by restaurant")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }catch (Error e){
            throw new InternalServerError();
        }
    }

    // Images type banner
    @Override
    public BaseResponse GetBannerImageByRestaurantId(Long idRestaurant) {
        Optional<FileProjection> files = repository.GetBannerImageByRestaurantId(idRestaurant);
        return BaseResponse.builder()
                .data(files
                        .stream()
                        .map(this::from)
                        .collect(Collectors.toList()))
                .message("list all banner images by restaurant")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public void deleteFileFromS3Bucket(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        s3client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, fileName));
        //return "Successfully deleted";
    }

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }

    // projection to Image
    private GetImageResponse from (FileProjection projection){
        try {
            return GetImageResponse.builder()
                    .name(projection.getName())
                    .id(projection.getId())
                    .imgType(projection.getImageType())
                    .fileUrl(projection.getFileUrl()).build();
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Can't transform into a GetImageResponse");
        }
    }

    @Override
    public Image fromFileProjectionToImage(FileProjection projection){
        Image response = new Image();
        try {
            response.setId(projection.getId());
            response.setName(projection.getName());
            response.setFileUrl(projection.getFileUrl());
            response.setImageType(projection.getImageType());
            return response;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Can't transform into a GetImageResponse");
        }
    }

    @Override
    public Boolean ValidateFileExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png")){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    private Boolean ValidateNumberOfBanners(Long idRestaurant){
        Optional<FileProjection> projection = repository.GetBannerImageByRestaurantId(idRestaurant);

        if (projection.isPresent()){
            return Boolean.TRUE; // One data in Database, we cannot upload a Banner
        }else {
            return Boolean.FALSE; // No one in Database, we can upload a Banner
        }
    };

    private Boolean ValidateNumberOfLogos(Long idRestaurant){
        Optional<FileProjection> projection = repository.GetLogoImageByRestaurantId(idRestaurant);

        if (projection.isPresent()){
            return Boolean.TRUE; // One data in Database, we cannot upload a Banner
        }else {
            return Boolean.FALSE; // No one in Database, we can upload a Banner
        }
    };

    private Image FindImageAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private Image fromProjectionToImage(FileProjection fileProjection){
        Image image = new Image();
        image.setId(fileProjection.getId());
        image.setImageType(fileProjection.getImageType());
        image.setFileUrl(fileProjection.getFileUrl());
        image.setRestaurant(restaurantService.FindAndEnsureExist(fileProjection.getIdRestaurant()));
        return image;
    }

}