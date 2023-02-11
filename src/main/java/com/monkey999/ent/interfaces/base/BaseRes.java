package com.monkey999.ent.interfaces.base;


import com.monkey999.constant.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseRes {
    private ResponseStatus status = ResponseStatus.reject;

}
