package com.xiexin.dao;

import com.xiexin.bean.Jiaose;
import com.xiexin.bean.JiaoseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JiaoseDAO {
    long countByExample(JiaoseExample example);

    int deleteByExample(JiaoseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jiaose record);

    int insertSelective(Jiaose record);

    List<Jiaose> selectByExample(JiaoseExample example);

    Jiaose selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jiaose record, @Param("example") JiaoseExample example);

    int updateByExample(@Param("record") Jiaose record, @Param("example") JiaoseExample example);

    int updateByPrimaryKeySelective(Jiaose record);

    int updateByPrimaryKey(Jiaose record);
}