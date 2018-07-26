package com.ym.quartz.core.quartz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.ym.quartz.core.base.Pagination;
import com.ym.quartz.core.util.InstanceUtil;
import com.ym.quartz.core.util.PageUtil;
import com.ym.quartz.domain.TaskFireLog;
import com.ym.quartz.mapper.TaskFireLogMapper;

/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
@Configuration
public class SchedulerServiceImpl {
    @Autowired
    private TaskFireLogMapper logMapper;

    @Lazy
    @Autowired
    private SchedulerManager schedulerManager;

    @Autowired
    protected ApplicationContext applicationContext;

    // 获取所有作业
    public List<TaskScheduled> getAllTaskDetail() {
        return schedulerManager.getAllJobDetail();
    }

    // 执行作业
    public void execTask(TaskScheduled taskScheduler) {
        schedulerManager.runJob(taskScheduler);
    }

    // 恢复作业
    public void openTask(TaskScheduled taskScheduled) {
        schedulerManager.resumeJob(taskScheduled);
    }

    // 暂停作业
    public void closeTask(TaskScheduled taskScheduled) {
        schedulerManager.stopJob(taskScheduled);
    }

    // 删除作业
    public void delTask(TaskScheduled taskScheduled) {
        schedulerManager.delJob(taskScheduled);
    }

    // 修改任务
    public void updateTask(TaskScheduled taskScheduled) {
        schedulerManager.updateTask(taskScheduled);
    }

    @Cacheable("taskFireLog")
    public TaskFireLog getFireLogById(Long id) {
        return logMapper.selectById(id);
    }

    @Transactional
    @CachePut("taskFireLog")
    public TaskFireLog updateLog(TaskFireLog record) {
        if (record.getId() == null) {
            logMapper.insert(record);
        } else {
            logMapper.updateById(record);
        }
        return record;
    }

    public Pagination<TaskFireLog> queryLog(Map<String, Object> params) {
        Page<Long> ids = PageUtil.getPage(params);
        ids.setRecords(logMapper.selectIdByMap(ids, params));
        Pagination<TaskFireLog> page = new Pagination<TaskFireLog>(ids.getCurrent(), ids.getSize());
        page.setTotal(ids.getTotal());
        if (ids != null) {
            List<TaskFireLog> records = InstanceUtil.newArrayList();
            for (Long id : ids.getRecords()) {
                records.add(applicationContext.getBean(getClass()).getFireLogById(id));
            }
            page.setRecords(records);
        }
        return page;
    }
}
