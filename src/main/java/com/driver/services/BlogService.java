package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs() {
        //find all blogs
        return blogRepository1.findAll();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
          Blog blogger=new Blog();
          blogger.set(userId);
           blogger.set(title);
           blogger.set(content);

        //updating the blog details
        blogger.setUser(userRepository1.findById(userId).get());
        User user=userRepository1.findById(userId).get();
        List<Blog> res=user.getBlogList();
        user.setBlogList(res);

        //Updating the userInformation and changing its blogs
       // blogRepository1.save(blogger);
        userRepository1.save(user);

    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog=blogRepository1.findById(blogId).get();
        Image image=imageService1.createAndReturn(blog,description,dimensions);
         image.setBlog(blog);
         List<Image> list=new ArrayList<>();
         list=blog.getImageList();
         list.add(image);
         blog.setImageList(list);
         blogRepository1.save(blog);



    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        if(blogRepository1.findById(blogId).get()==null){
            return;
        }
         blogRepository1.deleteById(blogId);
    }
}
