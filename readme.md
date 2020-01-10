## mqtt 测试服务器搭建
    参照demo：https://github.com/gulteking/spring-boot-mqtt-sample
1. 准备 docker 环境
2. 项目根目录下运行
* `docker-compose up -d` 新建容器
* `docker-compose start` 启动服务
* `docker-compose stop` 停止服务

## mqtt 测试
向 http://localhost:8080/test/mqtt/publish 发送 POST 请求
````
{
	"message": {
		"deviceId":"123",
		"step":"12333",
		"longitude":22312.123,
		"latitude":3323.33
	},
	"topic":"hello",
	"retained":true,
	"qos":0
}
````