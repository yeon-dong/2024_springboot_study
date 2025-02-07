package com.hyundaiautoever.beexample.infrastructure.mapper;

import com.hyundaiautoever.beexample.infrastructure.mapper.dto.TextElementDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Locale;

@Mapper
public interface TextElementMapper {
    TextElementDto existBy(Locale locale, String textCode);
    List<TextElementDto> findBy(@Param("uri") String uri, @Param("locale") String locale);
}
