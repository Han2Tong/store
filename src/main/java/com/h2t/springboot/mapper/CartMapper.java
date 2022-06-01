package com.h2t.springboot.mapper;

import com.h2t.springboot.entity.Cart;
import com.h2t.springboot.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import javax.lang.model.element.NestingKind;
import java.util.Date;
import java.util.List;

/**
 * 购物车的持久化操作
 */
public interface CartMapper {
    /**
     * 向购物车插入数据
     * @param cart 购物车数据
     * @return 受影响行数
     */
   Integer insert (Cart cart);

    /**
     * 修改购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户的id和商品的id来查询购物车中的数据
     * @param uid
     * @param pid
     * @return
     */
   Cart findByUidAndPid(Integer uid,Integer pid);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> findVOByUid(Integer uid);


    /**
     * 根据购物车数据id查询购物车数据详情
     * @param cid 购物车数据id
     * @return 匹配的购物车数据详情，如果没有匹配的数据则返回null
     */
    Cart findByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> findVOByCids(Integer[] cids);
}
