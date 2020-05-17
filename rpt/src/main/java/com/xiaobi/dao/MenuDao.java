package com.xiaobi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.xiaobi.bean.Menu;
import com.xiaobi.bean.Query;
/**
 * @author Nifury
 * @data 2017-1-9
 */
@Repository("menuDao")
public class MenuDao extends DemoDao<Menu>{

    @Autowired
    private JdbcTemplate jdbcTemplate;

  	/**
	 * BeanMapper 将sql数据库查到的数据转为javaBean的对象
	 * @return javaBean的对象
	 */
     public final static RowMapper<Menu> MenuMapper = new RowMapper<Menu>() {

        public Menu mapRow(ResultSet rs, int rowNumber) throws SQLException {
        	Menu bean= new Menu();
			bean.setMenuId(rs.getLong("menu_id"));
			bean.setParentId(rs.getLong("parent_id"));
			bean.setName(rs.getString("name"));
			bean.setUrl(rs.getString("url"));
			bean.setLevel(rs.getLong("level"));
			bean.setDescription(rs.getString("description"));
			bean.setStatus(rs.getLong("status"));
			bean.setUpdateTime(rs.getTimestamp("update_time"));
			bean.setCreateTime(rs.getTimestamp("create_time"));
			bean.setType(rs.getLong("type"));
            return bean;
        }
    };

    private final static String QUERY
			= "SELECT "
			+" `menu_id` ,\n"
			+" `parent_id` ,\n"
			+" `name` ,\n"
			+" `url` ,\n"
			+" `level` ,\n"
			+" `description` ,\n"
			+" `status` ,\n"
			+" `update_time` ,\n"
			+" `create_time` ,\n"
			+" `type` \n"
			+" FROM `menu`\n ";

	/**
	 * 通过主键Id来查询
	 * @param menuId 默认表中第一个字段名称
	 * @return Menu对象
	 */
	public Menu queryByKey(Long menuId) {
		Object[] args = {menuId};
		List<Menu> list = jdbcTemplate.query(QUERY + " WHERE menu_id = ?", args, MenuMapper);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}


	/**
	 * 查询所有记录
	 * @return Menu对象列表
	 */
	public List<Menu> queryAll() {
        return jdbcTemplate.query(QUERY, MenuMapper);
    }

    private final static String INSERT
            = "INSERT INTO "
            +" `menu`(\n"
			+" `parent_id`, \n"
			+" `name`, \n"
			+" `url`, \n"
			+" `level`, \n"
			+" `description`, \n"
			+" `status`, \n"
			+" `update_staff_id`, \n"
			+" `update_time`, \n"
			+" `create_staff_id`, \n"
			+" `create_time`, \n"
			+" `type` )\n"
			+ " VALUES( ? ,? ,? ,? ,? ,? ,? ,? ,?,?,?)";

