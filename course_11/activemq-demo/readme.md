必做1：
````
复制一份sentinel.conf文件
cp sentinel.conf sentinel‐26379.conf

将相关配置修改为如下值：

port 26379
daemonize yes
pidfile "/var/run/redis‐sentinel‐26379.pid"
logfile "26379.log"
dir "/usr/local/redis‐5.0.3/data"

# sentinel monitor <master‐name> <ip> <redis‐port> <quorum>
# quorum是一个数字，指明当有多少个sentinel认为一个master失效时(值一般为：sentinel总数/2 +
1)，master才算真正失效12 sentinel monitor mymaster 192.168.0.60 6379 2

 3、启动sentinel哨兵实例
 src/redis‐sentinel sentinel‐26379.conf

 4、查看sentinel的info信息
 src/redis‐cli ‐p 26379
 127.0.0.1:26379>info
 可以看到Sentinel的info里已经识别出了redis的主从

 5、可以自己再配置两个sentinel，端口26380和26381，注意上述配置文件里的对应数字都要修改
````
必做6：