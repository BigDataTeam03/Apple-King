package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.ProductListDto;

public interface ProductListDao {

	public List<ProductListDto> listDao() throws Exception;
}
