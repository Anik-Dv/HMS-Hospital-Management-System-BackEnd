package com.hms.anikdv.code.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author AnikDV
 * @info This is ModelMapper Configuration Class
 */
@Configuration
public class ModelMapperConfiguration {
    /**
     * @return This method returns modelMapper object
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
