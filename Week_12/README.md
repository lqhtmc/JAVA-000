学习笔记
一、redis主从配置
1、master
port 6379
bind 127.0.0.1
2、slave1
port 6380
bind 127.0.0.1
slaveof 127.0.0.1 6379
3、slave1
port 6381
bind 127.0.0.1
slaveof 127.0.0.1 6379
4、window下启动
redis-server.exe redis.windows6379.conf
redis-server.exe redis.windows6380.conf
redis-server.exe redis.windows6381.conf
5、查看主从信息
redis-cli -h 127.0.0.1 -p 6379
info replication
redis-cli -h 127.0.0.1 -p 6380
info replication
redis-cli -h 127.0.0.1 -p 6381
info replication

二、redis sentinal高可用配置
1、sentinal26379
port 26379
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 15000
2、sentinal26380
port 26380
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 15000
3、sentinal26381
port 26381
sentinel monitor mymaster 127.0.0.1 6379 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 15000
4、启动哨兵
redis-server.exe sentinel26379.conf --sentinel
redis-server.exe sentinel26380.conf --sentinel
redis-server.exe sentinel26381.conf --sentinel
5、功能验证
关闭6379redis服务，查看主从状态
重启6379redis服务，查看主从状态

三、redis cluster配置
复制下面多份配置
1、redis.windows-service-6379.conf
port 6380      
appendonly yes
appendfilename "appendonly.6380.aof"   
cluster-enabled yes                                    
cluster-config-file nodes.6380.conf
cluster-node-timeout 15000
cluster-slave-validity-factor 10
cluster-migration-barrier 1
cluster-require-full-coverage yes
2、redis.windows-service-6380conf
port 6380      
appendonly yes
appendfilename "appendonly.6380.aof"   
cluster-enabled yes                                    
cluster-config-file nodes.6380.conf
cluster-node-timeout 15000
cluster-slave-validity-factor 10
cluster-migration-barrier 1
cluster-require-full-coverage yes
3、启动
redis-server.exe redis.windows-service-6380.conf
redis-server.exe redis.windows-service-6381.conf



