package com.hcven.community.service;

import com.hcven.community.data.PostComment;
import com.hcven.community.vo.MineUserVO;
import com.hcven.community.vo.PostVO;

import java.util.List;
import java.util.Map;

/**
 * @author zhanghao
 * @since 2019-03-12
 */
public interface PostService {

    /**
     * 获得Post列表
     * @param start
     * @param count
     * @param username
     * @param system 是否为系统访问
     * @return
     */
    List<PostVO> listPost(Long start, Integer count, String username, Boolean system);

    /**
     * list查询的时候，返回总数来分页
     * @param username 如果不为null，则查询某个人
     * @return
     */
    Integer countPost(String username);

    /**
     * 新增一条post
     * @param post
     * @return
     */
    Boolean addPost(PostVO post);

    /**
     * 删除一条post
     * @param id
     * @return
     */
    Map<String, Object> deletePost(Long id);

    /**
     * 用户点赞
     * @param postId
     * @return
     */
    Boolean userLikePost(Long postId);

    /**
     * 用户取消点赞
     * @param postId
     * @return
     */
    Boolean userUnlikePost(Long postId);

    /**
     * 获得带有id的所有comment
     * @param postId postId
     * @return
     */
    PostComment getPostComment(Long postId);

    /**
     * 添加一条comment
     * @param postId
     * @param content
     * @return 成功 失败
     */
    Boolean addPostComment(Long postId, String content);

    /**
     * 热门推荐
     * @param start
     * @param count
     * @return
     */
    List<PostVO> listRecommendPost(String location, Long start, Integer count);

    /**
     * 热门推荐计数
     * @Param location
     * @return
     */
    Integer countRecommendPost(String location);

    /**
     * 为我推荐，一次获取二十条
     * @return
     */
    List<PostVO> getUserRecommend();


    /**
     * 查看用户详情页的信息
     * @param username 用户名 为null则为查询自己
     * @return
     */
    MineUserVO getMineUserDetail(String username);

    //todo 上线完删掉
    void postTagInit();
}
