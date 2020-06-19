:: cd D:\SoftWare\rocketmq-all-4.7.0-bin-release\bin
:: call mqbroker.cmd -n 127.0.0.1:9876 -c ../conf/broker.conf
call %ROCKETMQ_HOME%\bin\mqbroker.cmd -n 127.0.0.1:9876 -c %ROCKETMQ_HOME%\conf\broker.conf