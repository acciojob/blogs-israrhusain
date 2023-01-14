package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
          Blog blogger=new Blog(title,content,new Date());
          blogger.setUser(userRepository1.findById(userId).get());
          User user=userRepository1.findById(userId).get();
          List<Blog> res=user.getBloglist();
          user.setBloglist(res);
          blogRepository1.save(blogger);
          userRepository1.save(user);
        //updating the blog details

        //Updating the userInformation and changing its blogs

    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository1.findAll().get(blogId);
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog=blogRepository1.findById(blogId).get();
        Image image=imageService1.createAndReturn(blog,description,dimensions);
         image.setBlog(blog);
         List<Image> list=new ArrayList<>();
         list=blog.getImagelist();
         list.add(image);
         blog.setImagelist(list);
         blogRepository1.save(blog);



    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
         if(blogRepository1.findById(blogId).get()==null)
             return;
         blogRepository1.deleteById(blogId);
    }
}
