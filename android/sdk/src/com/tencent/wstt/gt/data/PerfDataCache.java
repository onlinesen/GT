/*
 * Tencent is pleased to support the open source community by making
 * Tencent GT (Version 2.4 and subsequent versions) available.
 *
 * Notwithstanding anything to the contrary herein, any previous version
 * of Tencent GT shall not be subject to the license hereunder.
 * All right, title, and interest, including all intellectual property rights,
 * in and to the previous version of Tencent GT (including any and all copies thereof)
 * shall be owned and retained by Tencent and subject to the license under the
 * Tencent GT End User License Agreement (http://gt.qq.com/wp-content/EULA_EN.html).
 * 
 * Copyright (C) 2015 THL A29 Limited, a Tencent company. All rights reserved.
 * 
 * Licensed under the MIT License (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://opensource.org/licenses/MIT
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.tencent.wstt.gt.data;

import java.util.HashMap;
import java.util.Map;

import com.tencent.wstt.gt.AidlTask;
import com.tencent.wstt.gt.QueryPerfEntry;
import com.tencent.wstt.gt.data.local.LocalNumberDataPerfEntry;

public abstract class PerfDataCache {
	// 对于耗时统计，只存startTime的Entry，Reduce直接送TaskCache中的队列
	private Map<QueryPerfEntry, LocalNumberDataPerfEntry> perfMap;
	
	public PerfDataCache()
	{
		perfMap = new HashMap<QueryPerfEntry, LocalNumberDataPerfEntry>();
	}
	
	public void put(LocalNumberDataPerfEntry dataEntry)
	{
		perfMap.put(dataEntry.getQueryEntry(), dataEntry);
	}
	
	public LocalNumberDataPerfEntry get(QueryPerfEntry queryEntry)
	{
		return perfMap.remove(queryEntry);
	}
	
	public void clear()
	{
		perfMap.clear();
	}
	
	/**
	 * 返回统计数据到本地，并将统计数据发到控制台
	 */
	abstract public AidlTask profiler(LocalNumberDataPerfEntry...endDataEntry);
}