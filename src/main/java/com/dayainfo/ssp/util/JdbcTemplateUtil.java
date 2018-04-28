package com.dayainfo.ssp.util;

import com.dayainfo.ssp.exception.JdbcTemplateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * @Author: longrui
 * @Date: 2018/3/14 10:20
 * <p>
 * jJdbcTemplate操作基础类
 */
public class JdbcTemplateUtil {
    private static ApplicationContext ctx = null;
    /**
     * spring提供的操作jdbc的工具类
     */
    private static JdbcTemplate jdbcTemplate = null;

    static {
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
    }


    /**
     * 查询数量
     *
     * @param sql   要查询的sql语句，参数用?代替
     * @return
     */
    public static int queryCount(String sql,Object[] para) throws JdbcTemplateException {
        checkSqlStr(sql);
        return jdbcTemplate.queryForObject(sql, para, Integer.class);
    }

    /**
     * 查询单条数据
     *
     * @param sql   要查询的sql语句，参数用?代替
     * @param Class 实体类的class对象，映射属性
     * @param args  sql语句中的参数值,可以为空
     * @return
     */
    public static Object querySingle(String sql, Class Class, Object... args) throws JdbcTemplateException {
        checkSqlStr(sql);
        RowMapper rowMapper = new BeanPropertyRowMapper(Class);
        return jdbcTemplate.queryForObject(sql, rowMapper, args);
    }

    /**
     * 查询列表
     *
     * @param sql   要查询的sql语句，参数用?代替
     * @param Class 实体类的class对象，映射属性
     * @param args  sql语句中的参数值,可以为空
     * @return
     */
    public static List queryList(String sql, Class Class, Object... args) throws JdbcTemplateException {
        checkSqlStr(sql);
        RowMapper rowMapper = new BeanPropertyRowMapper(Class);
        return jdbcTemplate.query(sql, rowMapper, args);
    }


    /**
     * 新增，更新，删除
     *
     * @param sql   要查询的sql语句，参数用?代替
     * @param args  sql语句中的参数值,可以为空
     * @return
     */
    public static int updateOperate(String sql, Object... args) throws JdbcTemplateException {
        checkSqlStr(sql);
        return jdbcTemplate.update(sql, args);
    }

    /**
     * 校验sql参数，不能为空,此校验不全，后面再补充
     *
     * @param sql
     * @throws JdbcTemplateException
     */
    private static void checkSqlStr(String sql) throws JdbcTemplateException {
        if (StringUtil.isEmpty(sql)) {
            throw new JdbcTemplateException("sql语句不能为空!");
        }
    }
}
