package com.example.backend.mapper;

import com.example.backend.entity.ItemNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/28
 * @JDKVersion 17.0.4
 */
@Mapper
public interface ItemNoticeMapper {
    @Select("SELECT * FROM item_notice WHERE user_id=${userId}")
    List<ItemNotice> selectItemNotice(@Param("userId") Long userId);

    @Update("UPDATE item_notice set status=1 WHERE item_notice_id=${itemNoticeId}")
    int modifyItemNoticeStatus(@Param("itemNoticeId")Long itemNoticeId);
}