	/**
	 * 插入新记录并返回主键
	 * @param bean 需要插入的Menu对象
	 * @return 插入的Menu 成功后返回的主键Id
	 */
	public Long insert(final Menu bean) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, bean.getParentId());
				ps.setString(2, bean.getName());
				ps.setString(3, bean.getUrl());
				ps.setLong(4, bean.getLevel());
				ps.setString(5, bean.getDescription());
				ps.setLong(6, bean.getStatus());
				ps.setString(7, bean.getUpdateStaffId());
				ps.setTimestamp(8, bean.getUpdateTime());
				ps.setString(9, bean.getCreateStaffId());
				ps.setTimestamp(10, bean.getCreateTime());
				ps.setLong(11, bean.getType());
                return ps;
            }
        }, key);
        return key.getKey().longValue();
    }

    private final static String UPDATE
            = "UPDATE `menu` "
            + "SET\n"
			+" `parent_id` = ? , \n"
			+" `name` = ? , \n"
			+" `url` = ? , \n"
			+" `level` = ? , \n"
			+" `description` = ? , \n"
			+" `status` = ? , \n"
			+" `update_staff_id` = ? , \n"
			+" `update_time` = ? , \n"
			+" `create_staff_id` = ? , \n"
			+" `create_time` = ? , \n"
			+" `type` = ? \n"
			+ " WHERE "
			+" menu_id = ? ";


	/**
	 * 更新记录
	 * @param bean 需要更新的Menu对象
	 */
    public void update(Menu bean) {
        jdbcTemplate.update(UPDATE,
				bean.getParentId(),
				bean.getName(),
				bean.getUrl(),
				bean.getLevel(),
				bean.getDescription(),
				bean.getStatus(),
				bean.getUpdateStaffId(),
				bean.getUpdateTime(),
				bean.getCreateStaffId(),
				bean.getCreateTime(),
				bean.getType(),
      			bean.getMenuId());
    }

    private final static String DELETE
      		= "DELETE FROM menu WHERE menu_id = ?";

    /**
	 * 通过主键删除记录
	 * 传入需要删除的记录的menuId
	 */
    public void deleteByKey(Long menuId) {
        jdbcTemplate.update(DELETE, menuId);
    }
    /***********************************************xiaobi*******************************************/
    private final static String QUERYMENUPOWER
		= "SELECT "
		+" e.menu_id `menu_id` ,\n"
		+" e.parent_id `parent_id` ,\n"
		+" e.name `name` ,\n"
		+" e.url `url` ,\n"
		+" e.level `level` ,\n"
		+" e.description`description` ,\n"
		+" e.status `status` ,\n"
		+" e.type `type` ,\n"
		+" e.update_time`update_time` ,\n"
		+" e.create_time `create_time` \n"
		+" from role_menu a,menu e,role c \n"
		+" where a.menu_id = e.menu_id and a.role_id = c.role_id  \n"
		+" and a.role_id in(select role_id from staff_role where staff_id = ? and status =0) \n"
		+" and a.status = 0 and e.`status`=0 and c.`status` = 0\n";
	/**
	* 通过用户id查询用户的一级菜单权限
	*/
    public List<Menu> queryOneMenuPowerBySId(Long staffId) {
        return jdbcTemplate.query(QUERYMENUPOWER+" and e.`level`=1 and e.`type`=1 and e.`status`=0\n"
        		+ "group by e.menu_id,e.parent_id,e.name,e.url", MenuMapper,staffId);
    }
    /**
	* 通过用户id和菜单上级id查询用户的一级菜单下对应的二级菜单权限
	*/
    public List<Menu> queryTwoMenuPowerBySIdAndPId(Long staffId,Long parentId) {
        return jdbcTemplate.query(QUERYMENUPOWER+" and e.parent_id = ? and "
        		+ "e.parent_id != e.menu_id and e.status = 0 group by e.menu_id order by e.menu_id "
        		+ ", e.parent_id  , e.name  , e.url", MenuMapper,staffId,parentId);
    }
    /**
     * 查询所有的一级菜单
     */
    public List<Menu> queryAllOneMenu(){
    	return jdbcTemplate.query(QUERY+" where type = 1 and level = 1 and status = 0", MenuMapper);
    }
    /**
     * 根据上级id查询菜单
     */
    public List<Menu> queryByParentId(Long parentId) {
        return jdbcTemplate.query(QUERY+" where parent_id = ? and type = 1 and status !=1 \n"
        		+ "order by menu_id", MenuMapper,parentId);
    }

    public final static RowMapper<Menu> ParentMenuMapper = new RowMapper<Menu>() {

        public Menu mapRow(ResultSet rs, int rowNumber) throws SQLException {
        	Menu bean= new Menu();
			bean.setMenuId(rs.getLong("menu_id"));
			bean.setParentId(rs.getLong("parent_id"));
			bean.setName(rs.getString("name"));
			bean.setUrl(rs.getString("url"));
			bean.setLevel(rs.getLong("level"));
			bean.setDescription(rs.getString("description"));
			bean.setStatus(rs.getLong("status"));
			bean.setUpdateTime(rs.getTimestamp("update_time"));
			bean.setCreateTime(rs.getTimestamp("create_time"));
			bean.setType(rs.getLong("type"));
			bean.setParentName(rs.getString("parentName"));
            return bean;
        }
    };
    /**
     * 带上级菜单名称
     */
    public Query<Menu> parentMenu(Query<Menu> query){
    	String sql = "select a.`menu_id` ,a.`parent_id` ,a.`name` ,a.`url` ,a.`level` ,a.`description` ,"
    			+ "a.`status` ,a.`update_time` ,a.`create_time` ,a.`type`,b.name as parentName from menu a "
    			+ "left JOIN menu b on a.parent_id = b.menu_id";
    	queryMessage(query, sql, ParentMenuMapper);
   		return query;
    }
	/**
	 * 根据等级查询菜单
	 */
	public List<Menu> queryMenuByLevel(String level) {
		return jdbcTemplate.query(QUERY+" where level = ? and type = 1 and status !=1 \n"
				+ "order by menu_id", MenuMapper,level);
	}
	/**
	 * 条件查询统一模板
	 */
	public List<Menu> queryMenuByParam(Map<String,Object> map) {
		StringBuffer sql = new StringBuffer();
		sql.append(QUERY+" where ");
		if(!map.isEmpty()){
			map.forEach((key ,value) -> {
				boolean isArray = value.getClass().isArray();
				if(isArray){
					sql.append(key+" in (:"+key+") and ");
				}else{
					sql.append(key+" = :"+key+" and ");
				}
			});
		}
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Menu> menuList = jdbc.query(sql.substring(0,sql.length()-4), map, MenuMapper);
		return menuList;
	}

}
