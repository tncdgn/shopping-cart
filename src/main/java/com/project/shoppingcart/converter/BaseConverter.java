package com.project.shoppingcart.converter;

import com.project.shoppingcart.entity.BaseEntity;
import com.project.shoppingcart.model.BaseModel;

public abstract class BaseConverter<E extends BaseEntity, M extends BaseModel> {

    public abstract E toEntity(M m);

    public abstract M toModel(E e);
}
