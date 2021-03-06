/**
 * Copyright (c) 2011-2015, Mobangjack 莫帮杰 (mobangjack@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jodb.dialect;

import java.util.List;
import java.util.Map;

/**
 * Db2Dialect for DB2 database.
 * @author 帮杰
 *
 */
public class Db2Dialect extends DefaultDialect {

	@Override
	public String paginate(String table, Map<String, Object> map,
			int pageNumber, int pageSize,List<Object> params) {
		int start = (pageNumber - 1) * pageSize + 1;
		int end = pageNumber * pageSize;
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( select row_.*, rownum rownum_ from (  ");
		sql.append(select(table,map,params));
		sql.append(" ) row_ where rownum <= ").append(end).append(") table_alias");
		sql.append(" where table_alias.rownum_ >= ").append(start);
		return sql.toString();
	}

}
