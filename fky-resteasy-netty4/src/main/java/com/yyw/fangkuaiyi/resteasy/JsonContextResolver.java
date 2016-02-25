package com.yyw.fangkuaiyi.resteasy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyw.fangkuaiyi.util.JsonMapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Component
@Provider
public class JsonContextResolver implements ContextResolver<ObjectMapper> {

	final ObjectMapper mapper	= JsonMapper.nonEmptyMapper().getMapper();

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return mapper;
	}
}
