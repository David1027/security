package com.david.user.vo.mapper;


import com.david.user.entity.User;
import com.david.user.vo.UserView;
import org.modelmapper.ModelMapper;

/**
 * (User)的DTO和DO转换类
 *
 * @author lingjian
 * @since 2019-08-27 15:46:53
 */
public class UserMapper {
    
    private ModelMapper modelMapper = null;
    
    /**
     * DO转为DTO
     */
    private void typeMapperDOToDTO() {
        modelMapper.createTypeMap(User.class, UserView.class).addMappings(mapper ->
                mapper.map(User::getId, UserView::setId));
    }

    /**
     * DTO转为DO
     */
    private void typeMapperDTOToDO() {
        modelMapper.createTypeMap(UserView.class, User.class).addMappings(mapper ->
                mapper.map(UserView::getId, User::setId));
    }

    /**
     * DO与DTO之间的转化方法
     *
     * @param flag 布尔值 true：DO->DTO，false：DTO->DO
     * @return ModelMapper对象
     */
    public ModelMapper modelMapperConfig(Boolean flag) {
        modelMapper = new ModelMapper();
        if (flag) {
            typeMapperDOToDTO();
        } else {
            typeMapperDTOToDO();
        }
        return modelMapper;
    }
}