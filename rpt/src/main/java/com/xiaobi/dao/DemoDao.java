package com.xiaobi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.xiaobi.bean.Query;
@Component
public class DemoDao<T>{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final Logger logger= LoggerFactory.getLogger(this.getClass());
    public Query<T> queryMessage(Query<T> query,String oldSql,RowMapper<T> Mapper) {
    	//参数
    	Map<String,String> params = query.getQueryParameters();
    	//要执行的sql
    	String executedSql = "select #variable# from (#sql#) a where 1=1 \n";
    	executedSql = executedSql.replace("#sql#", oldSql);
    	//拼接参数
    	String paramSql = setParameter(params, oldSql);
    	//拼接排序
    	String orderbySql = setOrderBy(params);
    	//将参数和排序拼接到executedSql里面
    	executedSql += paramSql;
    	executedSql += orderbySql;
    	//总数
    	String countExecutedSql = executedSql.replace("#variable#", "COUNT(*)");
    	int totalRecord = jdbcTemplate.queryForObject(countExecutedSql,Integer.class);
    	//保存总数
    	query.setTotalRecord(totalRecord);
    	//分页
    	String pageSql = " LIMIT "+(query.getCurrentPage()-1)*query.getPageSize()+","+query.getPageSize();
    	//将分页拼接到executedSql里面
    	executedSql += pageSql;
    	//用来保存查询出来的数据
    	List<T> messageList = new ArrayList<T>();
    	executedSql = executedSql.replace("#variable#", "*");
    	messageList= jdbcTemplate.query(executedSql, Mapper);
    	query.setDataList(messageList);
    	return query;
	}
    
    //将参数拼接起来
    public String setParameter(Map<String,String> params,String oldSql){
    	String param = "";
    	for (Entry<String,String> entry : params.entrySet()) {
			logger.debug("entry："+entry.getKey()+"="+entry.getValue());
			if( oldSql.contains("`"+entry.getKey()+"`") && entry.getValue()!=null 
					&& !entry.getValue().isEmpty()){//检查参数中存在对应的数据库字段
				param += " AND `"+entry.getKey()+"` = '"+entry.getValue()+"'";
			}else if( entry.getKey().contains("_start") ){//如create_time_start 表示需查询create_time大于此时间的数据
				String subKey = entry.getKey().substring(0, entry.getKey().indexOf("_start"));
				if(subKey.contains("_money")){
					String key = subKey.substring(0, subKey.indexOf("_money"));
					if(oldSql.contains(key) && entry.getValue()!=null
							&& !entry.getValue().isEmpty()){
						param += " AND `"+key+"` >= "+entry.getValue()+"*100";
					}
				}else{
					if(oldSql.contains(subKey) && entry.getValue()!=null
							&& !entry.getValue().isEmpty()){
						param += " AND `"+subKey+"` >= '"+entry.getValue()+"'";
					}
				}
			}else if(entry.getKey().contains("_end") ){//如create_time_end 表示需查询create_time小于此时间的数据
				String subKey = entry.getKey().substring(0, entry.getKey().indexOf("_end"));
				if(subKey.contains("_money")){
					String key = subKey.substring(0, subKey.indexOf("_money"));
					if(oldSql.contains(key) && entry.getValue()!=null
							&& !entry.getValue().isEmpty()){
						param += " AND `"+key+"` <= "+entry.getValue()+"*100";
					}
				}else{
					if(oldSql.contains(subKey) && entry.getValue()!=null
							&& !entry.getValue().isEmpty()){
						param += " AND `"+subKey+"` <= '"+entry.getValue()+"'";
					}
				}
			}else if(entry.getKey().contains("_like")){//需要模糊查询
				String subKey = entry.getKey().substring(0, entry.getKey().indexOf("_like"));
				if(oldSql.contains(subKey) && entry.getValue()!=null
						&& !entry.getValue().isEmpty()){
					param += " AND `"+subKey+"` LIKE '%"+entry.getValue()+"%'";
				}
			}
		}
    	return param;
    }
    //将排序拼接起来
    public String setOrderBy(Map<String,String> params){
    	String orderBy = "";
    	for (Entry<String,String> entry : params.entrySet()) {
			logger.debug("entry："+entry.getKey()+"="+entry.getValue());
			if(entry.getKey().contains("orderBy_")){
				String subKey = entry.getKey().substring(8);
				String[] strArray = subKey.split("_And_");
				if(strArray.length == 1){
					if(Long.valueOf(entry.getValue())==1){
						orderBy+=" `"+subKey+"` asc";
					}else if(Long.valueOf(entry.getValue())==2){
						orderBy+=" `"+subKey+"` desc";
					}
				}else if(strArray.length > 1){
					if(Long.valueOf(entry.getValue())==1){
						orderBy+=" "+StringUtils.join(strArray,",")+" asc";
					}else if(Long.valueOf(entry.getValue())==2){
						orderBy+=" "+StringUtils.join(strArray,",")+" desc";
					}
				}
			}
		}
    	return (orderBy.length()>1?" ORDER BY "+orderBy:"");
    }
}
