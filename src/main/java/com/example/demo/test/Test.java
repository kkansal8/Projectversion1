package com.example.demo.test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import org.springframework.stereotype.Component;

import com.example.demo.dto.dto;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

@Component
public class Test {
	
	private String text;
	
	private byte[] testarray;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getTestarray() {
		return testarray;
	}

	public void setTestarray(byte[] testarray) {
		this.testarray = testarray;
	}
	
	public dto imageToText(dto data1) {
		
		System.out.println("value of text 1"+data1.getText1());
		//File imageFile = new File("path_to_your_image.jpg"); // Change this to your image file path
		String filebyte = data1.getBase();
		Base64.Decoder bd = Base64.getDecoder();
		testarray = bd.decode(filebyte);
		BufferedImage img =null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(testarray));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ITesseract instance = new Tesseract();
        instance.setDatapath("/tessdata"); // Change this to your tessdata directory path

        try {
            String result = instance.doOCR(img);
            data1.setResult(result);
            System.out.println(result);
            Pattern ptr = Pattern.compile("\n");
            String[] arr1 = ptr.split(result);
            data1.setArr(arr1);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        
        return data1;
    }
	
	public String imageToText1(dto data1) {
		String imagePath = "path_to_your_image.jpg"; // Change this to your image file path
		String filebyte = data1.getBase();
		Base64.Decoder bd = Base64.getDecoder();
		testarray = bd.decode(filebyte);
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
            // Read the image file
            Path path = Paths.get(imagePath);
            byte[] data = Files.readAllBytes(path);
            ByteString imgBytes = ByteString.copyFrom(testarray);

            // Perform OCR on the image
            Image image = Image.newBuilder().setContent(imgBytes).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION))
                    .setImage(image)
                    .build();
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(List.of(request));

            // Extract text from the OCR result
//            List<EntityAnnotation> annotationsList = response.getResponses(0).getTextAnnotations();
//            if (annotationsList != null && !annotationsList.isEmpty()) {
//                System.out.println("Text found:");
//                for (EntityAnnotation annotation : annotationsList) {
//                    System.out.println(annotation.getDescription());
//                }
//            } else {
//                System.out.println("No text found.");
//            }
            for (AnnotateImageResponse res : response.getResponsesList()) {
                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                    System.out.println(annotation.getDescription());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "sucess";
    }
	}

	


