--��ʱ�����
select * from dba_jobs;
--ɾ������   drop sequence job_task_log_sequence;
--ɾ����    drop table job_task_log
--ɾ���洢����     drop procedure equitablePrincipleJob
--������ʱ����     begin dbms_job.run(4); end;
--ɾ����ʱ����      begin dbms_job.remove(3);end;
--ֹͣ��ʱ����      begin dbms_job.broken(4,true);end;
select * from job_task_log order by create_time desc;

--����
create sequence job_task_log_sequence
increment by 1      --ÿ�μӼ�
start with 1        --��1��ʼ����
maxvalue 999999999  --���ֵ
cache 10            --���û��棬���Ӷ�д�ٶ�
;

--��ʱ�����¼��
create table job_task_log(
       id varchar2(50) primary key,
       name varchar2(100),
       create_time date,
       mark varchar2(100)
);
comment on table job_task_log is '��ʱ�����¼��';

--������Ĵ洢����
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
    dbms_output.put_line('����ʧ��');
    rollback;
end equitableprinciplejob;

--������ʱ����
declare
  jobno number;
begin
  dbms_job.submit(
      jobno,                        --��ʱ��ID��ϵͳ�Զ����
      'equitablePrincipleJob;',     --whatִ�еĹ�����
      sysdate,                      --next_date,��ʱ����ʼִ�е�ʱ�䣬����д��ʾ����ִ��
      'sysdate+1/(24*60*60)'        --interval,���ö�ʱ��ִ�е�Ƶ�ʣ�����д��ÿ��1��ִ��һ��
  );
  commit;
end;

