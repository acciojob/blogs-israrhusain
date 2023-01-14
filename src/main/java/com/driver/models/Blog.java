package com.driver.models;

import javax.persistence.*;
import java.util.Date;
import java.util.*;

@Entity
@Table
public class Blog{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String title;
   private String content;
   private Date pubdate;

   public Blog(String title, String content, Date pubdate) {
      this.id = id;
      this.title = title;
      this.content = content;
      this.pubdate = pubdate;
      this.user = user;
   }



   @ManyToOne
   @JoinColumn
   private User user;

   @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
   private List<Image> imagelist;

   public List<Image> getImagelist() {
      return imagelist;
   }
   public void setImagelist(List<Image> imagelist){
      this.imagelist=imagelist;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Date getPubdate() {
      return pubdate;
   }

   public void setPubdate(Date pubdate) {
      this.pubdate = pubdate;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
}




