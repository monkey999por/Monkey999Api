package com.monkey999.ent.interfaces;


import com.monkey999.ent.interfaces.base.BaseRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRes extends BaseRes {
    private String message;
}
