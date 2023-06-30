package com.icedragongame.vo;

import com.icedragongame.entity.Reply;
import com.icedragongame.utils.MyBeanUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyVo {
    Integer replyId;
    String replyContext;
    LocalDateTime buildTime;

    public ReplyVo(Reply reply) {
        replyId = reply.getReplyId();
        replyContext = reply.getReplyContext();
        buildTime = reply.getBuildTime();
    }

    public static List<ReplyVo> getRVbyReply(List<Reply> replyList ){
    int i=0;
    List<ReplyVo> replyVos = new ArrayList<>();

    while (i<replyList.size()){
        ReplyVo replyVo = MyBeanUtils.beanCopy(replyList.get(i),ReplyVo.class,true);
        replyVos.add(replyVo);
        i++;
    }
    return replyVos;
}


}
