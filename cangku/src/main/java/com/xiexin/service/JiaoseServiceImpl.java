package com.xiexin.service;

import com.xiexin.bean.Jiaose;
import com.xiexin.bean.JiaoseExample;
import com.xiexin.dao.JiaoseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jiaoseService")
public class JiaoseServiceImpl implements JiaoseService {
	@Autowired(required = false)
	private JiaoseDAO jiaoseDAO;
	public long countByExample(JiaoseExample example){
    	return jiaoseDAO.countByExample(example);
    }

	public int deleteByExample(JiaoseExample example){
    	return jiaoseDAO.deleteByExample(example);
    }

	public int deleteByPrimaryKey(Integer id){
    	return jiaoseDAO.deleteByPrimaryKey(id);
    }

	public int insert(Jiaose record){
    	return jiaoseDAO.insert(record);
    }

	public int insertSelective(Jiaose record){
    	return jiaoseDAO.insertSelective(record);
    }

	public List<Jiaose> selectByExample(JiaoseExample example){
    	return jiaoseDAO.selectByExample(example);
    }

	public Jiaose selectByPrimaryKey(Integer id){
    	return jiaoseDAO.selectByPrimaryKey(id);
    }
  
	public int updateByExampleSelective(Jiaose record, JiaoseExample example){
    	return jiaoseDAO.updateByExampleSelective(record, example);
    }

	public int updateByExample(Jiaose record, JiaoseExample example){
    	return jiaoseDAO.updateByExample(record, example);
    }

	public int updateByPrimaryKeySelective(Jiaose record){
    	return jiaoseDAO.updateByPrimaryKeySelective(record);
    }

	public int updateByPrimaryKey(Jiaose record){
    	return jiaoseDAO.updateByPrimaryKey(record);
    }


}
