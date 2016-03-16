package com.its.util;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 继承自己的MyMapper
 *
 * @author
 * @since
 */
@Component
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
