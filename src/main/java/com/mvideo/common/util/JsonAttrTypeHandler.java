package com.mvideo.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by admin on 16/12/5.
 */
public class JsonAttrTypeHandler extends BaseTypeHandler<JSONObject> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONObject jsonObject, JdbcType jdbcType) throws SQLException {
        if (jsonObject != null && preparedStatement != null) {
            preparedStatement.setString(i, jsonObject.toJSONString());
        }
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return JSONObject.parseObject(resultSet.getString(s));
    }

    @Override
    public JSONObject getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return JSONObject.parseObject(resultSet.getString(i));
    }

    @Override
    public JSONObject getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement == null) {
            return null;
        }
        return JSONObject.parseObject(callableStatement.getString(i));
    }
}