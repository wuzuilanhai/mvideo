package com.mvideo.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.omg.CORBA.Object;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 16/12/5.
 */
public class ListAttrTypeHandler extends BaseTypeHandler<List<Object>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Object> objects, JdbcType jdbcType) throws SQLException {
        if (objects != null && preparedStatement != null) {
            preparedStatement.setString(i, JSONObject.toJSONString(objects));
        }
    }

    @Override
    public List<Object> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return JSONArray.parseArray(resultSet.getString(s), Object.class);
    }

    @Override
    public List<Object> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet == null) {
            return null;
        }
        return JSONArray.parseArray(resultSet.getString(i), Object.class);
    }

    @Override
    public List<Object> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement == null) {
            return null;
        }
        return JSONArray.parseArray(callableStatement.getString(i), Object.class);
    }
}