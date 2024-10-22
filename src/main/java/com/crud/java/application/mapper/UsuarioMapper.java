package com.crud.java.application.mapper;
import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "id", expression = "id")
    @Mapping(target = "idade", source = "idade")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cpfCnpj", source = "cpfCnpj")
    UsuarioEntity objToEntity(UsuarioDTO usuario);
}
