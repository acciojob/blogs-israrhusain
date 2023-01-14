package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog

        Image image=new Image();
        List<Image> list=new ArrayList<>();
        list=blog.getImageList();
        image.setDescription(description);
        image.setDimensions(dimensions);

         list.add(image);
         blog.setImageList(list);
         blogRepository.save(blog);
         return image;
    }

    public void deleteImage(Image image){
          imageRepository2.delete(image);
    }

    public Image findById(int id) {
          return imageRepository2.findAll().get(id);
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0

      if(screenDimensions.split("x").length==2 || Objects.nonNull(image)){
           int maxlength=Integer.parseInt(screenDimensions.split("x")[0])/Integer.parseInt(image.getDimensions().split("x")[0]);
           int maxwidth=Integer.parseInt(screenDimensions.split("x")[1])/Integer.parseInt(image.getDimensions().split("x")[1]);
           return maxlength*maxwidth;
      }
       return 0;
    }
}
