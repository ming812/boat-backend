package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.internal.Debug;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class ProductMapper extends CommonMapper {

//    public static ProductDto mapToProductDto(Product product){
//        LocalDateTime localDateTime = LocalDateTime.now();
//        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
//        Date date = Date.from(instant);
//        List<String> subImage = new ArrayList<>();
//        product.getSubImage().forEach( image ->
//                subImage.add(Base64.getEncoder().encodeToString(image))
//        );
//        return new ProductDto(
//                product.getProductId(),
//                product.getProductName(),
//                product.getPrice(),
//                product.getDiscount(),
//                product.getCategory(),
//                product.getQty(),
//                product.getDescription(),
//                product.getMainImage(),
//                subImage,
//                null,
//                null,
//                null,
//                null
//        );
//    }

    public static Product mapToProductWithUpload(String userName, ProductDto productDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        List<byte[]> subImage = new ArrayList<>();
        productDto.getSubImages().forEach(image ->
                subImage.add(Base64.getDecoder().decode(image))
        );
        return new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getPrice(),
                productDto.getDiscount(),
                productDto.getCategory(),
                productDto.getQty(),
                productDto.getDescription(),
                userName,
                date,
                userName,
                date
        );
    }

    public static ProductImage mapToProductImageWithUpload(String userName, ProductDto productDto) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        String mainImage = saveImageToLocal(productDto.getMainImage(), productDto.getProductId().toString() , true);
        String[] subImage = new String[3];
        if (!productDto.getSubImages().isEmpty() && productDto.getSubImages().size() <= 3) {
            for (int i = 0; i < productDto.getSubImages().size(); i++) {
//                subImage[i] = resizeImage(productDto.getSubImages().get(i));
                subImage[i] = saveImageToLocal(productDto.getSubImages().get(i) , productDto.getProductId().toString() + "_" + i , false);
            }
        }
        return new ProductImage(
                productDto.getProductId(),
                mainImage,
                subImage[0],
                subImage[1],
                subImage[2],
                userName,
                date,
                userName,
                date
        );
    }

    private static String saveImageToLocal(String base64Image , String productId , boolean mainImage){
        // Decode Base64 string and save to local folder
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        // Generate a unique filename
        String filename = mainImage ? productId + "_main" + ".jpg" : productId + "_sub" + ".jpg";
        String imagePath = "/Users/wongming/Desktop/upload_image/" + filename;

        try {
            Files.write(Paths.get(imagePath), imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagePath;
    }

    private static String resizeImage(String base64Image) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);


        try {
            BufferedImage originalImage = Imaging.getBufferedImage(imageBytes, null);

            // Resize the image (e.g., to 100x100)
            BufferedImage resizedImage = Scalr.resize(originalImage, 300, 300);

            // Encode the resized image back to Base64
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            byte[] resizedImageBytes = baos.toByteArray();
            String resizedBase64Image = Base64.getEncoder().encodeToString(resizedImageBytes);
            System.out.println("the size of Image = " + resizedBase64Image.length());
            return resizedBase64Image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ImageReadException e) {
            throw new RuntimeException(e);
        }
    }
}
