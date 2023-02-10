package com.monkey999.ent.interfaces.base;


import com.monkey999.ent.common.ServerError;

public class BaseRes {
    public String status;

    /**
     * TODO: エラー時だし分け
     * https://qiita.com/syukai/items/f03844feca78572ce24f
     */
    public ServerError error;
}
