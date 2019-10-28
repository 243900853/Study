--定时任务表
select * from dba_jobs;
--删除序列   drop sequence job_task_log_sequence;
--删除表    drop table job_task_log
--删除存储过程     drop procedure equitablePrincipleJob
--启动定时任务     begin dbms_job.run(4); end;
--删除定时任务      begin dbms_job.remove(3);end;
--停止定时任务      begin dbms_job.broken(4,true);end;
select * from job_task_log order by create_time desc;

--序列
create sequence job_task_log_sequence
increment by 1      --每次加几
start with 1        --从1开始计数
maxvalue 999999999  --最大值
cache 10            --设置缓存，增加读写速度
;

--定时任务记录表
create table job_task_log(
       id varchar2(50) primary key,
       name varchar2(100),
       create_time date,
       mark varchar2(100)
);
comment on table job_task_log is '定时任务记录表';

--带事务的存储过程
create or replace procedure equitableprinciplejob is
  dynamic_sql varchar2(1000);
  dynamic_table varchar2(50);
  dynamic_pamara1 varchar2(50) := 'equitableprinciplejob'||to_char(sysdate,'yyyy-mm-dd hh24:mi:ss');
  dynamic_pamara2 varchar2(2) := '';
begin
  dynamic_sql := 'insert into job_task_log values (job_task_log_sequence.nextval,:1,sysdate,:2)';
  execute immediate dynamic_sql using dynamic_pamara1,dynamic_pamara2;
  --insert into job_task_log values (job_task_log_sequence.nextval,'equitableprinciplejob',sysdate, '');
  commit;
exception
  when others then
    dbms_output.put_line('操作失败');
    rollback;
end equitableprinciplejob;

--创建定时任务
declare
  jobno number;
begin
  dbms_job.submit(
      jobno,                        --定时器ID，系统自动获得
      'equitablePrincipleJob;',     --what执行的过程名
      sysdate,                      --next_date,定时器开始执行的时间，这样写表示立即执行
      'sysdate+1/(24*60*60)'        --interval,设置定时器执行的频率，这样写天每隔1秒执行一次
  );
  commit;
end;

