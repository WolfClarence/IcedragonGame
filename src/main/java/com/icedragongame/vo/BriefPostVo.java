package com.icedragongame.vo;

import com.icedragongame.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BriefPostVo {
    Integer post_id;
    String title;
    BigInteger scan_num;
    BigInteger reply_num;
    String username;
    LocalDateTime build_time;
    String game_name;
    String image_url;
    String category;

    /**
     * 根据post返回brief_post
     * @param post post
     */
    public BriefPostVo(Post post){
        post_id = post.getPostId();
        title = post.getTitle();
        scan_num = post.getScanNum();
        reply_num = post.getReplyNum();
        username = post.getUsername();
        build_time = post.getBuildTime();
        game_name = post.getGameName();
        image_url = post.getImageUrl();
        category = post.getCategory();
    }

    public static List<BriefPostVo> getBPVbyPosts(List<Post> posts){
        int length = posts.size();
        List<BriefPostVo> retList = new ArrayList<>();
        for(int i = 0; i < length; i++) {
                retList.add(new BriefPostVo(posts.get(i)));
        }
        return retList;
    }

    public static BriefPostVo getBPVbyAPost(Post post){
        return  new BriefPostVo(post);
    }
}
